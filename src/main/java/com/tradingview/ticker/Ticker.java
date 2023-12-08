package com.tradingview.ticker;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Getter @Setter
public class Ticker implements Comparable<Ticker> {

    private String ticker;
    private Instant timestamp;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long volume;

    public Ticker() {
    }

    public Ticker(String ticker, Instant timestamp, Long volume, Double open, Double close, Double high, Double low) {
        this.ticker = ticker;
        this.timestamp = timestamp;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    @Override
    public int compareTo(Ticker other) {
        int tmp = this.getTicker().compareTo(other.getTicker());
        if (tmp == 0) {
            return this.getTimestamp().compareTo(other.getTimestamp());
        }
        return tmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticker ticker1 = (Ticker) o;
        return Objects.equals(ticker, ticker1.ticker) && Objects.equals(timestamp, ticker1.timestamp)
                && Objects.equals(open, ticker1.open) && Objects.equals(close, ticker1.close)
                && Objects.equals(high, ticker1.high) && Objects.equals(low, ticker1.low)
                && Objects.equals(volume, ticker1.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, timestamp, open, close, high, low, volume);
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "ticker='" + ticker + '\'' +
                ", timestamp=" + timestamp +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                '}';
    }
}
