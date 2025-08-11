package com.tradingview.ticker.Repository;

import com.tradingview.ticker.Entity.Stock;
import com.tradingview.ticker.Entity.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TickerRepository extends JpaRepository<Stock, StockId> {
    List<Stock> findAllByIdTickerOrderByIdTimeDesc(String ticker);
    List<Stock> findTopIdTimeByIdTicker(String ticker);
}
