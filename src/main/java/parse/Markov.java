package parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import util.Entry;

public class Markov<V> {

    int order;
    PrefixNode<V> root;
    
    /*
     * Constructs a Markov Chain with the given order.
     */
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
     * after the given prefix. If the prefix has
     * not appeared, then return a random value based
     * on the longest subsequence.
     */
    public V getNext(List<V> prefix) {
    	
        PrefixNode<V> ptr = root;

        for (V value: prefix) {
            if (ptr.hasNext(value)) {
                ptr = ptr.getNext(value);
            } else {
                break;
            }
        }
        return ptr.getRand();
    }

    /*
     * Returns a valid seed prefix for this Markov chain.
     */
    public List<V> getSeed() {
        List<V> seed = new LinkedList<V>();

        PrefixNode<V> ptr = root;
        for (int i = 0; i < order; i++) {
            V value = ptr.getRand();
            seed.add(value);
            ptr = ptr.getNext(value);
        }

        return seed;
    }
    
    public List<Entry<V>> mostProbable(List<V> prefix, int size) {
        PrefixNode<V> ptr = root;
        ArrayList<Entry<V>> probable = new ArrayList<>();
        
    	for (V value: prefix) {
            if (ptr.hasNext(value)) {
                ptr = ptr.getNext(value);
            } else {
                break;
            }
        }
    	
    	Stream<Map.Entry<V, Integer>> stream = ptr.getCount().entrySet().stream()
    			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(size);
    	
        float total = (float) ptr.getTotal();
    	stream.forEachOrdered(item -> {
    		probable.add(new Entry<V>(item.getValue() / total, item.getKey()));
    	});
    	
    	return probable;
    }
}
