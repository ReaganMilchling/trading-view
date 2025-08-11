package com.tradingview.ticker;

import com.tradingview.ticker.Mapper.TickerDateRangeMapper;
import com.tradingview.ticker.Mapper.TickerRowMapper;
import com.tradingview.ticker.DTO.Ticker;
import com.tradingview.ticker.DTO.TickerDateRange;
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

    public List<TickerDateRange> getTickerDateRange() {
        String sql = "select max(time) as latest, min(time) as earliest, ticker from stocks_d group by ticker order by ticker";
        return jdbcTemplate.query(sql, new TickerDateRangeMapper());
    }

    @Cacheable(value = "relationalTickers", key = "#ticker")
    public List<Ticker> getAllAsTicker(String ticker) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ticker", ticker);
        String sql = "SELECT * FROM stocks_d WHERE ticker = :ticker";

        return namedParameterJdbcTemplate.query(sql, namedParameters, new TickerRowMapper());
    }

    public List<Count> getCountOfTickers() {
        String sql = "SELECT ticker, count(*) as ct FROM stocks_d group by ticker";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Count(rs.getString("ticker"), rs.getInt("ct")));
    }
}

