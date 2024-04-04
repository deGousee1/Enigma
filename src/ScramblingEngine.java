import java.util.List;

public class ScramblingEngine {
    private final List<Character> alphabet;
    private final List<Integer> rotor1Scrambler;
    private final List<Integer> rotor2Scrambler;
    private final List<Integer> rotor3Scrambler;
    private final List<Integer> reverserScrambler;
    private int positionRotor1;
    private int positionRotor2;
    private int positionRotor3;
    private final int reverserInput;

    //Constructor
    public ScramblingEngine(List<Character> alphabet, List<Integer> rotor1Scrambler, List<Integer> rotor2Scrambler, List<Integer> rotor3Scrambler, List<Integer> reverserScrambler, int reverserInput, int positionRotor1, int positionRotor2, int positionRotor3) {
        this.alphabet = alphabet;
        this.rotor1Scrambler = rotor1Scrambler;
        this.rotor2Scrambler = rotor2Scrambler;
        this.rotor3Scrambler = rotor3Scrambler;
        this.reverserScrambler = reverserScrambler;
        this.reverserInput = reverserInput;
        this.positionRotor1 = positionRotor1;
        this.positionRotor2 = positionRotor2;
        this.positionRotor3 = positionRotor3;
    }

    // Scramblers and rotors
    public char scramble(Character letter) {
        // Rotor 1
        int rotorInput1 = getAlphabetIndex(alphabet, letter) + positionRotor1;
        int rotorOutput1 = getOutputForRotor1(rotorInput1, rotor1Scrambler);
        // Rotor 2
        int rotorOutput2 = getOutputForRotor2(positionRotor1, positionRotor2, rotorOutput1, rotor2Scrambler);
        // Rotor 3
        int rotorOutput3 = getOutputForRotor3(positionRotor2, positionRotor3, rotorOutput2, rotor3Scrambler);
        // Reverser
        int reverserOutput = getOutputForReverser(positionRotor3, rotorOutput3, reverserScrambler);
        // Rotor 3 back
        rotorOutput3 = getOutputForRotor3Back(positionRotor3, reverserOutput, reverserInput, rotor3Scrambler);
        // Rotor 2 back
        rotorOutput2 = getOutputForRotor2Back(positionRotor3, positionRotor2, rotorOutput3, rotor2Scrambler);
        // Rotor 1 back
        rotorOutput1 = getOutputForRotor1Back(positionRotor2, positionRotor1, rotorOutput2, rotor1Scrambler);
        // Result
        return getResultLetter(positionRotor1, rotorOutput1, alphabet);
    }

    // Method for updating the position of Rotor 1
    public void updateRotor1Position(int positionRotor1) {
        this.positionRotor1 = positionRotor1;
    }

    // Method for counting the position of Rotor 1
    public int getPositionRotor1() {
        if (positionRotor1 == 25) {
            positionRotor1 = 0;
        } else {
            positionRotor1 = positionRotor1 + 1;
        }
        return this.positionRotor1;
    }

    // Method for updating the position of Rotor 2
    public void updateRotor2Position(int positionRotor2) {
        this.positionRotor2 = positionRotor2;
    }

    // Method for counting the position of Rotor 2
    public int getPositionRotor2() {
        if (positionRotor1 == 25) {
            if (positionRotor2 == 25) {
                positionRotor2 = 0;
            } else {
                positionRotor2 = positionRotor2 + 1;
            }
        }
        return this.positionRotor2;
    }

    // Method for updating the position of Rotor 3
    public void updateRotor3Position(int positionRotor3) {
        this.positionRotor3 = positionRotor3;
    }

    // Method for counting the position of Rotor 3
    public int getPositionRotor3() {
        if (positionRotor1 == 25) {
            if (positionRotor2 == 25) {
                if (positionRotor3 == 25) {
                    positionRotor3 = 0;
                } else {
                    positionRotor3 = positionRotor3 + 1;
                }
            }
        }
        return this.positionRotor3;
    }

