package se.ox.timur.concurrency.unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUnsafeCounter {
    private static final int THREAD_COUNT = 10;
    private static int counter = 0;
    private static AtomicInteger correctCounter = new AtomicInteger(0);
    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        for (int threadNumber = 0; threadNumber < THREAD_COUNT; threadNumber++) {
            executor.execute(() -> {
                for (int i = 0; i < 10_000_000; i++) {
                    counter++;
                    correctCounter.incrementAndGet();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(counter);
        System.out.println(correctCounter.intValue());
    }
}
