package com.tradingview.ticker;

import com.fasterxml.jackson.databind.JsonNode;
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
    private final TickerDAO tickerRepository;

    @Autowired
    public TickerService(TickerDAO tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public List<Ticker> getLastDayofAllTickers() {
        return tickerRepository.getLastDayOfAllTickers();
    }

    public List<Ticker> getAllAsTicker(String ticker) {
        List<Ticker> list = tickerRepository.getAllAsTicker(ticker);
        Collections.sort(list);
        logger.info("Returning {} sorted records for {}", list.size(), ticker);

        return list;
    }

    public List<String> getTickers() {
        return tickerRepository.getTickers();
    }

    public void getTickerFromJSON(JsonNode node) {
        List<Ticker> ticks = new ArrayList<>();
//            JsonNode node = new ObjectMapper().readTree(new File("src/main/resources/" + fileName + ".json"));
        int len = node.get("result").get(0).get("timestamp").size();

        for (int i = 0; i < len; i++) {
            Ticker data = new Ticker();
            data.setTicker(node.get("result").get(0).get("meta").get("symbol").textValue());
            Instant instant = Instant.ofEpochSecond(node.get("result").get(0).get("timestamp").get(i).longValue());
            data.setTime(Instant.parse(node.get("result").get(0).get("meta").get("exchangeTimezoneName").textValue()));
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
            data.setTime(zdt.toInstant());
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
