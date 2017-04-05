package util;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Wrapper class for Queue that maintains
 * a fixed-length invariant.
 */
public class FixedLengthQueue<E> {

    private Queue<E> q;

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
}
