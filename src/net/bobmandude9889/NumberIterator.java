package net.bobmandude9889;

import java.util.Iterator;

public class NumberIterator implements Iterator<int[]> {

    int[] num;

    public NumberIterator() {
        num = new int[] {1,1,0};
    }

    @Override
    public boolean hasNext() {
        return !(num[0] == 5 && num[1] == 5 && num[2] == 5);
    }

    @Override
    public int[] next() {
        for (int i = 2; i >= 0; i--) {
            if (num[i] == 5) {
                num[i] = 1;
            } else {
                num[i] += 1;
                break;
            }
        }
        return num;
    }
}
