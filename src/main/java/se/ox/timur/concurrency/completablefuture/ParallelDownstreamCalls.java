package se.ox.timur.concurrency.completablefuture;

import se.ox.timur.concurrency.Data;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ParallelDownstreamCalls {
    public Data getData() throws Exception {
        Data data = new Data(0);
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> data.merge(getDataFromServiceA())),
                CompletableFuture.runAsync(() -> data.merge(getDataFromServiceB()))
        ).completeOnTimeout(null, 50, TimeUnit.MILLISECONDS)
                .get();
        return new Data(data);
    }

    private Data getDataFromServiceA() {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }
        return new Data(1);
    }

    private Data getDataFromServiceB() {
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
        }
        return new Data(2);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new ParallelDownstreamCalls().getData().getA());
    }
}
