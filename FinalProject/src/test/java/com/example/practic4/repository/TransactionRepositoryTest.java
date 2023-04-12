package com.example.practic4.repository;

import com.example.practic4.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@DataJpaTest
@RunWith(SpringRunner.class)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;
    @Test
    public void testRepo(){
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setClient_id(1L);
        transaction.setDatetime_op(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);
        Optional<List<Transaction>> transaction1 = Optional.of(transactionRepository.findAll());
        assertTrue(transaction1.isPresent());
    }

}