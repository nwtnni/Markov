package parse;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TextParser tp = new TextParser(Integer.parseInt(args[0]));
        Scanner s = new Scanner(System.in);

        try {
            tp.load(new File(args[1]));
            tp.parse();
        } catch(Exception e){
            System.out.println("Invalid file. Exiting...");
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
}
