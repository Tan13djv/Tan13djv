package com.example.practic4.service;

import com.example.practic4.entity.Income;
import com.example.practic4.entity.Spend;
import com.example.practic4.repository.IncomeRepository;
import com.example.practic4.repository.SpendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinanceService {

    static final String ADD_INCOME = "/addincome";
    static final String ADD_SPEND = "/addspend";
    static final String CURRENT_RATES = "/currentrates";

    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;


    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String message;
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            Income income = new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price));
            incomeRepository.save(income);
            message = "Доход в размере " + price + " был успешно добавлен";
        } else {
            Spend spend = new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price));
            spendRepository.save(spend);
            message = "Расход в размере " + price + " был успешно добавлен";
        }
        return message;
    }
}
