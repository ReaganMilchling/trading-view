package com.TradingView.common;

import com.TradingView.entity.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin("*")
@Component
@RestController
@RequestMapping("/")
public class DefaultController {

    private final TickerService tickerService;

    @Autowired
    public DefaultController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping("default")
    public ResponseEntity<List<Ticker>> getgetLastDayofAllTickers() {
        System.out.println("access default controller");
        List<Ticker> tickerList = tickerService.getLastDayofAllTickers();
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }
}
