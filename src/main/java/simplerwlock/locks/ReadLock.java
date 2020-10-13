package simplerwlock.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ReadLock implements Lock {

    SimpleReadWriteLock simpleReadWriteLock;

    public ReadLock(SimpleReadWriteLock simpleReadWriteLock) {
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
            simpleReadWriteLock.incrementReaders(1);
        }
    }

    public void unlock() {
        synchronized (simpleReadWriteLock) {
            simpleReadWriteLock.incrementReaders(-1);
            if (simpleReadWriteLock.getReaders() == 0)
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
