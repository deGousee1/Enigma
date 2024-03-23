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
        List<String> ResultList = new ArrayList<>(List.of());


        int rotor1Display = 1;
        int rotor2Display = 1;
        int rotor3Display = 1; // rotorDisplay variables could be removed


        // Necessary variables initialization
        // Rotor 1
        int rotorInput1;
        int rotorOutput1;
        // Rotor 2
        int rotorOutput2;
        //Rotor 3
        int rotorOutput3;
        // Reverser
        int reverserInput = 0;
        int reverserOutput;
        //Rotor 3 back
        int rotorInput3Back;
        int rotorOutput3Back;
        //Rotor 2 back
        int rotorInput2Back;
        int rotorOutput2Back;
        //Rotor 1 back
        int rotorInput1Back;
        int rotorOutput1Back;
        // Result variables
        int resultDigit;
        String resultLetter = "---";

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
            rotor1Display = positionRotor1 + 1;
            rotor2Display = positionRotor2 + 1;
            rotor3Display = positionRotor3 + 1;
            // Letter input
            System.out.println("Rotor 1: " + rotor1Display + ", Rotor2: " + rotor2Display + ", Rotor3: " + rotor3Display + ".");
            System.out.println("Enter the letter for scrambling (Only uppercase letters): ");
            char letter = scanner.next().charAt(0);
            // Rotor 1
            rotorInput1 = getAlphabetIndex(alphabet, letter) + positionRotor1;
            rotorOutput1 = getOutputForRotor1(rotorInput1, rotor1Scrambler);

            // Rotor 2
            rotorOutput2 = getOutputForRotor2(positionRotor1, positionRotor2, rotorOutput1, rotor2Scrambler);
            // Rotor 3
            rotorOutput3 = getOutputForRotor3(positionRotor2, positionRotor3, rotorOutput2, rotor3Scrambler);
            // Reverser
            reverserOutput = getOutputForReverser(positionRotor3, rotorOutput3, reverserScrambler);

            // Rotor 3 back
            rotorInput3Back = reverserOutput + positionRotor3;

            if (rotorInput3Back > 25) {
                rotorInput3Back = rotorInput3Back - 26;
            }
            else {
                if (reverserInput < 0) {
                    rotorInput3Back = rotorInput3Back + 26;
                }
            }

            rotorOutput3Back = rotor3Scrambler.get(rotorInput3Back);
            // Rotor 2 back
            if (positionRotor3 > positionRotor2) {
                rotorInput2Back = rotorOutput3Back - (positionRotor3 - positionRotor2);
            }
            else {
                if (positionRotor3 < positionRotor2) {
                    rotorInput2Back = rotorOutput3Back + (positionRotor2 - positionRotor3);
                }
                else {
                    rotorInput2Back = rotorOutput3Back;
                }
            }
            if (rotorInput2Back > 25) {
                rotorInput2Back = rotorInput2Back - 26;
            }
            else {
                if (rotorInput2Back < 0) {
                    rotorInput2Back = rotorInput2Back + 26;
                }
            }
            rotorOutput2Back = rotor2Scrambler.get(rotorInput2Back);
            // Rotor 1 back
            if (positionRotor2 > positionRotor1) {
                rotorInput1Back = rotorOutput2Back - (positionRotor2 - positionRotor1);
            }
            else {
                if (positionRotor2 < positionRotor1) {
                    rotorInput1Back = rotorOutput2Back + (positionRotor1 - positionRotor2);
                }
                else {
                    rotorInput1Back = rotorOutput2Back;
                }
            }
            if (rotorInput1Back > 25) {
                rotorInput1Back = rotorInput1Back - 26;
            }
            else {
                if (rotorInput1Back < 0) {
                    rotorInput1Back = rotorInput1Back + 26;
                }
            }
            rotorOutput1Back = rotor1Scrambler.get(rotorInput1Back);
            // Result
            resultDigit = rotorOutput1Back - positionRotor1;
            if (rotorOutput1Back < 26 && rotorOutput1Back >= 0) {
                if (resultDigit < 0) {
                    resultLetter = String.valueOf(alphabet.get(resultDigit + 26));
                }
                else {
                    resultLetter = String.valueOf(alphabet.get(rotorOutput1Back - positionRotor1));
                }
            }
            else {
                if (resultDigit > 25) {
                    resultLetter = String.valueOf(alphabet.get(resultDigit - 26));
                }
                else {
                    resultLetter = String.valueOf(alphabet.get(resultDigit + 26));
                }
            }
            System.out.println("Scrambling result: " + resultLetter);
            ResultList.add(resultLetter);
            if (positionRotor1 == 25) {
                positionRotor1 = 0;
                if (positionRotor2 == 25) {
                    positionRotor2 = 0;
                    if (positionRotor3 == 25) {
                        positionRotor3 = 0;
                    }
                    else {
                        positionRotor3 = positionRotor3 + 1;
                    }
                }
                else {
                    positionRotor2 = positionRotor2 + 1;
                }
            }
            else {
                positionRotor1 = positionRotor1 + 1;
            }
        }
        System.out.println("Scrambled text: " + ResultList);

    }


    private static int getAlphabetIndex(List<Character> alphabet, char letter) {
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i) == letter) {
                return i;
            }
        }
        return -1; // Return -1 if the letter is not in the alphabet
    }

    private static int getOutputForRotor1(int rotorInput1, List<Integer> rotor1Scrambler) {
        if (rotorInput1 > 25) {
            rotorInput1 = rotorInput1 - 26;
        }
        else {
            if (rotorInput1 < 0) {
                rotorInput1 = rotorInput1 + 26;
            }
        }
        return rotor1Scrambler.get(rotorInput1);
    }

    private static int getOutputForRotor2(int positionRotor1, int positionRotor2, int rotorOutput1, List<Integer> rotor2Scrambler) {
        int rotorInput2 = 0;
        if (positionRotor1 > positionRotor2) {
            rotorInput2 = rotorOutput1 - (positionRotor1 - positionRotor2);
        }
        else {
            if (positionRotor1 < positionRotor2) {
                rotorInput2 = rotorOutput1 + (positionRotor2 - positionRotor1);
            }
            else {
                rotorInput2 = rotorOutput1;
            }
        }

        if (rotorInput2 > 25) {
            rotorInput2 = rotorInput2 - 26;
        }
        else {
            if (rotorInput2 < 0) {
                rotorInput2 = rotorInput2 + 26;
            }
        }

        return rotor2Scrambler.get(rotorInput2);
    }
    private static int getOutputForRotor3(int positionRotor2, int positionRotor3, int rotorOutput2, List<Integer> rotor3Scrambler) {
        int rotorInput3 = 0;
        if (positionRotor2 > positionRotor3) {
            rotorInput3 = rotorOutput2 - (positionRotor2 - positionRotor3);
        }
        else {
            if (positionRotor2 < positionRotor3) {
                rotorInput3 = rotorOutput2 + (positionRotor3 - positionRotor2);
            }
            else {
                rotorInput3 = rotorOutput2;
            }
        }

        if (rotorInput3 > 25) {
            rotorInput3 = rotorInput3 - 26;
        }
        else {
            if (rotorInput3 < 0) {
                rotorInput3 = rotorInput3 + 26;
            }
        }

        return rotor3Scrambler.get(rotorInput3);
    }
    private static int getOutputForReverser(int positionRotor3, int rotorOutput3, List<Integer> reverserScrambler) {
        int reverserInput = 0;
        reverserInput = rotorOutput3 - positionRotor3;

        if (reverserInput > 25) {
            reverserInput = reverserInput - 26;
        }
        else {
            if (reverserInput < 0) {
                reverserInput = reverserInput + 26;
            }
        }
        return reverserScrambler.get(reverserInput);
    }
}