package se.ox.timur.concurrency.synchronization;

import se.ox.timur.concurrency.Data;

public class CachedPeriodicallyUpdatedData {
    private Data data;

    public synchronized Data getData() {
        return data;
    }

    public synchronized void update() {
        data = loadData();
    }

    private Data loadData() {
        return new Data();
    }
}
