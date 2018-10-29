package se.ox.timur.concurrency.immutable;

import se.ox.timur.concurrency.Data;

public class Immutable {
    private final String name;
    private final Data data;

    public Immutable(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public Data getData() {
        return data;
    }
}
