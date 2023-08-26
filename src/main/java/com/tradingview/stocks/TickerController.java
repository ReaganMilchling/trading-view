package com.tradingview.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<List<Ticker>> getLastDayofAllTickers() {
        return new ResponseEntity<>(tickerService.getLastDayofAllTickers(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllTickers() {
        return new ResponseEntity<>(tickerService.getTickers(), HttpStatus.OK);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<TickerTimeSeries> getDataByTicker(
            @PathVariable("ticker") String ticker
    ) {
        System.out.println(ticker);
        return new ResponseEntity<>(tickerService.getDataByTicker(ticker), HttpStatus.OK);
    }

    @GetMapping("/{ticker}/from/{date}")
    public ResponseEntity<TickerTimeSeries> getDataByTickerFromDate(
            @PathVariable("ticker") String ticker,
            @PathVariable("date") String date
    ) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        TickerTimeSeries tickerList = tickerService.getDataByTickerFromDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @GetMapping("/{ticker}/before/{date}")
    public ResponseEntity<TickerTimeSeries> getDataByTickerBeforeDate(
            @PathVariable("ticker") String ticker,
            @PathVariable("date") String date
    ) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        TickerTimeSeries tickerList = tickerService.getDataByTickerBeforeDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ticker}")
    public ResponseEntity<?> deleteTickByTicker(
            @PathVariable String ticker
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/upload/yahoo")
    public void upload(JsonNode node) {
        tickerService.getTickerFromJSON(node);
    }

    @PostMapping("/upload/schwab")
    public void uploadSchwab(JsonNode node) {
        tickerService.getTickerFromJSONSchwab(node);
    }

}
