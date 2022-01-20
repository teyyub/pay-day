package com.example.stock;

import com.example.stock.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class StockServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServiceApplication.class, args);
//		Timer timer = new Timer();
//		timer.schedule(new StockController(), 0, 5000);
	}

}
