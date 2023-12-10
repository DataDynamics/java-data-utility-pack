package io.datadynamics.utility.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class DebugDisabledTimedLock implements DebuggableTimedLock {

    private final Lock lock;

    public DebugDisabledTimedLock(final Lock lock) {
        this.lock = lock;
    }

    /**
     * @return true if lock obtained; false otherwise
     */
    @Override
    public boolean tryLock() {
        return lock.tryLock();
    }

    /**
     * @param timeout  the duration of time to wait for the lock
     * @param timeUnit the unit which provides meaning to the duration
     * @return true if obtained lock in time; false otherwise
     */
    @Override
    public boolean tryLock(final long timeout, final TimeUnit timeUnit) {
        try {
            return lock.tryLock(timeout, timeUnit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock(final String task) {
        lock.unlock();
    }

}
