package io.datadynamics.utility.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TimedLock {

    private final DebugEnabledTimedLock enabled;
    private final DebugDisabledTimedLock disabled;

    private final Logger logger;

    public TimedLock(final Lock lock, final String name, final int iterationFrequency) {
        this.enabled = new DebugEnabledTimedLock(lock, name, iterationFrequency);
        this.disabled = new DebugDisabledTimedLock(lock);

        logger = LoggerFactory.getLogger(TimedLock.class.getName() + "." + name);
    }

    private DebuggableTimedLock getLock() {
        return logger.isDebugEnabled() ? enabled : disabled;
    }

    public boolean tryLock() {
        return getLock().tryLock();
    }

    public boolean tryLock(final long timeout, final TimeUnit timeUnit) {
        return getLock().tryLock(timeout, timeUnit);
    }

    public void lock() {
        getLock().lock();
    }

    public void unlock(final String task) {
        getLock().unlock(task);
    }

}
