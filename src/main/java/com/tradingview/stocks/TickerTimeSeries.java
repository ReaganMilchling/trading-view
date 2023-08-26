package com.tradingview.stocks;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TickerTimeSeries {
    private String ticker;
    private List<ZonedDateTime> time;
    private List<Long> volume;
    private List<Double> open;
    private List<Double> close;
    private List<Double> high;
    private List<Double> low;

    public TickerTimeSeries(String ticker) {
        this.ticker = ticker;
        this.time = new ArrayList<>();
        this.volume = new ArrayList<>();
        this.open = new ArrayList<>();
        this.close = new ArrayList<>();
        this.high = new ArrayList<>();
        this.low = new ArrayList<>();
    }

    public TickerTimeSeries(String ticker, List<ZonedDateTime> time, List<Long> volume, List<Double> open, List<Double> close, List<Double> high, List<Double> low) {
        this.ticker = ticker;
        this.time = time;
        this.volume = volume;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }

    public void addTime(ZonedDateTime zdt) {
        this.time.add(zdt);
    }

    public void addVolume(Long volume) {
        this.volume.add(volume);
    }

    public void addOpen(Double open) {
        this.open.add(open);
    }

    public void addClose(Double close) {
        this.close.add(close);
    }

    public void addHigh(Double high) {
        this.high.add(high);
    }

    public void addLow(Double low) {
        this.low.add(low);
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<ZonedDateTime> getTime() {
        return time;
    }

    public void setTime(List<ZonedDateTime> time) {
        this.time = time;
    }

    public List<Long> getVolume() {
        return volume;
    }

    public void setVolume(List<Long> volume) {
        this.volume = volume;
    }

    public List<Double> getOpen() {
        return open;
    }

    public void setOpen(List<Double> open) {
        this.open = open;
    }

    public List<Double> getClose() {
        return close;
    }

    public void setClose(List<Double> close) {
        this.close = close;
    }

    public List<Double> getHigh() {
        return high;
    }

    public void setHigh(List<Double> high) {
        this.high = high;
    }

    public List<Double> getLow() {
        return low;
    }

    public void setLow(List<Double> low) {
        this.low = low;
    }
}
