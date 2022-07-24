package com.TradingView.common;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TickerID implements Serializable {
    private String ticker;
    private ZonedDateTime timestamp;

    public TickerID() {

    }

    public TickerID(String ticker, ZonedDateTime timestamp) {
        this.ticker = ticker;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TickerID tickerID = (TickerID) o;
        return Objects.equals(ticker, tickerID.ticker) && Objects.equals(timestamp, tickerID.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker, timestamp);
    }
}
