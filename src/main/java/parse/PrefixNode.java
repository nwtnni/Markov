package parse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Node class representing some prefix. Keeps track of
 * how many times a successor node appears.
 */
public class PrefixNode<V> {

    private HashMap<V, PrefixNode<V>> next;
    private HashMap<V, Integer> count;
    private HashMap<V, Double> prob;
    private V value;
    private int total;

    public PrefixNode(V value) {
        this.value = value; 
        next = new HashMap<V, PrefixNode<V>>();
        count = new HashMap<V, Integer>();
        prob = new HashMap<V, Double>();
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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Node " + value + " has children:\n");
        
        for (V value : next.keySet()) {
            sb.append(); 
        }

        return null; 
    }
}
