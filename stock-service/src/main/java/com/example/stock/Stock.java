package com.example.stock;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Stock {
    private String code;
    private BigDecimal value;
    private int index;
    public Stock(){}
    public Stock(String code, BigDecimal value, int ind) {
        this.code = code;
        this.value=value;
        this.index = ind;
    }

}
