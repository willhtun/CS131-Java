import java.util.concurrent.locks.ReentrantLock;

class BetterSafe implements State {
    private byte[] value;
    private byte maxval;
    private ReentrantLock rlock;

    BetterSafe(byte[] v) {
        value = v;
        maxval = 127;
        rlock = new ReentrantLock();
    }

    BetterSafe(byte[] v, byte m) {
        value = v;
        maxval = m;
        rlock = new ReentrantLock();
    }

    public int size() { return value.length; }

    public byte[] current() { return value; }

    public synchronized boolean swap(int i, int j) {
        rlock.lock();
        if (value[i] <= 0 || value[j] >= maxval) {
            rlock.unlock();
            return false;
        }
        value[i]--;
        value[j]++;
        rlock.unlock();
        return true;
    }
}
