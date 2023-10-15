package io.datadynamics.utility.file;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleIdGenerator {

    private final AtomicLong leastSigBits = new AtomicLong();

    public UUID generateId() {
        return new UUID(0, this.leastSigBits.incrementAndGet());
    }

}
