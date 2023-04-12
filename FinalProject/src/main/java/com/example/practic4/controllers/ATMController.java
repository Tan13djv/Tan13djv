package com.example.practic4.controllers;

import com.example.practic4.dto.Respond;
import com.example.practic4.entity.Transaction;
import com.example.practic4.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name="ATMController", description = "")
public class ATMController {
    private final BankService BankService;


    @GetMapping("/getBalance/{client_id}")

    @Operation(summary = "Получение баланса на текущий день")
    public ResponseEntity<Respond> getBalanceOnDate(@PathVariable @Parameter(description ="Идентификатор клиента") String client_id)
            throws Exception {
        return BankService.getBalance(Long.parseLong(client_id));
    }

    @GetMapping("/putMoney/{client_id}/{amount}")

     @Operation(summary = "Пополнение средств")
     public ResponseEntity<Respond> putMoney(@PathVariable @Parameter(description ="Сумма") String amount,
                                             @PathVariable @Parameter(description ="Идентификатор клиента") String client_id)
            throws Exception {
        return BankService.putMoney(new BigDecimal(amount),Long.parseLong(client_id));
    }

    @GetMapping("/takeMoney/{client_id}/{amount}")

    @Operation(summary = "Снятие средств")
    public ResponseEntity<Respond> takeMoney(@PathVariable @Parameter(description ="Сумма") String amount,
                                             @PathVariable @Parameter(description ="Идентификатор клиента") String client_id)
            throws Exception {
        return BankService.takeMoney(new BigDecimal(amount),Long.parseLong(client_id));
    }
    @GetMapping("/getOperationList/{client_id}/{date_from}/{date_to}")
    @Operation(summary = "Получение списка операций за период")

    public ResponseEntity<List<Transaction>> getOperationList(@PathVariable @Parameter(description ="Идентификатор клиента") String client_id,
                                                              @PathVariable @Parameter(description ="Дата начала периода") String date_from,
                                                              @PathVariable @Parameter(description ="Дата окончания периода") String date_to) throws Exception {
        return BankService.getOperationList(Long.parseLong(client_id),date_from,date_to);
    }

    @GetMapping("/getOperationList/{client_id}")
    @Operation(summary = "Получение списка операций за текущий день")
    public ResponseEntity<List<Transaction>> getOperationList(@PathVariable @Parameter(description ="Идентификатор клиента") String client_id) throws Exception {
        return BankService.getOperationList(Long.parseLong(client_id),null,null);
    }
    @GetMapping("/transferMoney/{client_idf}/{client_idt}/{amount}")
    @Operation(summary = "Перевод средств между клиентами")
    public ResponseEntity<Respond> transferMoney(@PathVariable @Parameter(description ="Идентификатор клиента - плательщика") String client_idf,
                                                 @PathVariable @Parameter(description ="Идентификатор клиента - получателя") String client_idt,
                                                 @PathVariable @Parameter(description ="Сумма перевода") String amount) throws Exception {
        return BankService.transferMoney(Long.parseLong(client_idf),Long.parseLong(client_idt), new BigDecimal(amount));
    }

}
