package com.tradingview.ticker.Entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class StockId implements Serializable, Comparable<StockId> {
    private LocalDateTime time;
    private String ticker;

    public StockId() {
    }

    public StockId(LocalDateTime timestamp, String ticker) {
        this.time = timestamp;
        this.ticker = ticker;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public int compareTo(StockId stockId) {
        int tmp = this.getTicker().compareTo(stockId.getTicker());
        if (tmp == 0) {
            return this.getTime().compareTo(stockId.getTime());
        }
        return tmp;
    }
}
