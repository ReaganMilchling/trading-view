package com.TradingView;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TradingViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingViewApplication.class, args);
    }

}
