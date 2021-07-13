package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.PriceSourceImpl;
import com.acme.mytrader.price.model.BuyPrice;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private final ExecutionService tradeExecutionService;
    private final PriceSource priceSource;

    public TradingStrategy(ExecutionService tradeExecutionService, PriceSource priceSource) {
        this.tradeExecutionService = tradeExecutionService;
        this.priceSource = priceSource;
    }

    public void autoBuy(List<BuyPrice> request, boolean isTimeToBuy) throws InterruptedException {

        request.stream().map(
                buyPrice -> new PriceListenerImpl(buyPrice,
                        tradeExecutionService, isTimeToBuy)).forEach(priceSource::addPriceListener);
        Thread thread = new Thread(priceSource);
        thread.start();
        thread.join();
    }


    public static void main(String[] args) throws InterruptedException {
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
