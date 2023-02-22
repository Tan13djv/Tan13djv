package com.example.practic4.repository;

import com.example.practic4.Practic4Application;
import com.example.practic4.entity.Income;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class IncomeRepositoryTest {

    @Autowired
    private IncomeRepository incomeRepository;
/*
    @Test
    public void testRepo() {
        //noinspection StatementWithEmptyBody
        for (int i = 0; i < 10; i++, incomeRepository.save(new Income()));
        final List<Income> found = incomeRepository.findAll();
        assertEquals(10, found.size());
    }

 */
    @Test
    public void testDataScripts(){
        /*Income tst = new Income();
        tst.setId(65456L);
        tst.setChatId(12345L);
        tst.setIncome(BigDecimal.valueOf(3000));
        incomeRepository.save(tst);

         */
        Optional<Income> income = incomeRepository.findById(65456L);
        assertTrue(income.isPresent());
        assertEquals(new BigDecimal(3000),income.get().getIncome());

    }
}