package parse;

import java.util.List;

public class Markov<V> {

    int order;
    PrefixNode<V> root;

    public Markov(int order) {
        this.order = order; 
        root = new PrefixNode<V>(null);
    }

    /*
     * Adds the given sequence to this Markov chain.
     * Returns true if successful; otherwise false.
     */
    public boolean addSeq(List<V> prefix, V next) {

        // Must match this Markov chain's order
        if (prefix.size() != order) {
            return false; 
        }
    
        PrefixNode<V> ptr = root;
        for (V value: prefix) {
            ptr.addNext(value);
            ptr = ptr.getNext(value);
        }

        ptr.addNext(next);
        return true;
    }

    /*
     * Returns a random value with probability
     * proportional to how many times it's appeared
     * after the given prefix.
     */
    public V getNext(List<V> prefix) {
        if (prefix.size() != order) {
            return null; 
        }

        PrefixNode<V> ptr = root;
        for (V value: prefix) {
            ptr = ptr.getNext(value); 
        }
        return ptr.getRand();
    }
}
