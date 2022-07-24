package com.TradingView.common;

import com.TradingView.entity.Ticker;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZonedDateTime.now;

@CrossOrigin("*")
@Component
@RestController
@RequestMapping("/ticker")
public class TickerController {

    private final TickerService tickerService;

    @Autowired
    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<Ticker>> getgetLastDayofAllTickers() {
        System.out.println("access default controller");
        List<Ticker> tickerList = tickerService.getLastDayofAllTickers();
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @GetMapping("/tickers")
    public ResponseEntity<List<Ticker>> getTickers() {
        return new ResponseEntity<>(tickerService.getAllTickers(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticker>> getAllTickers() {
        return new ResponseEntity<>(tickerService.getAllTickers(), HttpStatus.OK);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<List<Ticker>> getDataByTicker(@PathVariable("ticker") String ticker) {
        List<Ticker> tickerList = tickerService.getDataByTicker(ticker);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @GetMapping("/{ticker}/from/{date}")
    public ResponseEntity<List<Ticker>> getDataByTickerFromDate(@PathVariable("ticker") String ticker,
                                                               @PathVariable("date") String date) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        List<Ticker> tickerList = tickerService.getDataByTickerFromDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @GetMapping("/{ticker}/before/{date}")
    public ResponseEntity<List<Ticker>> getDataByTickerBeforeDate(@PathVariable("ticker") String ticker,
                                                                @PathVariable("date") String date) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        List<Ticker> tickerList = tickerService.getDataByTickerBeforeDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ticker}")
    public ResponseEntity<?> deleteTickByTicker(@PathVariable String ticker) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
