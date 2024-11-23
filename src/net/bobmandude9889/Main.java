package net.bobmandude9889;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final String TRIANGLE = "TRIANGLE";
    public static final String SQUARE = "SQUARE";
    public static final String CIRCLE = "CIRCLE";
    static final List<CriteriaSet> criterias = List.of(
            // 1
            new CriteriaSet(
                    List.of(n -> n[0] == 1, n -> n[0] > 1),
                    List.of(TRIANGLE + " = 1", TRIANGLE + " > 1")
            ),
            // 2
            new CriteriaSet(
                    List.of(n -> n[0] < 3, n -> n[0] == 3, n -> n[0] > 3),
                    List.of(TRIANGLE + " < 3", TRIANGLE + " = 3", TRIANGLE + " > 3")
            ),
            // 3
            new CriteriaSet(
                    List.of(n -> n[1] < 3, n -> n[1] == 3, n -> n[1] > 3),
                    List.of(SQUARE + " < 3", SQUARE + " = 3", SQUARE + " > 3")
            ),
            // 4
            new CriteriaSet(
                    List.of(n -> n[1] < 4, n -> n[1] == 4, n -> n[1] > 4),
                    List.of(SQUARE + " < 4", SQUARE + " = 4", SQUARE + " > 4")
            ),
            // 5
            new CriteriaSet(
                    List.of(n -> n[0]%2==0, n -> n[0]%2==1),
                    List.of(TRIANGLE + " is even", TRIANGLE + " is odd")
            ),
            // 6
            new CriteriaSet(
                    List.of(n -> n[1]%2==0, n -> n[1]%2==1),
                    List.of(SQUARE + " is even", SQUARE + " is odd")
            ),
            // 7
            new CriteriaSet(
                    List.of(n -> n[2]%2==0, n -> n[2]%2==1),
                    List.of(CIRCLE + " is even", CIRCLE + " is odd")
            ),
            // 8
            new CriteriaSet(
                    List.of(n -> count(n, 1) == 0, n -> count(n, 1) == 1, n -> count(n, 1) == 2, n -> count(n, 1) == 3),
                    List.of("zero 1's", "one 1's", "two 1's", "three 1's")
            ),
            // 9
            new CriteriaSet(
                    List.of(n -> count(n, 3) == 0, n -> count(n, 3) == 1, n -> count(n, 3) == 2, n -> count(n, 3) == 3),
                    List.of("zero 3's", "one 3's", "two 3's", "three 3's")
            ),
            // 10
            new CriteriaSet(
                    List.of(n -> count(n, 4) == 0, n -> count(n, 4) == 1, n -> count(n, 4) == 2, n -> count(n, 4) == 3),
                    List.of("zero 4's", "one 4's", "two 4's", "three 4's")
            ),
            // 11
            new CriteriaSet(
                    List.of(n -> n[0] < n[1], n-> n[0] == n[1], n -> n[0] > n[1]),
                    List.of(TRIANGLE + " < " + SQUARE, TRIANGLE + " = " + SQUARE, TRIANGLE + " > " + SQUARE)
            ),
            // 12
            new CriteriaSet(
                    List.of(n -> n[0] < n[2], n-> n[0] == n[2], n -> n[0] > n[2]),
                    List.of(TRIANGLE + " < " + CIRCLE, TRIANGLE + " = " + CIRCLE, TRIANGLE + " > " + CIRCLE)
            ),
            // 13
            new CriteriaSet(
                    List.of(n -> n[1] < n[2], n-> n[1] == n[2], n -> n[1] > n[2]),
                    List.of(SQUARE + " < " + CIRCLE, SQUARE + " = " + CIRCLE, SQUARE + " > " + CIRCLE)
            ),
            // 14
            new CriteriaSet(
                    List.of(n -> n[0] < Math.min(n[1], n[2]), n -> n[1] < Math.min(n[0], n[2]), n -> n[2] < Math.min(n[1], n[0])),
                    List.of(TRIANGLE + " smallest", SQUARE + " smallest", CIRCLE + " smallest")
            ),
            // 15
            new CriteriaSet(
                    List.of(n -> n[0] > Math.max(n[1], n[2]), n -> n[1] > Math.max(n[0], n[2]), n -> n[2] > Math.max(n[1], n[0])),
                    List.of(TRIANGLE + " smallest", SQUARE + " smallest", CIRCLE + " smallest")
            ),
            // 16
            new CriteriaSet(
                    List.of(n -> countEven(n) > 1, n -> countEven(n) < 2),
                    List.of("Even > Odd", "Odd > Even")
            ),
            // 17
            new CriteriaSet(
                    List.of(n -> countEven(n) == 0, n -> countEven(n) == 1, n -> countEven(n) == 2, n -> countEven(n) == 3),
                    List.of("zero evens", "one even", "two evens", "three evens")
            ),
            // 18
            new CriteriaSet(
                    List.of(n -> sum(n)%2 == 0, n -> sum(n)%2 == 1),
                    List.of(TRIANGLE + "+" + SQUARE + "+" + CIRCLE + " = EVEN", TRIANGLE + "+" + SQUARE + "+" + CIRCLE + " = ODD")
            ),
            // 19
            new CriteriaSet(
                    List.of(n -> n[0] + n[1] < 6, n -> n[0] + n[1] == 6, n -> n[0] + n[1] > 6),
                    List.of(TRIANGLE + "+" + SQUARE + " < 6", TRIANGLE + "+" + SQUARE + " = 6", TRIANGLE + "+" + SQUARE + " > 6")
            ),
            // 20
            new CriteriaSet(
                    List.of(n -> !hasPair(n) && !hasTriple(n), Main::hasPair, Main::hasTriple),
                    List.of("No repeat", "One number repeats once", "Triple number")
            ),
            // 21
            new CriteriaSet(
                    List.of(Main::hasPair, n -> !hasPair(n)),
                    List.of("One pair", "No pairs")
            ),
            // 22
            new CriteriaSet(
                    List.of(Main::isAscending, Main::isDescending, n -> !isAscending(n) && !isDescending(n)),
                    List.of("Ascending", "Descending", "No order")
            ),
            // 23
            new CriteriaSet(
                    List.of(n -> sum(n) < 6, n -> sum(n) == 6, n -> sum(n) > 6),
                    List.of("Sum < 6", "Sum = 6", "Sum > 6")
            ),
            // 24
            new CriteriaSet(
                    List.of(n -> hasConsecutiveIncrease(n, 3), n -> hasConsecutiveIncrease(n, 2), n -> !hasConsecutiveIncrease(n, 2) && !hasConsecutiveIncrease(n, 3)),
                    List.of("3-digit increase", "2-digit increase", "No consecutive increase")
            ),
            // 25
            new CriteriaSet(
                    List.of(n -> !hasConsecutiveChange(n, 2) && !hasConsecutiveChange(n, 3), n -> hasConsecutiveChange(n, 3), n -> hasConsecutiveChange(n, 2)),
                    List.of("No order", "3-digit change", "No consecutive change")
            ),
            // 26
            new CriteriaSet(
                    List.of(n -> n[0] < 3, n -> n[1] < 3, n -> n[2] < 3),
                    List.of(TRIANGLE + " < 3", SQUARE + " < 3", CIRCLE + " < 3")
            ),
            // 27
            new CriteriaSet(
                    List.of(n -> n[0] < 4, n -> n[1] < 4, n -> n[2] < 4),
                    List.of(TRIANGLE + " < 4", SQUARE + " < 4", CIRCLE + " < 4")
            ),
            // 28
            new CriteriaSet(
                    List.of(n -> n[0] == 1, n -> n[1] == 1, n -> n[2] == 1),
                    List.of(TRIANGLE + " = 1", SQUARE + " = 1", CIRCLE + " = 1")
            ),
            // 29
            new CriteriaSet(
                    List.of(n -> n[0] == 3, n -> n[1] == 3, n -> n[2] == 3),
                    List.of(TRIANGLE + " = 3", SQUARE + " = 3", CIRCLE + " = 3")
            ),
            // 30
            new CriteriaSet(
                    List.of(n -> n[0] == 4, n -> n[1] == 4, n -> n[2] == 4),
                    List.of(TRIANGLE + " = 4", SQUARE + " = 4", CIRCLE + " = 4")
            ),

            // 31
            new CriteriaSet(
                    List.of(n -> n[0] > 1, n -> n[1] > 1, n -> n[2] > 1),
                    List.of(TRIANGLE + " > 1", SQUARE + " > 1", CIRCLE + " > 1")
            ),
            // 32
            new CriteriaSet(
                    List.of(n -> n[0] > 3, n -> n[1] > 3, n -> n[2] > 3),
                    List.of(TRIANGLE + " > 3", SQUARE + " > 3", CIRCLE + " > 3")
            ),
            // 33
            new CriteriaSet(
                    List.of(
                            n -> n[0]%2 == 0, n -> n[0]%2 == 1, n -> n[1]%2 == 0,
                            n -> n[1]%2 == 1, n -> n[2]%2 == 0, n -> n[2]%2 == 1
                    ),
                    List.of(
                            TRIANGLE + " is even", TRIANGLE + " is odd", SQUARE + " is even",
                            SQUARE + " is odd", CIRCLE + " is even", CIRCLE + "is odd"
                    )
            ),
            // 34
            new CriteriaSet(
                    List.of(n -> n[0] <= Math.min(n[1],n[2]), n -> n[1] <= Math.min(n[0],n[2]), n -> n[2] <= Math.min(n[1],n[0])),
                    List.of(TRIANGLE + " <= " + CIRCLE + " " + SQUARE, SQUARE + " <= " + CIRCLE + " " + TRIANGLE, CIRCLE + " <= " + TRIANGLE + " " + SQUARE)
            ),
            // 35
            new CriteriaSet(
                    List.of(n -> n[0] >= Math.min(n[1],n[2]), n -> n[1] >= Math.min(n[0],n[2]), n -> n[2] >= Math.min(n[1],n[0])),
                    List.of(TRIANGLE + " >= " + CIRCLE + " " + SQUARE, SQUARE + " >= " + CIRCLE + " " + TRIANGLE, CIRCLE + " >= " + TRIANGLE + " " + SQUARE)
            ),
            // 36
            new CriteriaSet(
                    List.of(n -> sum(n) % 3 == 0, n -> sum(n) % 4 == 0, n -> sum(n) % 5 == 0),
                    List.of("Sum is a multiple of 3", "Sum is a multiple of 4", "Sum is a multiple of 5")
            ),
            // 37
            new CriteriaSet(
                    List.of(n -> n[0] + n[1] == 4, n -> n[0] + n[2] == 4, n -> n[1] + n[2] == 4),
                    List.of(TRIANGLE + " + " + SQUARE + " = 4", TRIANGLE + " + " + CIRCLE + " = 4", SQUARE + " + " + CIRCLE + " = 4")
            ),
            // 38
            new CriteriaSet(
                    List.of(n -> n[0] + n[1] == 6, n -> n[0] + n[2] == 6, n -> n[1] + n[2] == 6),
                    List.of(TRIANGLE + " + " + SQUARE + " = 6", TRIANGLE + " + " + CIRCLE + " = 6", SQUARE + " + " + CIRCLE + " = 6")
            ),
            // 39
            new CriteriaSet(
                    List.of(
                            n -> n[0] == 1, n -> n[1] == 1, n -> n[2] == 1,
                            n -> n[0] > 1, n -> n[2] > 1, n -> n[2] > 1
                    ),
                    List.of(
                            TRIANGLE + " = 1", SQUARE + " = 1", CIRCLE + " = 1",
                            TRIANGLE + " > 1", SQUARE + " > 1", CIRCLE + " > 1"
                    )
            ),
            // 40
            new CriteriaSet(
                    List.of(
                            n -> n[0] < 3, n -> n[1] < 3, n -> n[2] < 3,
                            n -> n[0] == 3, n -> n[1] == 3, n -> n[2] == 3,
                            n -> n[0] > 3, n -> n[1] > 3, n -> n[2] > 3
                    ),
                    List.of(
                            TRIANGLE + " < 3", SQUARE + " < 3", CIRCLE + " < 3",
                            TRIANGLE + " = 3", SQUARE + " = 3", CIRCLE + " = 3",
                            TRIANGLE + " > 3", SQUARE + " > 3", CIRCLE + " > 3"
                    )
            ),
            // 41
            new CriteriaSet(
                    List.of(
                            n -> n[0] < 4, n -> n[1] < 4, n -> n[2] < 4,
                            n -> n[0] == 4, n -> n[1] == 4, n -> n[2] == 4,
                            n -> n[0] > 4, n -> n[1] > 4, n -> n[2] > 4
                    ),
                    List.of(
                            TRIANGLE + " < 4", SQUARE + " < 4", CIRCLE + " < 4",
                            TRIANGLE + " = 4", SQUARE + " = 4", CIRCLE + " = 4",
                            TRIANGLE + " > 4", SQUARE + " > 4", CIRCLE + " > 4"
                    )
            ),
            // 42
            new CriteriaSet(
                    List.of(
                            n -> n[0] < Math.min(n[1], n[2]), n -> n[1] < Math.min(n[0], n[2]), n -> n[2] < Math.min(n[1], n[0]),
                            n -> n[0] > Math.min(n[1], n[2]), n -> n[1] > Math.min(n[0], n[2]), n -> n[2] > Math.min(n[1], n[0])
                    ),
                    List.of(
                            TRIANGLE + " < " + CIRCLE + SQUARE, SQUARE + " < " + TRIANGLE + CIRCLE, CIRCLE + " < " + SQUARE + TRIANGLE,
                            TRIANGLE + " > " + CIRCLE + SQUARE, SQUARE + " > " + TRIANGLE + CIRCLE, CIRCLE + " > " + SQUARE + TRIANGLE
                    )
            ),
            // 43
            new CriteriaSet(
                    List.of(
                            n -> n[0] < n[1], n -> n[0] == n[1], n -> n[0] > n[1],
                            n -> n[0] < n[2], n -> n[0] == n[2], n -> n[0] > n[2]
                    ),
                    List.of(
                            TRIANGLE + " < " + SQUARE, TRIANGLE + " = " + SQUARE, TRIANGLE + " > " + SQUARE,
                            TRIANGLE + " < " + CIRCLE, TRIANGLE + " = " + CIRCLE, TRIANGLE + " > " + CIRCLE
                    )
            ),
            // 44
            new CriteriaSet(
                    List.of(
                            n -> n[1] < n[0], n -> n[1] == n[0], n -> n[1] > n[0],
                            n -> n[1] < n[2], n -> n[1] == n[2], n -> n[1] > n[2]
                    ),
                    List.of(
                            SQUARE + " < " + TRIANGLE, SQUARE + " = " + TRIANGLE, SQUARE + " > " + TRIANGLE,
                            SQUARE + " < " + CIRCLE, SQUARE + " = " + CIRCLE, SQUARE + " > " + CIRCLE
                    )
            ),
            // 45
            new CriteriaSet(
                    List.of(
                            n -> count(n, 1) == 1, n -> count(n, 1) == 2, n -> count(n, 1) == 3,
                            n -> count(n, 3) == 1, n -> count(n, 3) == 2, n -> count(n, 3) == 3
                    ),
                    List.of("One 1", "Two 1s", "Three 1s", "One 3", "Two 3s", "Three 3s")
            ),
            // 46
            new CriteriaSet(
                    List.of(
                            n -> count(n, 3) == 1, n -> count(n, 3) == 2, n -> count(n, 3) == 3,
                            n -> count(n, 4) == 1, n -> count(n, 4) == 2, n -> count(n, 4) == 3
                    ),
                    List.of("One 3", "Two 3s", "Three 3s", "One 4", "Two 4s", "Three 4s")
            ),
            // 47
            new CriteriaSet(
                    List.of(
                            n -> count(n, 1) == 1, n -> count(n, 1) == 2, n -> count(n, 1) == 3,
                            n -> count(n, 4) == 1, n -> count(n, 4) == 2, n -> count(n, 4) == 3
                    ),
                    List.of("One 1", "Two 1s", "Three 1s", "One 4", "Two 4s", "Three 4s")
            ),

            // 48
            new CriteriaSet(
                    List.of(
                            n -> n[0] < n[1], n -> n[0] < n[2], n -> n[1] < n[2],
                            n -> n[0] == n[1], n -> n[0] == n[2], n -> n[1] == n[2],
                            n -> n[0] > n[1], n -> n[0] > n[2], n -> n[1] > n[2]
                    ),
                    List.of(
                            TRIANGLE + " < " + SQUARE, TRIANGLE + " < " + CIRCLE, SQUARE + " < " + CIRCLE,
                            TRIANGLE + " = " + SQUARE, TRIANGLE + " = " + CIRCLE, SQUARE + " = " + CIRCLE,
                            TRIANGLE + " > " + SQUARE, TRIANGLE + " > " + CIRCLE, SQUARE + " > " + CIRCLE
                    )
            )

    );

    public static void main(String[] args) {
        List<CriteriaSet> list = getByNums(4,9,13,14);
        checkAll(list);
 
        for (CriteriaSet set : list) {
            for (int i = 0; i < set.valid.size(); i++) {
                if (!set.valid.get(i)) {
                    System.out.printf("%s invalid given %s\n", set.labels.get(i), set.invalidGiven.get(i).labels);
                } else {
                    System.out.printf("%s valid\n", set.labels.get(i));
                }
            }
            System.out.println();
        }
    }

    public static List<CriteriaSet> getByNums(int... nums) {
        List<CriteriaSet> list = new ArrayList<>();

        for (int n : nums) {
            list.add(criterias.get(n-1));
        }

        return list;
    }

    public static void checkAll(List<CriteriaSet> sets) {
        boolean changed = true;

        while(changed) {
            changed = false;
            for (int i = 0; i < sets.size(); i++) {
                for (int j = 0; j < sets.size(); j++) {
                    if (i==j) continue;

                    if (invalidateAgainst(sets.get(i), sets.get(j))) changed = true;
                }
            }
        }
    }

    public static boolean invalidateAgainst(CriteriaSet test, CriteriaSet comp) {
        boolean changed = false;
        for (int i = 0; i < test.criteriaList.size(); i++) {
            if (countInvalid(comp) == 1) break;
            if (isInvalid(test.criteriaList.get(i), comp)) {
                if (test.valid.get(i)) {
                    test.valid.set(i, false);
                    test.invalidGiven.set(i, comp);
                    changed = true;
                }
            }
        }
        return changed;
    }

    public static boolean isInvalid(Criteria test, CriteriaSet comp) {
        Set<Integer> works = new HashSet<>();
        for (int[] n : new NumberIterable()) {
            if (test.test(n)) {
                for (int i = 0; i < comp.criteriaList.size(); i++) {
                    if (!comp.valid.get(i)) continue;

                    if (comp.criteriaList.get(i).test(n)) {
                        works.add(i);
                        if (works.size() > 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return works.size() == 1;
    }

    public static int countInvalid(CriteriaSet set) {
        int count = 0;
        for (boolean valid : set.valid) {
            count += valid ? 1 : 0;
        }
        return count;
    }

    public static int sum(int[] num) {
        int tot = 0;
        for (int d : num) {
            tot += d;
        }
        return tot;
    }

    public static int count(int[] num, int dig) {
        int count = 0;
        for (int d : num) {
            if (d == dig) count++;
        }
        return count;
    }

    public static int countEven(int[] num) {
        int count = 0;
        for (int d : num) {
            if (d % 2 == 0) count++;
        }
        return count;
    }
    // Counts odd numbers in the array
    public static int countOdd(int[] n) {
        return (int) Arrays.stream(n).filter(num -> num % 2 == 1).count();
    }

    // Checks if there is at least one pair of identical numbers in the array
    public static boolean hasPair(int[] n) {
        return Arrays.stream(n)
                .boxed()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count == 2);
    }

    public static boolean hasTriple(int[] n) {
        return n[0] == n[1] && n[1] == n[2];
    }

    // Checks if the numbers in the array are in ascending order
    public static boolean isAscending(int[] n) {
        for (int i = 0; i < n.length - 1; i++) {
            if (n[i] >= n[i + 1]) return false;
        }
        return true;
    }

    // Checks if the numbers in the array are in descending order
    public static boolean isDescending(int[] n) {
        for (int i = 0; i < n.length - 1; i++) {
            if (n[i] <= n[i + 1]) return false;
        }
        return true;
    }

    // Checks for consecutive increasing values of a specific length in the array
    public static boolean hasConsecutiveIncrease(int[] n, int length) {
        if (length == 3) {
            return n[0] == n[1] - 1 && n[1] == n[2] - 2;
        }

        if (length == 2) {
            return n[0] == n[1] - 1 || n[1] == n[2] - 2;
        }

        return true;
    }

    public static boolean hasConsecutiveDecrease(int[] n, int length) {
        if (length == 3) {
            return n[0] == n[1] + 1 && n[1] == n[2] + 2;
        }

        if (length == 2) {
            return n[0] == n[1] + 1 || n[1] == n[2] + 2;
        }

        return true;
    }

    // Checks for consecutive changing (increasing or decreasing) values of a specific length in the array
    public static boolean hasConsecutiveChange(int[] n, int length) {
        return hasConsecutiveIncrease(n, length) || hasConsecutiveDecrease(n, length);
    }


}
