package com.joanfen;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> first;
    private Node<Item> last;


    private class Node<Item> {
        private Item item;
        private Node<Item> pre;
        private Node<Item> next;

        public Node(Node<Item> pre, Item item, Node<Item> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        final Node<Item> fNode = first;
        final Node<Item> newNode = new Node<>(null, item, fNode);
        first = newNode;
        if (fNode == null) {
            last = newNode;
        } else {
            fNode.pre = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        final Node<Item> lNode = last;
        final Node<Item> newNode = new Node<>(lNode, item, null);
        last = newNode;
        if (lNode == null) {
            first = newNode;
        } else {
            lNode.next = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("no element in deque");
        }
        final Node<Item> f = first;
        final Item item = f.item;
        final Node<Item> next = first.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.pre = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("no element in deque");
        }
        final Node<Item> l = last;
        final Item item = l.item;
        final Node<Item> pre = l.pre;
        l.item = null;
        l.pre = null;
        last = pre;
        if (pre == null) {
            first = null;
        } else {
            pre.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> next = first;
        private int index;

        public DequeIterator() {
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = this.next.item;
            this.next = this.next.next;
            index++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(10);
        deque.addFirst(11);
        deque.addLast(9);

        Iterator<Integer> iterator = deque.iterator();

        while (iterator.hasNext()) {
            StdOut.println("next: " + iterator.next());
        }

    }

}
