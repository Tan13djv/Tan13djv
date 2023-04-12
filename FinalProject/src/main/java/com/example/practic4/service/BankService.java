package com.example.practic4.service;

import com.example.practic4.controllers.ATMController;
import com.example.practic4.dto.Respond;
import com.example.practic4.entity.Balance;
import com.example.practic4.entity.Transaction;
import com.example.practic4.enums.Direction;
import com.example.practic4.repository.BalanceRepository;
import com.example.practic4.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {


    @Autowired
    private final BalanceRepository balancerepository;
    private final TransactionRepository transactionRepository;

    private static final Logger logger = LoggerFactory.getLogger(ATMController.class);

    public ResponseEntity<Respond> getBalance(long client_id) {

        String message = "";
        BigDecimal result;
        ResponseEntity responseEntity;
        Balance balance = balancerepository.getBalanceByClient_id(client_id);
        Respond respond = new Respond();
        if(balance!=null)
        {
            result = balancerepository.getBalanceByClient_id(client_id).getAmount();
            respond.setMessage(message);
            respond.setResult(result);
            responseEntity = ResponseEntity.ok().body(respond);
            logger.info("Запрос баланса клиента с ID = " + client_id + " выполнен успешно. Остаток = "+ result);
        }
        else
        {
            message = "Клиент с ID:" + client_id + " не найден";
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(-1));
            responseEntity = ResponseEntity.internalServerError().body(respond);
            logger.info("Клиент с ID:" + client_id + " не найден");
        }
        return responseEntity;
    }

    @Transactional
    public ResponseEntity<Respond> takeMoney(BigDecimal amount, Long client_id) {
        int result = 1;
        String message = "";
        ResponseEntity responseEntity;
        Respond respond = new Respond();
        try{
            newOperation(Direction.DEBET,amount,client_id);
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.ok().body(respond);
            logger.info("Списание средств у клиента с ID = " + client_id + " выполнено успешно. Сумма = "+ amount);
        }
        catch (Exception e){
            message = e.getMessage();
            result = 0;
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.internalServerError().body(respond);
            logger.error("Списание средств у клиента с ID = " + client_id + " не выполнено. Сумма = "+ amount+" Ошибка: "+message);
        }

        return responseEntity;

    }
    @Transactional
    public ResponseEntity<Respond> putMoney(BigDecimal amount, Long client_id) {
        int result = 1;
        String message = "";
        ResponseEntity responseEntity;
        Respond respond = new Respond();
        try{
            newOperation(Direction.CREDIT,amount,client_id);
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.ok().body(respond);
            logger.info("Зачисление средств клиенту с ID = " + client_id + " выполнено успешно. Сумма = "+ amount);
        }
        catch (Exception e){
            message = e.getMessage();
            result = 0;
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.internalServerError().body(respond);
            logger.error("Зачисление средств клиенту с ID = " + client_id + " не выполнено. Сумма = "+ amount+" Ошибка: "+message);
        }
        return responseEntity;

    }

    private void newOperation(Direction operationType, BigDecimal amount, Long client_id) throws Exception {


            Transaction transaction = new Transaction();
            Balance balance = balancerepository.getBalanceByClient_id(client_id);
            BigDecimal old_amont = BigDecimal.valueOf(0);

            if(balance!=null)
            {
                old_amont = balancerepository.getBalanceByClient_id(client_id).getAmount();
            }
            else
            {
                balance = new Balance();
                balance.setClient_id(client_id);
            }

            transaction.setClient_id(client_id);
            transaction.setDirection(operationType);
            transaction.setAmount(amount);
            transaction.setDatetime_op(Timestamp.valueOf(LocalDateTime.now()));
            if ((operationType.compareTo(Direction.CREDIT)==0)||(operationType.compareTo(Direction.INPUT)==0))
                balance.setAmount(old_amont.add(amount));
            else
                if(old_amont.compareTo(amount)>=0)
                 balance.setAmount(old_amont.subtract(amount));
                else {
                    logger.info("У клиента с ID = "+client_id+" Недостаточно средств. Остаток = "+old_amont+" Запрошенная сумма = "+amount);
                    throw new Exception("Недостаточно средств");
                }
            transactionRepository.save(transaction);
            balancerepository.save(balance);

    }

    public ResponseEntity<List<Transaction>> getOperationList(Long client_id, String dateFrom, String dateTo) throws Exception {
        LocalDate date_tmp = LocalDate.now();
        LocalDate date_next = date_tmp.plusDays(1);
        if (dateFrom==null)
            dateFrom = date_tmp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (dateTo==null)
            dateTo = date_next.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Transaction> transactions =  transactionRepository.getOpersByClient_idAndDatetime_op(client_id, dateFrom, dateTo);
        if (transactions.isEmpty())
            logger.info("У клиента с ID = "+client_id+" Не найдены операции за период с: "+dateFrom+" по: "+dateTo);
        return ResponseEntity.ok().body(transactions);
       }
    @Transactional
    public ResponseEntity<Respond> transferMoney(Long client_id1,  Long client_id2, BigDecimal amount) throws DatatypeConfigurationException {
        String message = "";
        int result = 1;
        ResponseEntity responseEntity;
        Respond respond = new Respond();
        try{
            newOperation(Direction.OUTPUT, amount, client_id1);
            newOperation(Direction.INPUT, amount, client_id2);
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.ok().body(respond);
            logger.info("Перевод средств от клиента с ID = " + client_id1 + " клиенту с ID = "+client_id2+" выполнен успешно. Сумма = "+ amount);
        }
        catch (Exception e){
            message = e.getMessage();
            result = 0;
            respond.setMessage(message);
            respond.setResult(BigDecimal.valueOf(result));
            responseEntity = ResponseEntity.internalServerError().body(respond);
            logger.error(message);
            logger.error("Перевод средств от клиента с ID = " + client_id1 + " клиенту с ID = "+client_id2+" не выполнен. Сумма = "+ amount+" Ошибка: "+message);
        }
        return responseEntity;
    }


}