    // Get alphabet index (converts the letter given by the user into a number)
    private static int getAlphabetIndex(List<Character> alphabet, Character letter) {
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i).equals(letter)) {
                return i;
            }
        }
        return -1; // Return -1 if the letter is not in the alphabet
    }

    // Rotor 1 output
    private static int getOutputForRotor1(int rotorInput1, List<Integer> rotor1Scrambler) {
        if (rotorInput1 > 25) {
            rotorInput1 = rotorInput1 - 26;
        } else {
            if (rotorInput1 < 0) {
                rotorInput1 = rotorInput1 + 26;
            }
        }
        return rotor1Scrambler.get(rotorInput1);
    }

    // Rotor 2 output
    private static int getOutputForRotor2(int positionRotor1, int positionRotor2, int rotorOutput1, List<Integer> rotor2Scrambler) {
        int rotorInput2;
        if (positionRotor1 > positionRotor2) {
            rotorInput2 = rotorOutput1 - (positionRotor1 - positionRotor2);
        } else {
            if (positionRotor1 < positionRotor2) {
                rotorInput2 = rotorOutput1 + (positionRotor2 - positionRotor1);
            } else {
                rotorInput2 = rotorOutput1;
            }
        }

        if (rotorInput2 > 25) {
            rotorInput2 = rotorInput2 - 26;
        } else {
            if (rotorInput2 < 0) {
                rotorInput2 = rotorInput2 + 26;
            }
        }

        return rotor2Scrambler.get(rotorInput2);
    }

    // Rotor 3 output
    private static int getOutputForRotor3(int positionRotor2, int positionRotor3, int rotorOutput2, List<Integer> rotor3Scrambler) {
        int rotorInput3;
        if (positionRotor2 > positionRotor3) {
            rotorInput3 = rotorOutput2 - (positionRotor2 - positionRotor3);
        } else {
            if (positionRotor2 < positionRotor3) {
                rotorInput3 = rotorOutput2 + (positionRotor3 - positionRotor2);
            } else {
                rotorInput3 = rotorOutput2;
            }
        }

        if (rotorInput3 > 25) {
            rotorInput3 = rotorInput3 - 26;
        } else {
            if (rotorInput3 < 0) {
                rotorInput3 = rotorInput3 + 26;
            }
        }

        return rotor3Scrambler.get(rotorInput3);
    }

    // Reverser output
    private static int getOutputForReverser(int positionRotor3, int rotorOutput3, List<Integer> reverserScrambler) {
        int reverserInput;
        reverserInput = rotorOutput3 - positionRotor3;

        if (reverserInput > 25) {
            reverserInput = reverserInput - 26;
        } else {
            if (reverserInput < 0) {
                reverserInput = reverserInput + 26;
            }
        }
        return reverserScrambler.get(reverserInput);
    }

    // Rotor 3 output when going BACK
    private static int getOutputForRotor3Back(int positionRotor3, int reverserOutput, int reverserInput, List<Integer> rotor3Scrambler) {
        int rotorInput3;
        rotorInput3 = reverserOutput + positionRotor3;

        if (rotorInput3 > 25) {
            rotorInput3 = rotorInput3 - 26;
        } else {
            if (reverserInput < 0) {
                rotorInput3 = rotorInput3 + 26;
            }
        }

        return rotor3Scrambler.get(rotorInput3);
    }

    // Rotor 2 output when going BACK
    private static int getOutputForRotor2Back(int positionRotor3, int positionRotor2, int rotorOutput3, List<Integer> rotor2Scrambler) {
        int rotorInput2;
        if (positionRotor3 > positionRotor2) {
            rotorInput2 = rotorOutput3 - (positionRotor3 - positionRotor2);
        } else {
            if (positionRotor3 < positionRotor2) {
                rotorInput2 = rotorOutput3 + (positionRotor2 - positionRotor3);
            } else {
                rotorInput2 = rotorOutput3;
            }
        }
        if (rotorInput2 > 25) {
            rotorInput2 = rotorInput2 - 26;
        } else {
            if (rotorInput2 < 0) {
                rotorInput2 = rotorInput2 + 26;
            }
        }
        return rotor2Scrambler.get(rotorInput2);
    }

    // Rotor 1 output when going BACK
    private static int getOutputForRotor1Back(int positionRotor2, int positionRotor1, int rotorOutput2, List<Integer> rotor1Scrambler) {
        int rotorInput1;
        if (positionRotor2 > positionRotor1) {
            rotorInput1 = rotorOutput2 - (positionRotor2 - positionRotor1);
        } else {
            if (positionRotor2 < positionRotor1) {
                rotorInput1 = rotorOutput2 + (positionRotor1 - positionRotor2);
            } else {
                rotorInput1 = rotorOutput2;
            }
        }
        if (rotorInput1 > 25) {
            rotorInput1 = rotorInput1 - 26;
        } else {
            if (rotorInput1 < 0) {
                rotorInput1 = rotorInput1 + 26;
            }
        }
        return rotor1Scrambler.get(rotorInput1);
    }

    // Result letter (checks the result Integer and changes it back into Character)
    private static char getResultLetter(int positionRotor1, int rotorOutput1, List<Character> alphabet) {
        char resultLetter;
        int resultDigit;
        resultDigit = rotorOutput1 - positionRotor1;
        if (rotorOutput1 < 26 && rotorOutput1 >= 0) {
            if (resultDigit < 0) {
                resultLetter = alphabet.get(resultDigit + 26);
            } else {
                resultLetter = alphabet.get(rotorOutput1 - positionRotor1);
            }
        } else {
            if (resultDigit > 25) {
                resultLetter = alphabet.get(resultDigit - 26);
            } else {
                resultLetter = alphabet.get(resultDigit + 26);
            }
        }
        return resultLetter;
    }
}