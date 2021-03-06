package parse;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.FixedLengthQueue;

public class MarkovTextParser {

    private Scanner s;
    private Markov<String> mc;

    public void setMarkov(Markov<String> mc) {
    	this.mc = mc;
    }
    
    /*
     * Parses the given file into a Markov Chain tree.
     * Returns null if no file was loaded.
     *
     * @exception EOFException if file does not meet minumum length
     * for the given Markov Chain order.
     */
    public void parse(File f) throws EOFException, FileNotFoundException {

    	s = new Scanner(new BufferedInputStream(new FileInputStream(f)));
    	

        List<String> initial = new ArrayList<String>();

        for (int i = 0; i < mc.order; i++) {
            if (!s.hasNext()) {
                throw new EOFException("File is not long enough to parse correctly.");
            } else {
                initial.add(s.next());
            }
        }

        FixedLengthQueue<String> q = new FixedLengthQueue<String>(initial);

        while (s.hasNext()) {
            String word = s.next();
            mc.addSeq(q.toList(), word);
            q.shift(word);
        }
    }

    /*
     * Generates a random string using Markov Chain analysis on the
     * given file.
     *
     * @param length The length of the generated text
     */
    public String generate(int length) {

        StringBuilder sb = new StringBuilder();

        FixedLengthQueue<String> q = new FixedLengthQueue<String>(mc.getSeed());

        for (int i = 0; i < length; i++) {
            String next = mc.getNext(q.toList());
            q.shift(next);
            if (i != length - 1) {
                sb.append(next + " ");
            } else {
                sb.append(next + ".");
            }
        }

        return sb.toString();
    }
}
