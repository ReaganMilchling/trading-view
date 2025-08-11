package com.tradingview.ticker;

import com.fasterxml.jackson.databind.JsonNode;
import com.tradingview.ticker.Entity.Stock;
import com.tradingview.ticker.Repository.TickerRepository;
import com.tradingview.ticker.DTO.Ticker;
import com.tradingview.ticker.DTO.TickerDateRange;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TickerService {

    Logger logger = LoggerFactory.getLogger(TickerService.class);
    private final TickerDAO tickerDAO;
    private final EntityManager entityManager;
    private final TickerRepository tickerRepository;

    @Autowired
    public TickerService(TickerDAO tickerDAO, EntityManager entityManager, TickerRepository tickerRepository) {
        this.tickerDAO = tickerDAO;
        this.entityManager = entityManager;
        this.tickerRepository = tickerRepository;
    }

    public List<TickerDateRange> getDateRange() {
        return tickerDAO.getTickerDateRange();
    }

    public List<Ticker> getLastDayOfAllTickers() {
        return tickerDAO.getLastDayOfAllTickers();
    }

    public List<Count> getCountOfTickers() {
        return tickerDAO.getCountOfTickers();
    }

    public List<Ticker> getAllByTicker(String ticker) {
        return tickerRepository.findAllByIdTickerOrderByIdTimeDesc(ticker)
                .stream()
                .map(Ticker::new)
                .toList();
    }

    public List<Stock> getTopTimeByTicker(String ticker) {
        return tickerRepository.findTopIdTimeByIdTicker(ticker);
    }

    public List<Ticker> getAllAsTicker(String ticker) {
        List<Ticker> list = tickerDAO.getAllAsTicker(ticker);
        Collections.sort(list);
        return list;
    }

    public List<String> getTickers() {
        return tickerDAO.getTickers();
    }

    public void getTickerFromJSON(JsonNode node) {
        List<Ticker> ticks = new ArrayList<>();
//            JsonNode node = new ObjectMapper().readTree(new File("src/main/resources/" + fileName + ".json"));
        int len = node.get("result").get(0).get("timestamp").size();

        for (int i = 0; i < len; i++) {
            Ticker data = new Ticker();
            data.setTicker(node.get("result").get(0).get("meta").get("symbol").textValue());
            Instant instant = Instant.ofEpochSecond(node.get("result").get(0).get("timestamp").get(i).longValue());
            data.setTimestamp(Instant.parse(node.get("result").get(0).get("meta").get("exchangeTimezoneName").textValue()));
            data.setOpen(node.get("result").get(0).get("indicators").get("quote").get(0).get("open").get(i).doubleValue());
            data.setClose(node.get("result").get(0).get("indicators").get("quote").get(0).get("close").get(i).doubleValue());
            data.setHigh(node.get("result").get(0).get("indicators").get("quote").get(0).get("high").get(i).doubleValue());
            data.setLow(node.get("result").get(0).get("indicators").get("quote").get(0).get("low").get(i).doubleValue());
            data.setVolume((long) node.get("result").get(0).get("indicators").get("quote").get(0).get("volume").get(i).intValue());

            ticks.add(data);
        }
        //tickerRepository.saveAll(ticks);
    }

    public void getTickerFromJSONSchwab(JsonNode node) {
        List<Ticker> ticks = new ArrayList<>();
//            JsonNode node = new ObjectMapper().readTree(new File("src/main/resources/" + fileName + ".json"));
        int len = node.get("Dates").size();

        for (int i = 0; i < len; i++) {
            Ticker data = new Ticker();

            data.setTicker("SPY");
            ZonedDateTime zdt = LocalDateTime.parse(node.get("Dates").get(i).textValue()).atZone(ZoneId.of("America/New_York"));
            data.setTimestamp(zdt.toInstant());
            data.setOpen((double) (node.get("Elements").get(0).get("ComponentSeries").get(0).get("Values").get(i).longValue()/10f));
            data.setClose((double) (node.get("Elements").get(0).get("ComponentSeries").get(3).get("Values").get(i).longValue()/10f));
            data.setLow((double) (node.get("Elements").get(0).get("ComponentSeries").get(2).get("Values").get(i).longValue()/10f));
            data.setHigh((double) (node.get("Elements").get(0).get("ComponentSeries").get(1).get("Values").get(i).longValue()/10f));
            data.setVolume((long) node.get("Elements").get(1).get("ComponentSeries").get(0).get("Values").get(i).intValue());

            ticks.add(data);
        }
        //tickerRepository.saveAll(ticks);
    }

}
