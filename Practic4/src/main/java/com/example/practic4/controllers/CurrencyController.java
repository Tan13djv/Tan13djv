package com.example.practic4.controllers;

import com.example.practic4.dto.CentralRussianBankService;
import com.example.practic4.dto.ValuteCursOnDate;
import com.example.practic4.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;
    private final StatService statService;

    @GetMapping("/getCurrencies")

  //  @ApiOperation(value = "Получение курса всех валют на текущий день")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }

    @GetMapping("/getCurrency/{code}")

  //  @ApiOperation(value = "Получение курса определенно валюты на текущий день")
    public ValuteCursOnDate getCourseForCurrency(@PathVariable String code) throws Exception {
        return centralRussianBankService.getCourseForCurrency(code);
    }
    @GetMapping("/getStats")
    public int getCountOfIncomesThatGreater(@RequestParam(value = "amount")BigDecimal amount){
        return statService.getCountOfIncomesThatGreaterThan(amount);
    }
}