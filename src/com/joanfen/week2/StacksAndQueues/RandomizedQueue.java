package com.joanfen.week2.StacksAndQueues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item should not be null");
        }
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("no element in queue");
        }
        int index = StdRandom.uniform(size);
        Item item = queue[index];
        if (index != size - 1) {
            queue[index] = queue[size - 1];
        }
        queue[size - 1] = null;
        size--;
        if (size > 0 && size == queue.length / 4) {

        }
        return item;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
        copy = null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("no element in queue");
        }
        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int iteratorSize = size;
        private Item[] copyQueues = (Item[]) new Object[queue.length];


        public RandomizedQueueIterator() {
            for (int i = 0; i < queue.length; i++) {
                copyQueues[i] = queue[i];
            }
        }

        @Override
        public boolean hasNext() {
            return iteratorSize > 0;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                throw new NoSuchElementException();
            }
            int index = StdRandom.uniform(iteratorSize);
            Item item = copyQueues[index];
            if (index != iteratorSize - 1) {
                copyQueues[index] = copyQueues[iteratorSize - 1];
            }
            copyQueues[iteratorSize - 1] = null;
            iteratorSize--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> ran = new RandomizedQueue<>();
        ran.enqueue(1);
        ran.enqueue(2);
        ran.enqueue(10);
        ran.enqueue(11);
        ran.enqueue(9);

        Iterator<Integer> iterator = ran.iterator();

        while (iterator.hasNext()) {
            StdOut.println("next: " + iterator.next());
        }
    }

}
