package org.stoens.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.stoens.util.AlphabetPermutationsGenerator;

import junit.framework.TestCase;

/**
 * @author simontoens
 */
public class AlphabetPermutationsGeneratorTest extends TestCase {

    public void testNumberOfPermutations() {
        assertEquals(1, new AlphabetPermutationsGenerator("a", 1).getNumberOfPermutations());
        assertEquals(2, new AlphabetPermutationsGenerator("a", 2).getNumberOfPermutations());
        assertEquals(3, new AlphabetPermutationsGenerator("a", 3).getNumberOfPermutations());

        assertEquals(3, new AlphabetPermutationsGenerator("abc", 1).getNumberOfPermutations());
        assertEquals(12, new AlphabetPermutationsGenerator("abc", 2).getNumberOfPermutations());
        assertEquals(39, new AlphabetPermutationsGenerator("abc", 3).getNumberOfPermutations());
    }

    public void testSingleLetterWordSingleLetterAlphabetIterator() {
        Iterator<String> iter = new AlphabetPermutationsGenerator("a", 1).iterator();
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertFalse(iter.hasNext());
    }

    public void testTwoLetterWordSingleLetterAlphabetIterator() {
        Iterator<String> iter = new AlphabetPermutationsGenerator("a", 2).iterator();
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("aa", iter.next());
        assertFalse(iter.hasNext());
    }

    public void testSingleLetterWordTwoLetterAlphabetIterator() {
        Iterator<String> iter = new AlphabetPermutationsGenerator("ab", 1).iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertFalse(iter.hasNext());
    }

    public void testTwoLetterWordTwoLetterAlphabetIterator() {
        Iterator<String> iter = new AlphabetPermutationsGenerator("ab", 2).iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("aa", iter.next());
        assertEquals("ab", iter.next());
        assertEquals("ba", iter.next());
        assertEquals("bb", iter.next());
        assertFalse(iter.hasNext());
    }

    public void testThreeLetterWordThreeLetterAlphabetIterator() {
        Iterator<String> iter = new AlphabetPermutationsGenerator("abc", 3).iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("c", iter.next());
        assertEquals("aa", iter.next());
        assertEquals("ab", iter.next());
        assertEquals("ac", iter.next());
        assertEquals("ba", iter.next());
        assertEquals("bb", iter.next());
        assertEquals("bc", iter.next());
        assertEquals("ca", iter.next());
        assertEquals("cb", iter.next());
        assertEquals("cc", iter.next());
        assertEquals("aaa", iter.next());
        assertEquals("aab", iter.next());
        assertEquals("aac", iter.next());
        assertEquals("aba", iter.next());
        assertEquals("abb", iter.next());
        assertEquals("abc", iter.next());
        assertEquals("aca", iter.next());
        assertEquals("acb", iter.next());
        assertEquals("acc", iter.next());
        assertEquals("baa", iter.next());
        assertEquals("bab", iter.next());
        assertEquals("bac", iter.next());
        assertEquals("bba", iter.next());
        assertEquals("bbb", iter.next());
        assertEquals("bbc", iter.next());
        assertEquals("bca", iter.next());
        assertEquals("bcb", iter.next());
        assertEquals("bcc", iter.next());
        assertEquals("caa", iter.next());
        assertEquals("cab", iter.next());
        assertEquals("cac", iter.next());
        assertEquals("cba", iter.next());
        assertEquals("cbb", iter.next());
        assertEquals("cbc", iter.next());
        assertEquals("cca", iter.next());
        assertEquals("ccb", iter.next());
        assertEquals("ccc", iter.next());
        assertFalse(iter.hasNext());
    }

    public void testForEach() {
        List<String> permutations = new ArrayList<String>();
        AlphabetPermutationsGenerator generator = new AlphabetPermutationsGenerator("ab", 2);
        for (String permutation : generator) {
            permutations.add(permutation);
        }
        assertEquals(Arrays.asList("a", "b", "aa", "ab", "ba", "bb"), permutations);
    }

    public void testIteratorCanBeCalledMultipleTimesAndResetsIterationState() {
        AlphabetPermutationsGenerator generator = new AlphabetPermutationsGenerator("ab", 2);
        Iterator<String> iter = generator.iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("aa", iter.next());

        iter = generator.iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("aa", iter.next());
    }
}