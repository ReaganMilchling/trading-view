package com.tradingview.ticker.Mapper;

import com.tradingview.ticker.DTO.Ticker;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TickerRowMapper implements RowMapper<Ticker> {
    @Override
    public Ticker mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ticker(
                rs.getString("ticker"),
                rs.getTimestamp("time").toInstant(),
                rs.getLong("volume"),
                rs.getDouble("open"),
                rs.getDouble("close"),
                rs.getDouble("high"),
                rs.getDouble("low")
        );
    }
}