package com.tradingview.timeseries;

import com.tradingview.ticker.Ticker;
import com.tradingview.ticker.TickerDAO;
import com.tradingview.ticker.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSeriesService {

    Logger logger = LoggerFactory.getLogger(TickerService.class);
    private final TimeSeriesDAO timeSeriesDAO;
    private final TickerDAO tickerDAO;
    private final TickerService tickerService;

    @Autowired
    public TimeSeriesService(TimeSeriesDAO ts, TickerDAO tr, TickerService tickerService) {
        this.timeSeriesDAO = ts;
        this.tickerDAO = tr;
        this.tickerService = tickerService;
    }

    public TimeSeriesTicker getAllTickerSorted(String ticker) {
        List<Ticker> list = tickerService.getAllAsTicker(ticker);
        TimeSeriesTicker tts = new TimeSeriesTicker(ticker);

        for (Ticker item : list) {
            tts.addTicker(item);
        }

        return tts;
    }

    public TimeSeriesTicker getDataByTicker(String ticker) {
        List<String> tickerlist = tickerDAO.getTickers();

        if (!tickerlist.contains(ticker)) {
            return null;
        }

        return timeSeriesDAO.findDataByTicker(ticker);
    }

    public TimeSeriesTicker getDataByTickerFromDate(String ticker, LocalDate date) {
        Timestamp time = Timestamp.valueOf(date.atStartOfDay());
        return timeSeriesDAO.getDataByTickerFromTimestamp(ticker, time);
    }

    public TimeSeriesTicker getDataByTickerBeforeDate(String ticker, LocalDate date) {
        Timestamp time = Timestamp.valueOf(date.atStartOfDay());
        return timeSeriesDAO.getDataByTickerBeforeTimestamp(ticker, time);
    }
}
