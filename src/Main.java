import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        // Rotor 1 letter scrambler list
        List<Integer> rotor1Scrambler = Arrays.asList(7, 17, 25, 12, 10, 8, 18, 0, 5, 11, 4, 9, 3, 24, 19, 21, 23, 1, 6, 14, 22, 15, 20, 16, 13, 2);
        // Rotor 2 letter scrambler list
        List<Integer> rotor2Scrambler = Arrays.asList(9, 3, 25, 1, 6, 11, 4, 19, 16, 0, 13, 5, 22, 10, 15, 14, 8, 21, 20, 7, 18, 17, 12, 24, 23, 2);
        // Rotor 3 letter scrambler list
        List<Integer> rotor3Scrambler = Arrays.asList(2, 5, 0, 13, 22, 1, 15, 20, 12, 18, 14, 25, 8, 3, 10, 6, 23, 19, 9, 17, 7, 24, 4, 16, 21, 11);
        // Reverser letter scrambler list
        List<Integer> reverserScrambler = Arrays.asList(11, 3, 13, 1, 5, 4, 16, 12, 22, 23, 17, 0, 7, 2, 24, 18, 6, 10, 15, 21, 25, 19, 8, 9, 14, 20);
        // Result list
        List<Character> ResultList = new ArrayList<>(List.of());

        // Necessary variables initialization
        int reverserInput = 0;
        // Result variables
        char resultLetter;

        // Text length
        System.out.println("Type in the length of the text you want to scramble: ");
        int textLength = scanner.nextInt();

        // Initial setting of rotors made by the user
        System.out.println("Choose the starting position of the first rotor (from 1 to 26): ");
        int positionRotor1 = scanner.nextInt() - 1;
        System.out.println("Choose the starting position of the second rotor: ");
        int positionRotor2 = scanner.nextInt() - 1;
        System.out.println("Choose the starting position of the third rotor: ");
        int positionRotor3 = scanner.nextInt() - 1;



        for (int i = 0; i < textLength; i++) {
            // Scrambling engine
            // Letter input and rotor position display
            System.out.println("Rotor 1: " + (positionRotor1 + 1) + ", Rotor2: " + (positionRotor2 + 1) + ", Rotor3: " + (positionRotor3 + 1) + ".");
            System.out.println("Enter the letter for scrambling (Only uppercase letters): ");
            char letter = scanner.next().charAt(0);
            // Scrambling engine
            ScramblingEngine engine = new ScramblingEngine(alphabet, rotor1Scrambler, rotor2Scrambler, rotor3Scrambler, reverserScrambler, reverserInput, positionRotor1, positionRotor2, positionRotor3);
            // Getting the result from the engine
            resultLetter = engine.scramble(letter);
            //Printing result letter
            System.out.println("Scrambling result: " + resultLetter);
            ResultList.add(resultLetter);
            // Automatic rotor movement
            if (positionRotor1 == 25) {
                positionRotor1 = 0;
                if (positionRotor2 == 25) {
                    positionRotor2 = 0;
                    if (positionRotor3 == 25) {
                        positionRotor3 = 0;
                    } else {
                        positionRotor3 = positionRotor3 + 1;
                    }
                } else {
                    positionRotor2 = positionRotor2 + 1;
                }
            } else {
                positionRotor1 = positionRotor1 + 1;
            }
        }
        // Printing the full result
        System.out.println("Scrambled text: " + ResultList);

    }
}