package Threads;

public class SumThread extends Thread{
    int index;
    int[] arrayPart;
    int sum;

    public SumThread(int index, int[] arrayPart) {
        this.index = index;
        this.arrayPart = arrayPart;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i : arrayPart) {
            this.sum += arrayPart[i];
        }
    }

    public int getSum() {
        return this.sum;
    }
}
