package com.tradingview.ticker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TickerDateRange {
    private String ticker;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "UTC")
    private LocalDateTime earliest;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "UTC")
    private LocalDateTime latest;
    private String difference;

    public TickerDateRange() {
    }

    public TickerDateRange(String ticker, LocalDateTime earliest, LocalDateTime latest) {
        this.ticker = ticker;
        this.earliest = earliest;
        this.latest = latest;
        this.difference = DurationFormatUtils.formatDurationISO(Duration.between(earliest, latest).toMillis());
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LocalDateTime getEarliest() {
        return earliest;
    }

    public void setEarliest(LocalDateTime earliest) {
        this.earliest = earliest;
    }

    public LocalDateTime getLatest() {
        return latest;
    }

    public void setLatest(LocalDateTime latest) {
        this.latest = latest;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }
}
