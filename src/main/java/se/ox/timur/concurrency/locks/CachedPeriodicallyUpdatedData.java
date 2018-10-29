package se.ox.timur.concurrency.locks;

import se.ox.timur.concurrency.Data;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedPeriodicallyUpdatedData {
    private Data data;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Data getData() {
        lock.readLock().lock();
        try {
            return data;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void update() {
        lock.writeLock().lock();
        try {
            data = loadData();
        } finally {
            lock.writeLock().unlock();
        }
    }

    private Data loadData() {
        return new Data();
    }
}
