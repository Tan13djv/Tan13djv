package com.example.practic4.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "INCOMES")
public class Income {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "INCOME")
    private BigDecimal income;


    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
