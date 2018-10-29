package se.ox.timur.concurrency.volatility;

public class CachedPeriodicallyUpdatedData {
    private volatile Data data;

    public Data getData() {
        return data;
    }

    public void update() {
        data = loadData();
    }

    private Data loadData() {
        return new Data();
    }
}
