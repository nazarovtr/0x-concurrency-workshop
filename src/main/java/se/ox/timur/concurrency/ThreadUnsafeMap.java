package se.ox.timur.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ThreadUnsafeMap {
    private static final int THREAD_COUNT = 10;
    private static final int NUMBER_OF_ITERATIONS = 10;
    private static final int WAIT = 20;
    private static Map<Integer, String> map = new HashMap<>();
    private static Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        for (int threadNumber = 0; threadNumber < THREAD_COUNT; threadNumber++) {
            int finalThreadNumber = threadNumber;
            executor.execute(() -> {
                for (int i = NUMBER_OF_ITERATIONS * finalThreadNumber; i < NUMBER_OF_ITERATIONS * (finalThreadNumber + 1); i++) {
                    map.put(i, "value");
                    concurrentMap.put(i, "value");
                    randomWait();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(map.size());
        System.out.println(concurrentMap.size());
    }

    private static void randomWait() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(WAIT));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
