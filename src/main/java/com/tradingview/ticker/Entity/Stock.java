package com.tradingview.ticker.Entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonSubTypes({
        @JsonSubTypes.Type(value = StockId.class, name = "id"),
})
@Entity
@Table(name = "stocks_d")
public class Stock implements Comparable<Stock> {
    @Id
    private StockId id;
    private Long volume;
    private Double open;
    private Double close;
    private Double high;
    private Double low;

    public Stock() {
    }

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
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

    @Override
    public int compareTo(Stock stock) {
        return this.getId().compareTo(stock.getId());
    }
}
