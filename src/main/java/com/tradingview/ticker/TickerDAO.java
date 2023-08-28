package com.tradingview.ticker;

import com.tradingview.mappers.TickerRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TickerDAO {

    Logger logger = LoggerFactory.getLogger(TickerDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    TickerDAO(JdbcTemplate jt, NamedParameterJdbcTemplate npjt) {
        this.jdbcTemplate = jt;
        this.namedParameterJdbcTemplate = npjt;
    }

    public List<Ticker> getLastDayOfAllTickers() {
        String sql = "select distinct on (ticker) ticker t, * from stocks_d order by ticker, time DESC";
        return jdbcTemplate.query(sql, new TickerRowMapper());
    }

    @Cacheable(value = "tickerList")
    public List<String> getTickers() {
        String sql = "SELECT DISTINCT ticker from stocks_d";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("ticker"));
    }

    @Cacheable(value = "relationalTickers", key = "#ticker")
    public List<Ticker> getAllAsTicker(String ticker) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker";

        return namedParameterJdbcTemplate.query(sql, namedParameters, new TickerRowMapper());
    }
}
