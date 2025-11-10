package it.unibo.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 2000;

    private static final int TEST_ELEMENTS = 100000;
    private static final int READ_ELEM = 1000;

    private static final long AFRICA = 1_110_635_000L;
    private static final long AMERICAS = 972_005_000L;
    private static final long ANTARCTICA = 0L;
    private static final long ASIA = 4_298_723_000L;
    private static final long EUROPE = 742_452_000L;
    private static final long OCEANIA = 38_304_000L;


    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> list = new ArrayList<>();
        for(int i = MIN_VALUE; i < MAX_VALUE; i++) {
            list.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> list2 = new LinkedList<>(list);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int firstValue = list.getFirst();
        list.set(0, list.getLast());
        list.set(list.size() - 1, firstValue);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        StringBuilder string = new StringBuilder();
        for(Integer i : list) {
            string.append(i + ",");
        }
        System.out.println(string);
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for(int i = 0; i < TEST_ELEMENTS; i++) {
            list.addFirst(i);
        }
        System.out.println("Adding 100.000 elements to ArrayList use " + time);

        time = System.nanoTime() - time;
        for(int i = 0; i < TEST_ELEMENTS; i++) {
            list2.addFirst(i);
        }
        System.out.println("Adding 100.000 elements to LinkedList use " + time);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime() - time;
        for(int i = 0; i < READ_ELEM; i++) {
            list.get(list.size() / 2);
        }
        System.out.println("Reading 1.000 elements from central position of the ArrayList use " + time);

        time = System.nanoTime() - time;
        for(int i = 0; i < READ_ELEM; i++) {
            list2.get(list2.size() / 2);
        }
        System.out.println("Reading 1.000 elements from central position of the LinkedList use " + time);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> map = new TreeMap<>();
        map.put("Africa", AFRICA);
        map.put("Americas", AMERICAS);
        map.put("Antarctica", ANTARCTICA);
        map.put("Asia", ASIA);
        map.put("Europe", EUROPE);
        map.put("Oceania", OCEANIA);
        /*
         * 8) Compute the population of the world
         */
        long worldPop = 0;
        for(long n: map.values()) {
            worldPop = worldPop + n;
        }
        System.out.println("The total world population is " + worldPop);
    }
}
