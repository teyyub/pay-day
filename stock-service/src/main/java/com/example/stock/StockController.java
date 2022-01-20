package com.example.stock;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/stocks")
public class StockController extends TimerTask {
    Map<String, Stock > stocks = new HashMap<>();
    public StockController(){
        stocks.put("EU",new Stock("EU",new BigDecimal(15),1));
        stocks.put("AZ",new Stock("AZ",new BigDecimal(25),1));
        stocks.put("GB",new Stock("GB",new BigDecimal(15),1));

    }

    @Override
    public void run() {
        System.out.println("timer");
        Object[] crunchifyKeys = stocks.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        int v = new Random().nextInt(10)+1;
        System.out.println("v " +v);
        BigDecimal val= stocks.get(key).getValue();
        stocks.get(key).setValue(new BigDecimal(v).add(val));
        System.out.println(stocks.get(key));

    }

    @GetMapping
    public ResponseEntity<?> getStock(@RequestParam String query ){
        System.out.println("2.. " +query);
        Stock s = stocks.get(query);
        System.out.println("1.... "+s);
        return ResponseEntity.ok(s);
    }



}
