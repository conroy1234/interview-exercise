package com.acme.mytrader.price;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class PriceSourceImpl implements PriceSource {
    private static final int MIN = 1;
    private static final int MAX = 100;

    private final int TARGET = 10;
    private static final List<PriceListener> priceListeners = new CopyOnWriteArrayList<>();
    private static final List<String> SECURITIES = Arrays.asList("TESLA", "BMW", "AMAZON", "MICROSOFT", "APPLE", "GOOGLE");

    @Override
    public void addPriceListener(PriceListener listener) {
        this.priceListeners.add(listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        this.priceListeners.remove(listener);
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < TARGET; i++) {
            String security = SECURITIES.get(rand.nextInt(SECURITIES.size()));
            double price = MIN + (MAX - MIN) * rand.nextDouble();
            priceListeners.forEach(priceListener -> priceListener.priceUpdate(security, price));
        }
    }

}
