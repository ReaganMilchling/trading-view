package com.tradingview.timeseries;

import com.tradingview.mappers.TickerTimeSeriesRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimeSeriesDAO {
    Logger logger = LoggerFactory.getLogger(TimeSeriesDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    TimeSeriesDAO(JdbcTemplate jt, NamedParameterJdbcTemplate npjt) {
        this.jdbcTemplate = jt;
        this.namedParameterJdbcTemplate = npjt;
    }

    @Cacheable(value = "ticker", key = "#ticker")
    public TickerTimeSeries findDataByTicker(String ticker) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }

    @Cacheable(value = "tickerFromTime", key = "#ticker + #zdt")
    public TickerTimeSeries getDataByTickerFromTimestamp(String ticker, Timestamp timestamp) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker)
                .addValue("time", timestamp);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker AND time >= :time";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }

    @Cacheable(value = "tickerBeforeTime", key = "#ticker + #zdt")
    public TickerTimeSeries getDataByTickerBeforeTimestamp(String ticker, Timestamp timestamp) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker)
                .addValue("time", timestamp);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker AND time <= :time";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }
}
