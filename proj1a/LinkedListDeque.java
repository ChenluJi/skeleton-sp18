//package PACKAGE_NAME;
public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    private final Node sentinel;
    private int size;
    /** constructor: Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /** constructor: Creates a deep copy of other */
    /**public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }*/
    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        Node first = new Node(item, sentinel, null);
        sentinel.next.prev = first;
        first.next = sentinel.next;
        sentinel.next = first;
        size += 1;
    }
    /** Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        Node last = new Node(item, null, sentinel);
        sentinel.prev.next = last;
        last.prev = sentinel.prev;
        sentinel.prev = last;
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size==0;
    }
    /** Returns the number of items in the deque */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line */
    public void printDeque() {
        Node current = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(current.item.toString());
            System.out.print(" ");
            current = current.next;
        }
        System.out.print("\n");
    }
    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return first;
    }
    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0) {
            size -= 1;
        }
        return last;
    }
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }
    /** Same as get, but uses recursion */
    private T getRecursive(int index, Node current) {
        if (index == 0) {
            return current.item;
        } else {
            return getRecursive(index-1, current.next);
        }
    }
    public T getRecursive(int index) {
        if (index + 1 > size) {
            return null;
        }
        Node current = sentinel.next;
        return getRecursive(index, current);
    }
}
