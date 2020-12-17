import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class MainJUnitTest {
//}

//import static org.junit.Assert.*;
//
//        import java.util.*;
//        import org.junit.Before;
//        import org.junit.Test;

/**
 * This tester uses JUnit 4, please add that to your build path.
 *
 * This tester aims to firstly test basic usage of the hash maps, and then will
 * test edge cases after said basic usage.
 *
 *
 */
public class MainJUnitTest {

    // The seed will be different every single time this test is run, and will be
    // printed at the beginning of each test, so it can be reused through this.
    int seed = (int) System.nanoTime(); // Can change to custom seed

    @Before
    public void setup() {

    }

    @Test
    public void testChainingHash() {
        System.out.println("Seed: " + seed);
        /* Test logic follows: */
        Chaining c = new Chaining(10, 0, -1);
        assertEquals(30, c.chain(1));
        assertEquals(6, c.chain(36));
    }

    /**
     * Test if you can insert into chaining table when there are no elements in the
     * bucket
     */
    @Test
    public void testChainingBasicInsert() {
        Chaining c = new Chaining(10, 0, -1);
        int[] keyArray = generateRange(1, 10, true);
        int collisions = c.insertKeyArray(keyArray);
        assertEquals(0, collisions);
    }

    /**
     * Test if you can insert into chaining table when there are elements in the
     * bucket and value that exceed the elements in the bucket
     */
    @Test
    public void testChainingCollionInsert() {
        Chaining c = new Chaining(3, 0, -1);
        int[] keyArray = generateRange(1, 22, true);
        int collisions = c.insertKeyArray(keyArray);
        assertNotEquals(0, collisions);
    }

    /**
     * Check if probing works with i = 0
     */
    @Test
    public void testProbeHash() {
        Open_Addressing p = new Open_Addressing(10, 0, -1);
        assertEquals(30, p.probe(1, 0));
        assertEquals(0, p.probe(1, 2)); // m > i > 0
    }

    /**
     * insert with no conflicting probes
     */
    @Test
    public void testProbeBasicInsert() {
        Open_Addressing p = new Open_Addressing(10, 0, -1);
        int[] keyArray = generateRange(1, 10, true);
        int jumps = p.insertKeyArray(keyArray);
        assertEquals(0, jumps);
    }

    /**
     * Conflicting probes
     */
    @Test
    public void testProbeConflictInsert() {
        Open_Addressing p = new Open_Addressing(3, 0, -1);
        int[] keyArray = { 2, 2 };
        int jumps = p.insertKeyArray(keyArray);
        assertEquals(1, jumps);
    }

    /**
     * Too many probes in the table to add any.
     */
    @Test
    public void testProbeOverflowInsert() {
        Open_Addressing p = new Open_Addressing(2, 0, -1);
        int[] keyArray = { 1, 2, 3 };
        p.insertKeyArray(keyArray);
        int jumps = p.insertKey(5);
        assertEquals(2, jumps);
    }

    /**
     * remove from when there are no jumps
     */
    @Test
    public void testProbeRemoval() {
        Open_Addressing p = new Open_Addressing(10, 0, -1);
        int[] keyArray = generateRange(1, 10, true);
        p.insertKeyArray(keyArray);
        int jumps1 = p.removeKey(1);
        int removedVal1 = p.Table[p.probe(1, 0)];
        int jumps2 = p.removeKey(2);
        int removedVal2 = p.Table[p.probe(2, 0)];
        assertEquals(0, jumps1);
        assertNotEquals(1, removedVal1);
        assertEquals(0, jumps2);
        assertNotEquals(2, removedVal2);
    }

    /**
     * Remove with some jumps
     */
    public void testProbeJumpingRemoval() {
        Open_Addressing o = new Open_Addressing(2, 0, -1);
        int[] keyArray = { 1, 2 };
        o.insertKeyArray(keyArray);
        o.removeKey(2);
        int removedVal = o.Table[o.probe(2, 1)];
        int keptVal = o.Table[o.probe(1, 0)];

        assertNotEquals(2, removedVal);
        assertEquals(1, keptVal);
    }

    /**
     * Remove when the key is is not in the table
     */
    public void testProbeMissingRemoval() {
        Open_Addressing o = new Open_Addressing(10, 0, -1);
        int jumps = o.removeKey(2);
        int removedVal = o.Table[o.probe(2, 0)];
        assertEquals(-1, removedVal);
        assertEquals(o.m, jumps);
    }

    // ---HELPER FUNCTIONS---

    /**
     * Pretty print the values of the table in the Chaining hash map.
     *
     * @param chain
     */
    public static void printArrayTable(Chaining chain) {
        printArrayTable(chain.Table);
    }

    /**
     * Pretty print the values of the an ArrayList table of Integers.
     *
     * @param table
     */
    public static void printArrayTable(ArrayList<ArrayList<Integer>> table) {
        System.out.println("---");
        for (ArrayList<Integer> a : table) {
            System.out.print("[");
            for (Integer i : a) {
                System.out.print(" " + i + " ");
            }
            System.out.println("]");
        }
        System.out.println("---");
    }

    /**
     * Generate range of numbers from start..stop, non-inclusive.
     *
     * @param  start
     * @param  stop
     * @return
     */
    public static int[] generateRange(int start, int stop) {
        return generateRange(start, stop, false);
    }

    /**
     * Generate range of numbers from start..stop, inclusive optional.
     *
     * @param  start
     * @param  stop
     * @param  inclusive
     * @return
     */
    public static int[] generateRange(int start, int stop, boolean inclusive) {
        int b = Math.max(start, stop);
        int a = Math.min(start, stop);
        int in = inclusive ? 1 : 0;
        int[] result = new int[b - a + in];
        for (int i = 0; i < result.length; i++) {
            result[i] = i + a;
        }

        return result;
    }
}