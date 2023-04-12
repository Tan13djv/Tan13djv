package com.example.practic4.enums;

public enum Direction {
    DEBET("Списание"),
    CREDIT("Зачисление"),
    INPUT("Входящий перевод"),
    OUTPUT("Исходящий перевод");
    String directionName;
    Direction(String rus){directionName = rus;}
    public String getDirectionName() {return directionName;}
}
