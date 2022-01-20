package com.example.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

//    @JsonProperty("Status")
//    private String status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @JsonProperty("Name")
    private String name;

//    @JsonProperty("Symbol")
    private String symbol;
//    @JsonProperty("LastPrice")
    private BigDecimal lastPrice;
//    @JsonProperty("Change")
    private BigDecimal change;
//    @JsonProperty("ChangePercent")
    private Float changePercent;
//    @JsonProperty("Timestamp")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd HH:mm:ss zzzXXX yyyy", locale="ENGLISH")
    private Date actionDate;

    public Stock(String name, String symbol, BigDecimal lastPrice, BigDecimal change, Float changePercent, Date date) {
        this.name = name;
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.change = change;
        this.changePercent = changePercent;
         this.actionDate = date;

    }

//    @JsonProperty("MSDate")
//    private Float mSDate;
//    @JsonProperty("MarketCap")
//    private Float marketCap;
//    @JsonProperty("Volume")
//    private Integer volume;
//    @JsonProperty("ChangeYTD")
//    private Float changeYTD;
//    @JsonProperty("ChangePercentYTD")
//    private Float changePercentYTD;
//    @JsonProperty("High")
//    private BigDecimal high;
//    @JsonProperty("Low")
//    private BigDecimal low;
//    @JsonProperty("Open")
//    private BigDecimal open;



}
