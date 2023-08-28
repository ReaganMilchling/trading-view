package com.tradingview.ticker;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.ZonedDateTime.now;

@CrossOrigin("*")
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
        return new ResponseEntity<>(tickerService.getLastDayofAllTickers(), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllTickers() {
        return new ResponseEntity<>(tickerService.getTickers(), HttpStatus.OK);
    }

    @GetMapping("/relational/{ticker}")
    public ResponseEntity<List<Ticker>> getAllAsTicker(
            @PathVariable("ticker") String ticker
    ) {
        return new ResponseEntity<>(tickerService.getAllAsTicker(ticker), HttpStatus.OK);
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
