package org.stoens.util;

import java.util.Iterator;

/**
 * Given an alphabet and a max word length, generates all word permutations for
 * that alphabet.</p>
 * 
 * For example:</p>
 * 
 * <pre>
 * final String alphabet = &quot;ab&quot;;
 * final int maxWordLength = 2;
 * AlphabetPermutationsGenerator generator = new AlphabetPermutationsGenerator(alphabet, maxWordLength);
 * for (String permutation : generator) {
 *     System.out.println(permutation);
 * }
 * </pre>
 * 
 * Output is: "a", "b", "aa", "ab", "ba", "bb" </p> </p>
 * 
 * @author simontoens
 */
public class AlphabetPermutationsGenerator implements Iterable<String> {

    private final String alphabet;
    private final int maxWordLength;

    private final long numberOfPermutations;

    public AlphabetPermutationsGenerator(String alphabet, int maxWordLength) {
        if (alphabet == null || alphabet.length() == 0) {
            throw new IllegalArgumentException("Invalid value for alphabet: " + alphabet);
        }
        this.alphabet = alphabet;
        this.maxWordLength = maxWordLength;
        this.numberOfPermutations = calculateTotalNumberOfPermutations(alphabet.length(), maxWordLength);
    }

    /**
     * Returns a new iterator over all permutations.
     */
    @Override
    public Iterator<String> iterator() {
        return new PermutationsGeneratorIterator();
    }

    /**
     * Returns the total number of permutations this
     * {@link AlphabetPermutationsGenerator} will generate.
     */
    public long getNumberOfPermutations() {
        return numberOfPermutations;
    }

    private static long calculateTotalNumberOfPermutations(int numberOfCharacters, int wordLength) {
        long numberOfPermutations = 0;
        for (int i = 0; i < wordLength; i++) {
            numberOfPermutations += Math.pow(numberOfCharacters, i + 1);
        }
        return numberOfPermutations;
    }

    private class PermutationsGeneratorIterator implements Iterator<String> {

        private final int[] counterDigits;

        private int rightmostDigitIndex;
        private long iterationCounter;

        private PermutationsGeneratorIterator() {
            this.rightmostDigitIndex = 0;
            this.iterationCounter = 0;
            this.counterDigits = new int[maxWordLength];
        }

        @Override
        public String next() {
            String currentWord = getCurrentWord();
            increment();
            return currentWord;
        }

        private void increment() {
            int indexOfDigitToIncrease = rightmostDigitIndex;
            while (indexOfDigitToIncrease >= 0 && counterDigits[indexOfDigitToIncrease] == alphabet.length() - 1) {
                counterDigits[indexOfDigitToIncrease] = 0;
                indexOfDigitToIncrease -= 1;
            }
            if (indexOfDigitToIncrease == -1) {
                rightmostDigitIndex += 1;
            } else {
                counterDigits[indexOfDigitToIncrease] += 1;
            }
            iterationCounter += 1;
        }

        private String getCurrentWord() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= rightmostDigitIndex; i++) {
                int alphabetIndex = counterDigits[i];
                sb.append(alphabet.charAt(alphabetIndex));
            }
            return sb.toString();
        }

        @Override
        public boolean hasNext() {
            return iterationCounter < numberOfPermutations;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}