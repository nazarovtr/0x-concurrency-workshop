package se.ox.timur.concurrency.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class EventGeneratingSwitch {
    private final AtomicBoolean enabled = new AtomicBoolean(false);

    public boolean isEnabled() {
        return enabled.get();
    }

    public void enable() {
        boolean oldValue = enabled.getAndSet(true);
        if (!oldValue) {
            generateEvent();
        }
    }

    private void generateEvent() {
    }

}
