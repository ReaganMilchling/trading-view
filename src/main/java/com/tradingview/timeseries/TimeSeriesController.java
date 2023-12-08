package com.tradingview.timeseries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Component
@RestController
@RequestMapping("/time-series")
public class TimeSeriesController {

    Logger logger = LoggerFactory.getLogger(TimeSeriesController.class);
    private final TimeSeriesService timeSeriesService;

    @Autowired
    public TimeSeriesController(TimeSeriesService tts) {
        this.timeSeriesService = tts;
    }

    @GetMapping("/sorted/{ticker}")
    public ResponseEntity<TimeSeriesTicker> getAllTickerSorted(
            @PathVariable("ticker") String ticker
    ) {
        //this exists just because I haven't figured out how to sort in timescaledb yet
        return new ResponseEntity<>(timeSeriesService.getAllTickerSorted(ticker), HttpStatus.OK);
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<TimeSeriesTicker> getDataByTicker(
            @PathVariable("ticker") String ticker
    ) {
        return new ResponseEntity<>(timeSeriesService.getDataByTicker(ticker), HttpStatus.OK);
    }

    @GetMapping("/{ticker}/from/{date}")
    public ResponseEntity<TimeSeriesTicker> getDataByTickerFromDate(
            @PathVariable("ticker") String ticker,
            @PathVariable("date") String date
    ) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        TimeSeriesTicker tickerList = timeSeriesService.getDataByTickerFromDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

    @GetMapping("/{ticker}/before/{date}")
    public ResponseEntity<TimeSeriesTicker> getDataByTickerBeforeDate(
            @PathVariable("ticker") String ticker,
            @PathVariable("date") String date
    ) {
        //local date is format YYYY-MM-DD
        LocalDate newDate = LocalDate.parse(date);
        TimeSeriesTicker tickerList = timeSeriesService.getDataByTickerBeforeDate(ticker, newDate);
        return new ResponseEntity<>(tickerList, HttpStatus.OK);
    }

}
