package com.example.stock.controller;

import com.example.stock.domain.Stock;
import com.example.stock.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;


/**
 *
 * @author anar
 *
 */
@RestController
@RequestMapping("/stocks")
public class StockController extends TimerTask {

     Map<String, Stock> stocks = new HashMap<>();
    private final StockService service;
    public StockController(StockService service){
        this.service = service;
        stocks.put("EU",new Stock( "AZERIQAZ","EU",new BigDecimal("4.567"),null,null,new Date()));
        stocks.put("AZ",new Stock( "AZERCEL","AZ",new BigDecimal("5.678"),null,null, new Date()));
        stocks.put("GB",new Stock( "YAHOO","GB",new BigDecimal("5.789"),null,null,new Date()));

        System.out.println("service= " +service);
        service.add(stocks.get("EU"));
        service.add(stocks.get("AZ"));
        service.add(stocks.get("GB"));


    }
    @PostConstruct
    void init(){

        service.add(stocks.get("EU"));
    }

    @GetMapping("/operate")
    public void operation(){

        List<Stock> stocksList = service.allStock();
        Map<String, Stock > stocksMap = new HashMap<>();
        stocksList.forEach(it->{
            stocksMap.put(it.getSymbol(),it);
        });
        Object[] crunchifyKeys = stocksMap.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        int v = new Random().nextInt(10)+1;

        if(v>=5) v= -v;

        Stock stock =stocksMap.get(key);

        BigDecimal val= stock.getLastPrice();

        stock.setLastPrice(new BigDecimal(v).add(val));

        stock.setChange(val);
        Float changePercent=null;
        BigDecimal firstPrice=   stock.getLastPrice().subtract(stock.getChange());
        changePercent= stock.getChange().divide(firstPrice, MathContext.DECIMAL128).multiply(new BigDecimal(100)).floatValue();
        stock.setChangePercent(changePercent);

        service.add(stock);
    }

    @Override
    public void run() {
        System.out.println("timer 11111");
//        Object[] crunchifyKeys = stocks.keySet().toArray();
//        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
//        int v = new Random().nextInt(10)+1;
//
//        if(v>=5) v= -v;
//
//        Stock stock =stocks.get(key);
//
//        BigDecimal val= stock.getLastPrice();
//
//        stock.setLastPrice(new BigDecimal(v).add(val));
//
//        stock.setChange(val);
//        Float changePercent=null;
//        BigDecimal firstPrice=   stocks.get(key).getLastPrice().subtract(stocks.get(key).getChange());
//        changePercent= stocks.get(key).getChange().divide(firstPrice, MathContext.DECIMAL128).multiply(new BigDecimal(100)).floatValue();
//        stocks.get(key).setChangePercent(changePercent);
//
//        service.add(stock);
//
    }

    @GetMapping
    public ResponseEntity<?> getStock(@RequestParam String query ){
        System.out.println("stocks="+stocks);
        System.out.println("2.. " +query);
        Stock s = stocks.get(query);
        System.out.println("1.... "+s);
        return ResponseEntity.ok(s);
    }



}
