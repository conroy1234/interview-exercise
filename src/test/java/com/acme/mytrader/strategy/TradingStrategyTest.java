package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceSourceImpl;
import com.acme.mytrader.price.model.BuyPrice;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class TradingStrategyTest {

    /*
     this method will not chigger any buy
     */
    @Test
    public void testBuyingTimeClose() throws InterruptedException {
        TradingStrategy tradingStrategy = new TradingStrategy(new ExecutionServiceImpl(1),
                new PriceSourceImpl());
        List stock = Arrays.asList(new BuyPrice("TESLA", 70, 12),
                new BuyPrice("APPLE", 59, 10),
                new BuyPrice("BMW", 59, 10),
                new BuyPrice("AMAZON", 59, 10),
                new BuyPrice("MICROSOFT", 59, 10),
                new BuyPrice("GOOGLE", 59, 10));
        tradingStrategy.autoBuy(stock, false);
    }


    /*
        this test will chigger a buy when one comes available
        */
    @Test
    public void testNTimeToBuy() throws InterruptedException {
        TradingStrategy tradingStrategy = new TradingStrategy(new ExecutionServiceImpl(1),
                new PriceSourceImpl());

        List stock = Arrays.asList(new BuyPrice("TESLA", 70, 12),
                new BuyPrice("APPLE", 59, 10),
                new BuyPrice("BMW", 59, 10),
                new BuyPrice("AMAZON", 59, 10),
                new BuyPrice("MICROSOFT", 59, 10),
                new BuyPrice("GOOGLE", 59, 10));
        tradingStrategy.autoBuy(stock, true);
    }
}
