package parse;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void old_main(String[] args) {

    	Markov<String> mc;
        MarkovTextParser tp;

        try {
        	mc = new Markov<String>(Integer.parseInt(args[0]));
            tp = new MarkovTextParser();
            tp.setMarkov(mc);
        } catch(NumberFormatException n){
            usage();
            return;
        }

        Scanner s = new Scanner(System.in);

        try {
            tp.parse(new File(args[1]));
        } catch(Exception e){
            s.close();
            return;
        }

        while (true) {

            System.out.println("Enter how many words to generate, or q to quit.");
            String input = s.next();

            if (input.equals("q")) {
                break;
            } else {
                try {
                    String out = tp.generate(Integer.parseInt(input));
                    System.out.println(out);
                } catch(NumberFormatException nf){
                    System.out.println("Invalid input.");
                }
            }
        }

        s.close();
    }

    private static void usage() {
        System.out.println("Usage: java -jar Markov.jar <ORDER> <FILE>");
        System.out.println("Where <ORDER> is the desired Markov Chain order");
        System.out.println("And <FILE> is the file to parse");
    }
}
