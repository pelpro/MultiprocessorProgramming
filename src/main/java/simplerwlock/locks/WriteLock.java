package simplerwlock.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class WriteLock implements Lock {
    SimpleReadWriteLock simpleReadWriteLock;

    public WriteLock(SimpleReadWriteLock simpleReadWriteLock) {
        this.simpleReadWriteLock = simpleReadWriteLock;
    }

    public void lock() {
        synchronized (simpleReadWriteLock) {
            while (simpleReadWriteLock.isWriter()) {
                try {
                    simpleReadWriteLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            simpleReadWriteLock.setWriter(true);
            while (simpleReadWriteLock.getReaders() > 0) {
                try {
                    simpleReadWriteLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock() {
        synchronized (simpleReadWriteLock) {
            simpleReadWriteLock.setWriter(false);
            simpleReadWriteLock.notifyAll();
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public Condition newCondition() {
        return null;
    }
}