package se.ox.timur.concurrency.wrong;

import se.ox.timur.concurrency.Data;

public class WrongLazySingleton {
    private static Data data;

    public static Data getData() {
        if (data == null) {
            data = new Data();
        }
        return data;
    }
}
