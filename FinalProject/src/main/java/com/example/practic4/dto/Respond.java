package com.example.practic4.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Respond {

    @SerializedName(value = "result")
    @Expose(serialize = true,deserialize = true)
    BigDecimal result;

    @SerializedName(value = "message")
    @Expose(serialize = true,deserialize = true)
    String message;



}
