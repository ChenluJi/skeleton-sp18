public class ArrayDeque<T> {
    private T[] items;
    private int size;
    /** Circular ArrayDeque */
    private int nextFirst;
    private int nextLast;
    /** constructor: Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    /** constructor: Creates a deep copy of other. */
    /**public ArrayDeque(ArrayDeque other) {
        size = other.size();
        Object[] a = new Object[other.items.length];
        System.arraycopy(other.items, 0, a, 0, other.items.length);
        items = (T[]) a;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }*/
    private int getPrevIndex(int idx) {
        int prevIdx = idx - 1;
        if (prevIdx < 0) {
            prevIdx += items.length;
        }
        return prevIdx;
    }
    private int getNextIndex(int idx) {
        int nextIdx = idx + 1;
        if (nextIdx >= items.length) {
            nextIdx -= items.length;
        }
        return nextIdx;
    }
    private int getNextNthIndex(int idx, int n) {
        int nextNthIdx = idx + n;
        if (nextNthIdx >= items.length) {
            nextNthIdx -= items.length;
        }
        return nextNthIdx;
    }
    /** resize current items length to 2*length */
    private T[] resize(T[] array) {
        T[] a = (T[]) new Object[size * 2];
        //TODOa: check arraycopy
        int idx1 = getNextIndex(nextFirst);
        System.arraycopy(array, idx1, a, 0, array.length - idx1);
        if (idx1 != 0) {
            int idx2 = getPrevIndex(nextLast);
            System.arraycopy(array, 0, a, array.length - idx1, idx2 + 1);
        }
        nextFirst = a.length - 1;
        nextLast = size;
        return a;
    }
    /** downsize current items length: For arrays of length 16 or more,
     * your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low
    private T[] downSize(T[] array) {
        T[] a = (T[]) new Object[size * 2];
        // TODOa: check arraycopy
        int idx1 = getNextIndex(nextFirst);
        int idx2 = getPrevIndex(nextLast);
        System.arraycopy(array, idx1, a, 0, array.length - idx1);
        System.arraycopy(array, 0, a, array.length - idx1, idx2);
        nextFirst = a.length - 1;
        nextLast = size;
        return a;
    } */

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T f) {
        if (size == items.length) {
            items = resize(items);
        }
        items[nextFirst] = f;
        nextFirst = getPrevIndex(nextFirst);
        size += 1;
    }
    /** Adds an item of type T to the back of the deque */
    public void addLast(T l) {
        if (size == items.length) {
            items = resize(items);
        }
        items[nextLast] = l;
        nextLast = getNextIndex(nextLast);
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }
    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line */
    public void printDeque() {
        int idx = getNextIndex(nextFirst);
        while (idx != nextLast) {
            System.out.print(items[idx]);
            System.out.print(" ");
            idx = getNextIndex(idx);
        }
        System.out.print("\n");
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. For arrays of length 16 or more,
     * your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low */
    public T removeFirst() {
        T first = items[getNextIndex(nextFirst)];
        items[getNextIndex(nextFirst)] = null;
        if (size > 0) {
            size -= 1;
        }
        nextFirst = getNextIndex(nextFirst);
        if ((size >= 16) & ((float) size / items.length < 0.25)) {
            items = resize(items);
        }
        return first;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. For arrays of length 16 or more,
     * your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T last = items[getPrevIndex(nextLast)];
        items[getPrevIndex(nextLast)] = null;
        size -= 1;
        nextLast = getPrevIndex(nextLast);
        if ((size >= 16) & ((float) size / items.length < 0.25)) {
            items = resize(items);
        }
        return last;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        int first = getNextIndex(nextFirst);
        return items[getNextNthIndex(first, index)];
    }
}
