package com.example.practic4.entity;

import com.example.practic4.enums.Direction;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@Schema(description = "Сущность транзакция")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonIgnore
    private long id;


    @Column(name = "CLIENT_ID")
    private long client_id;

    @Column(name = "DATE_OP")
    @SerializedName(value = "date_op")
    @Expose(serialize = true,deserialize = true)
    @JsonFormat(pattern="dd-MM-YYYY HH:mm:ss")
    private Timestamp datetime_op;

    @Column(name = "DIRECTION")
    @SerializedName(value = "direction")
    @Expose(serialize = true,deserialize = true)
    Direction direction;


    @Column(name = "AMOUNT")
    @SerializedName(value = "amount")
    @Expose(serialize = true,deserialize = true)
    private BigDecimal amount;




}
