package io.datadynamics.utility.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.*;

public class TimedLockTest {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static TimedLock readLock = new TimedLock(rwLock.readLock(), "FlowControllerReadLock", 1);
    private static TimedLock writeLock = new TimedLock(rwLock.writeLock(), "FlowControllerWriteLock", 1);

    public static void main(String[] args) throws InterruptedException {
        readLock.lock();
        Thread.sleep(5000);
        readLock.unlock("Run Task");
    }

}