package io.datadynamics.utility.lock;

import java.util.concurrent.TimeUnit;

public interface DebuggableTimedLock {

    void lock();

    boolean tryLock(long timePeriod, TimeUnit timeUnit);

    boolean tryLock();

    void unlock(String task);
}
