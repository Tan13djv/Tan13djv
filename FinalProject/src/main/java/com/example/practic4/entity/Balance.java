package com.example.practic4.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "BALANCE")
@Data
@Schema(description = "Сущность баланс")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLIENT_ID")
    private long client_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<Transaction> transactions;

    @Column(name = "AMOUNT")
    private BigDecimal amount;


}

