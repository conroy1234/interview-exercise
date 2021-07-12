package com.acme.mytrader.execution;


public class ExecutionServiceImpl implements ExecutionService {

    private final int id;

    public ExecutionServiceImpl(int id) {
        this.id = id;
    }

    @Override
    public void buy(String security, double price, int volume) {
        System.out.printf("\n BUY executed for %s at the price of £ %.2f for %d number of securities", security,
                price, volume);
    }

    @Override
    public void sell(String security, double price, int volume) {
        throw new UnsupportedOperationException("Out of scope for this inteview-excercise");
    }
}
