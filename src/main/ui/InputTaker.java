package ui;

import java.util.Scanner;

/**
 * Utility class for taking inputs
 */

public final class InputTaker {
    private InputTaker(){}

    // EFFECTS: takes and returns user input with given prompt
    public static String takeInput(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}
