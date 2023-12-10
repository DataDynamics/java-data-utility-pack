package io.datadynamics.utility.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class DebugEnabledTimedLock implements DebuggableTimedLock {

    private final Lock lock;
    private final Logger logger;
    private final Map<String, Long> lockIterations = new HashMap<>();
    private final Map<String, Long> lockNanos = new HashMap<>();
    private final String name;
    private final int iterationFrequency;
    private long lockTime = 0L;

    public DebugEnabledTimedLock(final Lock lock, final String name, final int iterationFrequency) {
        this.lock = lock;
        this.name = name;
        this.iterationFrequency = iterationFrequency;
        logger = LoggerFactory.getLogger(TimedLock.class.getName() + "." + name);
    }

    /**
     * @return true if lock obtained; false otherwise
     */
    @Override
    public boolean tryLock() {
        logger.trace("Trying to obtain Lock: {}", name);
        final boolean success = lock.tryLock();
        if (!success) {
            logger.trace("TryLock failed for Lock: {}", name);
            return false;
        }
        logger.trace("TryLock successful");

        return true;
    }

    /**
     * @param timeout  duration to wait for lock
     * @param timeUnit unit to understand given duration
     * @return true if lock obtained in time; false otherwise
     */
    @Override
    public boolean tryLock(final long timeout, final TimeUnit timeUnit) {
        logger.trace("Trying to obtain Lock {} with a timeout of {} {}", name, timeout, timeUnit);
        final boolean success;
        try {
            success = lock.tryLock(timeout, timeUnit);
        } catch (final InterruptedException ie) {
            return false;
        }

        if (!success) {
            logger.trace("TryLock failed for Lock {} with a timeout of {} {}", name, timeout, timeUnit);
            return false;
        }
        logger.trace("TryLock successful");
        return true;
    }

    @Override
    public void lock() {
        logger.trace("Obtaining Lock {}", name);
        lock.lock();
        lockTime = System.nanoTime();
        logger.trace("Obtained Lock {}", name);
    }

    /**
     * @param task to release the lock for
     */
    @Override
    public void unlock(final String task) {
        if (lockTime <= 0L) {
            lock.unlock();
            return;
        }

        logger.trace("Releasing Lock {}", name);
        final long nanosLocked = System.nanoTime() - lockTime;

        Long startIterations = lockIterations.get(task);
        if (startIterations == null) {
            startIterations = 0L;
        }
        final long iterations = startIterations + 1L;
        lockIterations.put(task, iterations);

        Long startNanos = lockNanos.get(task);
        if (startNanos == null) {
            startNanos = 0L;
        }
        final long totalNanos = startNanos + nanosLocked;
        lockNanos.put(task, totalNanos);

        lockTime = -1L;

        lock.unlock();
        logger.trace("Released Lock {}", name);

        if (iterations % iterationFrequency == 0) {
            logger.debug("Lock {} held for {} nanos for task: {}; total lock iterations: {}; total lock nanos: {}", name, nanosLocked, task, iterations, totalNanos);
        }
    }

}
