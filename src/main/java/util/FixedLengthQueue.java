package util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Wrapper class for Queue that maintains
 * a fixed-length invariant.
 */
public class FixedLengthQueue<E> {

    private LinkedList<E> q;

    /*
     * Initializes this FixedLengthQueue with
     * length of the given list.
     */
    public FixedLengthQueue(List<E> initial) {
        q = new LinkedList<E>(initial);
    }

    /*
     * Adds an item to the queue, maintaining FIFO
     * and length invariants.
     *
     * @return The previous first element in the queue.
     */
    public E shift(E elem) {
        E first = q.poll();
        q.add(elem);
        return first;
    }

    /*
     * Returns an unmodifiable List version of the queue
     * backing this FixedLengthQueue.
     */
    public List<E> toList() {
        return Collections.unmodifiableList(q);
    }
}
