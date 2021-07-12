package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.model.BuyPrice;

public class PriceListenerImpl implements PriceListener {

    private final ExecutionService executionService;
    private boolean isTimeToBuy;
    private BuyPrice buyTriger;

    public PriceListenerImpl(BuyPrice buyPrice, ExecutionService executionService, boolean isTimeToBuy) {

        this.executionService = executionService;
        this.isTimeToBuy = isTimeToBuy;
        this.buyTriger = executeBuy(buyPrice.getSecurity(), buyPrice.getTargetPrice(), buyPrice.getVolume());
    }

    private BuyPrice executeBuy(String security, double targetPrice, int quantity) {
        return new BuyPrice(security, targetPrice, quantity);

    }

    @Override
    public void priceUpdate(String security, double price) {

        if (isTimeToBuy(buyTriger, security, price)) {
            executionService.buy(security, price, buyTriger.getVolume());
        } else {
            waitingToBuy();
        }
    }

    private boolean isTimeToBuy(BuyPrice buyPrice, String security, double price) {
        return (isTimeToBuy) && buyPrice.getSecurity().equals(security) && buyPrice.getTargetPrice() < price;
    }

    private void waitingToBuy() {
        System.out.printf("\n waiting ...........");
    }

}
