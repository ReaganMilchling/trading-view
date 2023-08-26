package com.tradingview.stocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class TickerRepository {

    Logger logger = LoggerFactory.getLogger(TickerRepository.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    TickerRepository(JdbcTemplate jt, NamedParameterJdbcTemplate npjt) {
        this.jdbcTemplate = jt;
        this.namedParameterJdbcTemplate = npjt;
    }

    @Cacheable(value = "ticker", key = "#ticker")
    TickerTimeSeries findDataByTicker(String ticker) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }

    @Cacheable(value = "tickerFromTime", key = "#ticker + #zdt")
    TickerTimeSeries getDataByTickerFromTimestamp(String ticker, Timestamp timestamp) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker)
                .addValue("time", timestamp);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker AND time >= :time";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }

    @Cacheable(value = "tickerBeforeTime", key = "#ticker + #zdt")
    TickerTimeSeries getDataByTickerBeforeTimestamp(String ticker, Timestamp timestamp) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker)
                .addValue("time", timestamp);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker AND time <= :time";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new TickerTimeSeriesRowMapper());
    }

    List<Ticker> getLastDayOfAllTickers() {
        String sql = "select distinct on (ticker) ticker t, * from stocks_d order by ticker, time DESC";
        return jdbcTemplate.query(sql, new TickerRowMapper());
    }

    List<String> getTickers() {
        String sql = "SELECT DISTINCT ticker from stocks_d";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("ticker"));
    }

    public static class TickerRowMapper implements RowMapper<Ticker> {
        @Override
        public Ticker mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Ticker(
                    rs.getString("ticker"),
                    rs.getTimestamp("time").toInstant().atZone(ZoneId.of("UTC")),
                    rs.getLong("volume"),
                    rs.getDouble("open"),
                    rs.getDouble("close"),
                    rs.getDouble("high"),
                    rs.getDouble("low")
            );
        }
    }

    public static class TickerTimeSeriesRowMapper implements RowMapper<TickerTimeSeries> {
        @Override
        public TickerTimeSeries mapRow(ResultSet rs, int rowNum) throws SQLException {
            TickerTimeSeries tts = new TickerTimeSeries(rs.getString("ticker"));
            tts.addTime(rs.getTimestamp("time").toInstant().atZone(ZoneId.of("UTC")));
            tts.addVolume(rs.getLong("volume"));
            tts.addOpen(rs.getDouble("open"));
            tts.addClose(rs.getDouble("close"));
            tts.addHigh(rs.getDouble("high"));
            tts.addLow(rs.getDouble("low"));

            while(rs.next()) {
                tts.addTime(rs.getTimestamp("time").toInstant().atZone(ZoneId.of("UTC")));
                tts.addVolume(rs.getLong("volume"));
                tts.addOpen(rs.getDouble("open"));
                tts.addClose(rs.getDouble("close"));
                tts.addHigh(rs.getDouble("high"));
                tts.addLow(rs.getDouble("low"));
            }

            return tts;
        }
    }
}
