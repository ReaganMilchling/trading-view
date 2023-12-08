package com.tradingview.mappers;

import com.tradingview.ticker.Ticker;
import com.tradingview.timeseries.TimeSeriesTicker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TickerTimeSeriesRowMapper implements RowMapper<TimeSeriesTicker> {
    @Override
    public TimeSeriesTicker mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimeSeriesTicker tts = new TimeSeriesTicker(rs.getString("ticker"));

        Ticker ticker = new TickerRowMapper().mapRow(rs, 1);
        assert ticker != null;
        tts.addTicker(ticker);

        while(rs.next()) {
            ticker = new TickerRowMapper().mapRow(rs, 1);
            assert ticker != null;
            tts.addTicker(ticker);
        }

        return tts;
    }
}