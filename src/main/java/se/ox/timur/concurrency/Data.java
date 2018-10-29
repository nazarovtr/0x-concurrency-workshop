package se.ox.timur.concurrency;

public class Data {
    private int a;

    public Data() {
        a = 1;
    }

    public Data(int a) {
        this.a = a;
    }

    public Data(Data other) {
        this.a = other.getA();
    }

    public synchronized Data merge(Data other) {
        a += other.getA();
        return this;
    }

    public synchronized int getA() {
        return a;
    }
}
