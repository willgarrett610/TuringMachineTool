package net.bobmandude9889;

import java.util.Iterator;

public class NumberIterable implements Iterable<int[]> {

    @Override
    public Iterator<int[]> iterator() {
        return new NumberIterator();
    }
}
