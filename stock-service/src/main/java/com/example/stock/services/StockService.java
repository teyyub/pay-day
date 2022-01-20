package com.example.stock.services;

import com.example.stock.Stock;
import com.example.stock.repos.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository repository;

    public void add(Stock stock){
        repository.save(stock);
    }

    public List<Stock> allStock(){
        return repository.findAll();
    }
}
