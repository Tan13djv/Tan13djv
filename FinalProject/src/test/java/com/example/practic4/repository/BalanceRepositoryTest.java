package com.example.practic4.repository;

import com.example.practic4.entity.Balance;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BalanceRepositoryTest {

    @Autowired
    private BalanceRepository balanceRepository;
    @Test
    public void testRepo(){
        Balance balance = new Balance();
        balance.setId(1L);
        balance.setAmount(BigDecimal.valueOf(1000));
        balanceRepository.save(balance);
        Optional<Balance> balance1 = balanceRepository.findById(1L);
        assertTrue(balance1.isPresent());
        //System.out.println("balance1.isPresent() = "+balance1.isPresent());
        assertEquals(new BigDecimal(1000), balance1.get().getAmount());
        //System.out.println("balance1.get().getAmount() = "+balance1.get().getAmount());
    }
}