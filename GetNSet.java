import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSet implements State {
    private AtomicIntegerArray int_array;
    private byte maxval;

    GetNSet(byte[] v) {
        maxval = 127;
        int[] arr = new int[v.length];
        for(int i = 0; i < v.length; i++)
            arr[i] = v[i];
        atomic_array = new AtomicIntegerArray(arr);
    }

    GetNSet(byte[] v, byte m) {
        maxval = m;
        int[] arr = new int[v.length];
        for(int i = 0; i < v.length; i++)
            arr[i] = v[i];
        atomic_array = new AtomicIntegerArray(arr);
    }

    public int size() { return int_array.length(); }

    public byte[] current() {
        byte[] arr = new byte[int_array.length()];
        for (int i = 0; i < int_array.length(); i++)
            arr[i] = (byte) int_array.get(i);
        return arr;
    }

    public synchronized boolean swap(int i, int j) {
        if (int_array.get(i) <= 0 || int_array.get(j) >= maxval)
            return false;
        atomic_array.getAndDecrement(i);
        atomic_array.getAndIncrement(j);
        return true;
    }
}
