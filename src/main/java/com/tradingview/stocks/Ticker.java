package com.tradingview.stocks;

import java.time.ZonedDateTime;

public class Ticker {

    private String ticker;
    private ZonedDateTime time;

    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long volume;

    public Ticker() {
    }

    public Ticker(String ticker, ZonedDateTime timestamp, Long volume, Double open, Double close, Double high, Double low) {
        this.ticker = ticker;
        this.time = timestamp;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "ticker='" + ticker + '\'' +
                ", timestamp=" + time +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}
