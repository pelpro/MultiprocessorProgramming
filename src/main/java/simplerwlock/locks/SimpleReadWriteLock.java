package simplerwlock.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimpleReadWriteLock {
    int readers;
    boolean writer;
    Lock readLock, writeLock;

    public SimpleReadWriteLock() {
        writer = false;
        readers = 0;
        readLock = new ReadLock(this);
        writeLock = new WriteLock(this);
    }

    public Lock readLock() {
        return readLock;
    }

    public Lock writeLock() {
        return writeLock;
    }

    public boolean isWriteLock() {
        return writer;
    }

    public int getReaders() {
        return readers;
    }

    public void incrementReaders(int increment) {
        this.readers += increment;
    }

    public boolean isWriter() {
        return writer;
    }

    public void setWriter(boolean writer) {
        this.writer = writer;
    }

    public Lock getReadLock() {
        return readLock;
    }

    public void setReadLock(Lock readLock) {
        this.readLock = readLock;
    }

    public Lock getWriteLock() {
        return writeLock;
    }

    public void setWriteLock(Lock writeLock) {
        this.writeLock = writeLock;
    }
}
