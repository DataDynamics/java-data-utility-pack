package io.datadynamics.utility;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple {@link IdGenerator} that starts at 1, increments up to
 * {@link Long#MAX_VALUE}, and then rolls over.
 */
public class SimpleIdGenerator  implements IdGenerator {

    private final AtomicLong leastSigBits = new AtomicLong();

    public UUID generateId() {
        return new UUID(0, this.leastSigBits.incrementAndGet());
    }

}
