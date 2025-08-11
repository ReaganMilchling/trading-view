package com.tradingview.ticker.Mapper;

import com.tradingview.ticker.DTO.TickerDateRange;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TickerDateRangeMapper implements RowMapper<TickerDateRange> {
    @Override
    public TickerDateRange mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TickerDateRange(
                rs.getString("ticker"),
                rs.getTimestamp("earliest").toLocalDateTime(),
                rs.getTimestamp("latest").toLocalDateTime()
        );
    }
}
