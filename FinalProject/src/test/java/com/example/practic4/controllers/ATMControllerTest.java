package com.example.practic4.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ATMControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void putMoneyTest() throws Exception {
        mockMvc.perform(get("/putMoney/1/1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value(new BigDecimal(1)))
                .andDo(print());
/*
        mockMvc.perform(get("/getBalance/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result").value(new BigDecimal(1000.00)))
                .andDo(print());

 */

    }

 }