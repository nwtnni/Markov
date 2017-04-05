package parse;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.FixedLengthQueue;

public class TextParser {

    private int order;
    private Markov<String> mc;
    private Scanner s;

    public TextParser(int order) {
        mc = new Markov<String>(order);
    }

    public void load(File f) throws IOException {
        s = new Scanner(new BufferedInputStream(new FileInputStream(f)));
     }

    public void parse() throws EOFException {

        List<String> initial = new ArrayList<String>();

        for (int i = 0; i < order; i++) {
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

    public String generate(int length) {

        StringBuilder sb = new StringBuilder();

        FixedLengthQueue<String> q = new FixedLengthQueue<String>(mc.getSeed());

        for (int i = 0; i < length; i++) {
            String next = mc.getNext(q.toList());
            q.shift(next);
            sb.append(next);
        }

        return sb.toString();
    }
}
