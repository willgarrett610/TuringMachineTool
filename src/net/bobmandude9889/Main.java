package net.bobmandude9889;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        CriteriaSet c1 = new CriteriaSet(
                List.of(n -> count(n, 4) == 0, n -> count(n, 4) == 1, n -> count(n, 4) == 2, n -> count(n, 4) == 3),
                List.of("0 4's", "1 4's", "2 4's", "3 4's")
        );

        CriteriaSet c2 = new CriteriaSet(
                List.of(n -> countEven(n) > 1, n -> countEven(n) < 2),
                List.of("Even > Odd", "Odd > Even")
        );

        for (int i = 0; i < c1.criteriaList.size(); i++) {
            if (isInvalid(c1.criteriaList.get(i), c2)) {
                c1.valid.set(i, false);
                System.out.printf("Invalid: %s\n", c1.labels.get(i));
            } else {
                System.out.printf("Valid: %s\n", c1.labels.get(i));
            }
        }
    }

    public static boolean isInvalid(Criteria test, CriteriaSet comp) {
        Set<Integer> works = new HashSet<>();
        for (int[] n : new NumberIterable()) {
            if (test.test(n)) {
                for (int i = 0; i < comp.criteriaList.size(); i++) {
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

}
