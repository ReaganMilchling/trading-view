package com.TradingView.common;

import com.TradingView.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface TickerRepository extends JpaRepository<Ticker, Long> {

    List<Ticker> findDataByTicker(String ticker);

    //Queries are written this way, as it is easier for me to understand, conceptualize, and develop
    //They may be moved to stored procedures later

    @Query(
            value = """
                    SELECT *
                    FROM ticker
                    WHERE ticker.ticker = ?1
                    AND ticker.timestamp >= ?2""",
            nativeQuery = true
            )
    List<Ticker> getDataByTickerFromTimestamp(String ticker, ZonedDateTime zdt);

    @Query(
            value = """
                    SELECT *
                    FROM ticker
                    WHERE ticker.ticker = ?1
                    AND ticker.timestamp <= ?2""",
            nativeQuery = true
            )
    List<Ticker> getDataByTickerBeforeTimestamp(String ticker, ZonedDateTime zdt);

    @Query(
            value = """
                    SELECT DISTINCT ON (ticker) *
                    FROM ticker
                    ORDER BY ticker, timestamp DESC""",
            nativeQuery = true
            )
    List<Ticker> getLastDayOfAllTickers();

    @Query(
            value = """
                    SELECT DISTINCT ticker from ticker""",
            nativeQuery = true
    )
    List<Ticker> getTickers();
}
