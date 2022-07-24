package com.TradingView.entity;

import com.TradingView.common.TickerID;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@IdClass(TickerID.class)
public class Ticker {

    @Id
    @Column(nullable = false, updatable = false)
    private String ticker;
    @Id
    @Column(nullable = false, updatable = false)
    private ZonedDateTime timestamp;

    private String dataGranularity;
    private double open;
    private double close;
    private double high;
    private double low;
    private int volume;

    public Ticker() {
    }

    public Ticker(String ticker, ZonedDateTime timestamp, String dataGranularity, double open, double close, double high, double low, int volume) {
        this.ticker = ticker;
        this.timestamp = timestamp;
        this.dataGranularity = dataGranularity;
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

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDataGranularity() {
        return dataGranularity;
    }

    public void setDataGranularity(String dataGranularity) {
        this.dataGranularity = dataGranularity;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "ticker='" + ticker + '\'' +
                ", timestamp=" + timestamp +
                ", dataGranularity='" + dataGranularity + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}
