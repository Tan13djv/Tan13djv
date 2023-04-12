package com.example.practic4.service;

import com.example.practic4.dto.Respond;
import com.example.practic4.entity.Balance;
import com.example.practic4.repository.BalanceRepository;
import com.example.practic4.repository.TransactionRepository;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @InjectMocks
    private BankService bankService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private BalanceRepository balanceRepository;

    @BeforeEach
    public void beforeAll() {
        System.out.println(System.currentTimeMillis());
    }

    @AfterEach
    public void afterEach() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void takeMoney() {
        ResponseEntity<Respond> respond = bankService.putMoney(BigDecimal.valueOf(100),1L);
        System.out.println(respond.getBody().getMessage());
        Assertions.assertEquals(new BigDecimal(1),respond.getBody().getResult());
    }

    @Test
    public void putMoney() throws DatatypeConfigurationException {
        ResponseEntity<Respond> respond = bankService.putMoney(BigDecimal.valueOf(1000),1L);
        System.out.println(respond.getBody().getMessage());
        Assertions.assertEquals(new BigDecimal(1),respond.getBody().getResult());
    }

    @Test
    public void transferMoney() throws DatatypeConfigurationException {
        ResponseEntity<Respond> respond = bankService.transferMoney(1L,2L, BigDecimal.valueOf(50));
        System.out.println(respond.getBody().getMessage());
        Assertions.assertEquals(new BigDecimal(1),respond.getBody().getResult());
    }
}