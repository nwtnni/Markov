package parse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

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

    public void parse() {
       

        

        while (s.hasNext()) {


        } 
    }
}
