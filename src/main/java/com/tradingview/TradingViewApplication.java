package com.tradingview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan
@PropertySource("classpath:application.properties")
public class TradingViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingViewApplication.class, args);
    }
}
