package parse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Node class representing some prefix. Keeps track of
 * how many times a successor node appears.
 */
public class PrefixNode<V> {

    private HashMap<V, PrefixNode<V>> next;
    private HashMap<V, Integer> count;
    private V value;
    private int total;

    public PrefixNode(V value) {
        this.value = value; 
        next = new HashMap<V, PrefixNode<V>>();
        count = new HashMap<V, Integer>();
        total = 0;
    }

    /*
     * Add the next value to this prefix node.
     * Returns true if it is new; otherwise false.
     */
    public boolean addNext(V vnxt) {

        total++;

        if (next.get(vnxt) == null) {
            next.put(vnxt, new PrefixNode<V>(vnxt));
        }

        if (count.get(vnxt) == null) {
            count.put(vnxt, 1);
            return true;
        }
        else {
            count.put(vnxt, count.get(vnxt) + 1);
            return false;
        }
    }

    /*
     * Returns the prefix this node represents.
     */
    public V getValue() {
        return value; 
    }

    /*
     * Returns an unmodifiable map of all of this
     * node's children and their counts.
     */
    public Map<V, Integer> getCount() {
        return Collections.unmodifiableMap(count);
    }

    /*
     * Returns the total number of children of
     * this node, including duplicates.
     */
    public int getTotal() {
        return total; 
    }

    /*
     * Returns the value child prefix node if it
     * exists, or null otherwise.
     */
    public PrefixNode<V> getNext(V value) {
        return next.get(value);
    }

    /*
     * Returns next value with probability proportional to
     * how many times it appears.
     */
    public V getRand() {
        int n = (new Random()).nextInt(total) + 1;
        int counter = 0;

        for (V value : count.keySet()) {
            counter += count.get(value);
            if (counter >= n) return value;
        }
        return null;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Node " + value + " has children:\n");
        for (V value : next.keySet()) {
            sb.append(value + ": " + count.get(value) + "\n");
        }
        return sb.toString();
    }
}
