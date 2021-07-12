package com.acme.mytrader.price.model;

public class BuyPrice {
    private final String security;
    private final double targetPrice;
    private final int volume;


    public BuyPrice(String security, double price, int volume) {
        this.security = security;
        this.targetPrice = price;
        this.volume = volume;
    }

    public String getSecurity() {
        return security;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public int getVolume() {
        return volume;
    }

}
