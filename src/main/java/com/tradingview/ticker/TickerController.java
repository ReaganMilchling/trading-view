package com.tradingview.ticker;

import com.fasterxml.jackson.databind.JsonNode;
import com.tradingview.ticker.Entity.Stock;
import com.tradingview.ticker.DTO.Ticker;
import com.tradingview.ticker.DTO.TickerDateRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.ZonedDateTime.now;

@Component
@RestController
@RequestMapping("/ticker")
public class TickerController {

    Logger logger = LoggerFactory.getLogger(TickerController.class);
    private final TickerService tickerService;

    @Autowired
    public TickerController(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<Ticker>> getLastDayofAllTickers() {
        return new ResponseEntity<>(tickerService.getLastDayOfAllTickers(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<List<Count>> getCountOfTickers() {
        return new ResponseEntity<>(tickerService.getCountOfTickers(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllTickers() {
        return new ResponseEntity<>(tickerService.getTickers(), HttpStatus.OK);
    }

    @GetMapping("/relational/{ticker}")
    public ResponseEntity<List<Ticker>> getAllAsTicker(
            @PathVariable("ticker") String ticker
    ) {
        return new ResponseEntity<>(tickerService.getAllByTicker(ticker), HttpStatus.OK);
    }

    @GetMapping("/max-date/{ticker}")
    public ResponseEntity<List<Stock>> getTopByTicker(
            @PathVariable("ticker") String ticker
    ) {
        return new ResponseEntity<>(tickerService.getTopTimeByTicker(ticker), HttpStatus.OK);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<TickerDateRange>> getDateRange() {
        return new ResponseEntity<>(tickerService.getDateRange(), HttpStatus.OK);
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
