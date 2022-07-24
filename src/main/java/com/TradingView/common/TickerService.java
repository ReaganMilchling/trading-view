package com.TradingView.common;

import com.TradingView.entity.Ticker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TickerService {

    private final TickerRepository tickerRepository;

    @Autowired
    public TickerService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public List<Ticker> getAllTickers() {
        return tickerRepository.findAll();
    }

    public List<Ticker> getDataByTicker(String ticker) {
        return tickerRepository.findDataByTicker(ticker);
    }

    public List<Ticker> saveAll(List<Ticker> data) {
        return tickerRepository.saveAll(data);
    }

    public List<Ticker> getDataByTickerFromDate(String ticker, LocalDate date) {
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.systemDefault());
        return tickerRepository.getDataByTickerFromTimestamp(ticker, zdt);
    }

    public List<Ticker> getDataByTickerBeforeDate(String ticker, LocalDate date) {
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.systemDefault());
        return tickerRepository.getDataByTickerBeforeTimestamp(ticker, zdt);
    }

    public List<Ticker> getLastDayofAllTickers() {
        return tickerRepository.getLastDayOfAllTickers();
    }

    public List<Ticker> getTickers() {
        return tickerRepository.getTickers();
    }
}
