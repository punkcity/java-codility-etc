package com.punkcity.codility;

import java.util.Arrays;

/**
 * Solving 4 problems on Sorting:
 * 1. Distinct: Compute number of distinct values in an array.
 * 2. MaxProductOfThree: Maximize A[P] * A[Q] * A[R] for any triplet (P, Q, R).
 * 3. Triangle: Determine whether a triangle can be built from a given set of edges.
 * 4. NumberOfDiscIntersections: Compute the number of intersections in a sequence of discs.
  */
public class Lesson06_Sorting {
    public static void main(String[] args) {
        new Lesson06_Sorting().runTestsForSorting();
    }

    public Lesson06_Sorting() {
        super();
    }

    public void runTestsForSorting() {
        System.out.println("*************** TESTS for - SORTING problems ***************");
        //1. Distinct
        System.out.printf("Using getCountOfDistinctValuesNoSort for [2, 3, 1, 4, 3, 1, 2, 4, 6] Expected 5 . . Found: [%d]%n",
                getCountOfDistinctValuesNoSort(new int[]{2, 3, 1, 4, 3, 1, 2, 4, 6}));
        System.out.printf("Using getDistinctValCountSortNEasy for [2, 3, 1, 4, 3, 1, 2, 4, 6] Expected 5 . . Found: [%d]%n",
                getDistinctValCountSortNEasy(new int[]{2, 3, 1, 4, 3, 1, 2, 4, 6}));
        compareDistinctApproaches();

        System.out.println(" - - - - - - ");


        //2. MaxProductOfThree
        System.out.printf("MaxProductOfThree for [-3, 1, 2, -2, 5, 6] Expected 60 . . Found: [%d]%n", maxProductOfThree(new int[]{-3, 1, 2, -2, 5, 6}));
        System.out.printf("MaxProductOfThree for [-3, 1, 2, -2, 5, 6] Expected 60 . . Found: [%d]%n", maxProductOfThreeFASTER(new int[]{-3, 1, 2, -2, 5, 6}));
        System.out.printf("MaxProductOfThree for [-5, 5, -5, 4] Expected 125 . . Found: [%d]%n", maxProductOfThree(new int[]{-5, 5, -5, 4}));
        System.out.println(" - - - - - - ");

        //3. Triangle
        System.out.printf("Triangle for [10, 2, 5, 1, 8, 20] Expected 1 . . Found: [%d]%n", isTrianglePossible(new int[]{10, 2, 5, 1, 8, 20}));
        System.out.printf("Triangle for [10, 50, 5, 1] Expected 0 . . Found: [%d]%n", isTrianglePossible(new int[]{10, 50, 5, 1}));
        System.out.println(" - - - - - - ");

        //4. NumberOfDiscIntersections
        System.out.printf("NumberOfDiscIntersections for [1, 5, 2, 1, 4, 0] Expected 11 . . Found: [%d]%n", getNumberOfDiscIntersections(new int[]{1, 5, 2, 1, 4, 0}));
        System.out.printf("NumberOfDiscIntersections for [1, 0, 1, 0, 1] Expected 6 . . Found: [%d]%n", getNumberOfDiscIntersections(new int[]{1, 0, 1, 0, 1}));
    }

    /**
     * DISTINCT: This method computes the number of distinct values in an array.
     * Write a function: class Solution { public int solution(int[] A); }
     * that, given an array A consisting of N integers, returns the number of distinct values in array A.
     * For example, given array A such that:
     *      A[0] = 2
     *      A[1] = 1
     *      A[2] = 1
     *      A[3] = 2
     *      A[4] = 3
     * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
     * Write an efficient algorithm for the following assumptions:
     *      N is an integer within the range [0..100,000];
     *      each element of array A is an integer within the range [−1,000,000..1,000,000].
     * @param srcArr - the array to be checked
     * @return - the number of distinct values in the array
     */
    public int getCountOfDistinctValuesNoSort(int[] srcArr) {
        for (int i = 0; i < srcArr.length; i++) {
            for (int j = i + 1; j < srcArr.length; j++) {
                if (srcArr[i] == srcArr[j]) {
                    srcArr[j] = -1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < srcArr.length; i++) {
            if (srcArr[i] != -1) {
                count++;
            }
        }
        return count;
    }

    /**
     * This approach is TWICE AS FAST (At Least) - Every Run!
     *
     * NOTE: Tried to use Set - BUT IT IS 6+ times slower than this approach!
     * @param array
     * @return
     */
    public int getDistinctValCountSortNEasy(int[] array) {
        Arrays.sort(array);
        int lastVal = array[0];
        int counter = 1;
        for (int value : array) {
            if (lastVal != value) {
                counter++;
                lastVal = value;
            }
        }
        return counter;
    }

    public void compareDistinctApproaches() {
        int[] arr = new int[]{2, 3, 1, 4, 3, 9, 8, 4, 6};
        int iterations = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            getCountOfDistinctValuesNoSort(arr);
//            maxProductOfThree(new int[]{-3, 1, 2, -2, 5, 6});
        }
        System.out.println("Running million times - getCountOfDistinctValuesNoSort - took: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            getDistinctValCountSortNEasy(arr);
//            maxProductOfThreeFASTER(new int[]{-3, 1, 2, -2, 5, 6});
        }
        System.out.println("Running million times - getDistinctValCountSortNEasy - took: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * MaxProductOfThree: This method maximizes A[P] * A[Q] * A[R] for any triplet (P, Q, R).
     * A non-empty array A consisting of N integers is given.
     * The goal is to find the maximal product of any triplet.
     * Write a function: class Solution { public int solution(int[] A); }
     * that, given a non-empty array A, returns the value of the maximal product of any triplet.
     * For example, given array A such that:
     *      A[0] = -3
     *      A[1] = 1
     *      A[2] = 2
     *      A[3] = -2
     *      A[4] = 5
     *      A[5] = 6
     * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
     * Write an efficient algorithm for the following assumptions:
     *      N is an integer within the range [3..100,000];
     *      each element of array A is an integer within the range [−1,000..1,000].
     * @param src - the array to be checked
     * @return - the maximal product of any triplet
     */
    public int maxProductOfThree(int[] src) {
        //time complexity of this solution is O(N log N), where N is the length of the array.
        Arrays.sort(src);
        int n = src.length;
        return Math.max(src[0] * src[1] * src[n - 1], src[n - 3] * src[n - 2] * src[n - 1]);
        //NOTE: The maximum product of three numbers in a sorted array is either the product of the last three
        // numbers (if all numbers are positive or there is at most one negative number) or the product of the first two
        // numbers and the last number (if there are at least two negative numbers and the absolute values of the first
        // two numbers are greater than or equal to the second and third largest numbers
    }

    /**
     * This approach is FASTER as it doesn't do sorting. Time complexity is O(N)
     * BUT - NOT SEEING MAJOR DIFFERENCE IN PERFORMANCE - MAYBE DUE TO SMALLER ARRAY SIZE
     * @param src
     * @return
     */
    public int maxProductOfThreeFASTER(int[] src) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : src) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    /**
     * Triangle: This method determines whether a triangle can be built from a given set of edges.
     * An array A consisting of N integers is given.
     * A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
     *      A[P] + A[Q] > A[R],
     *      A[Q] + A[R] > A[P],
     *      A[R] + A[P] > A[Q].
     * Write a function:
     *      class Solution { public int solution(int[] A); }
     * that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
     * For example, given array A such that:
     *      A[0] = 10    A[1] = 2    A[2] = 5
     *      A[3] = 1     A[4] = 8    A[5] = 20
     * the function should return 1, because Triplet (0, 2, 4) is triangular.
     *
     * Given array A such that:
     *      A[0] = 10    A[1] = 50    A[2] = 5
     *      A[3] = 1     A[4] = 0     A[5] = 20
     * the function should return 0.
     *
     * Write an efficient algorithm for the following assumptions:
     *      N is an integer within the range [0..100,000];
     *      each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
     * @param srcArr - the array to be checked
     * @return - 1 if there exists a triangular triplet for this array and returns 0 otherwise
     */
    public int isTrianglePossible(int[] srcArr) {
        Arrays.sort(srcArr);
        for (int i = 0; i < srcArr.length - 2; i++) {
            if (srcArr[i] + srcArr[i + 1] > srcArr[i + 2]) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * NumberOfDiscIntersections: This method computes the number of intersections in a sequence of discs.
     * We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers,
     * specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].
     * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point
     * (assuming that the discs contain their borders).
     * The figure below shows discs drawn for N = 6 and A as follows:
     *     A[0] = 1
     *     A[1] = 5
     *     A[2] = 2
     *     A[3] = 1
     *     A[4] = 4
     *     A[5] = 0
     *     There are eleven (unordered) pairs of discs that intersect, namely:
     *     discs 1 and 4 intersect, and both intersect with all the other discs;
     *     disc 2 also intersects with discs 0 and 3.
     * Write a function:
     *    class Solution { public int solution(int[] A); }
     *    that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs.
     *    The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
     *    Given array A shown above, the function should return 11, as explained above.
     *    Write an efficient algorithm for the following assumptions:
     *    N is an integer within the range [0..100,000];
     *    each element of array A is an integer within the range [0..2,147,483,647].
     * @param srcArr - the array to be checked
     * @return - the number of (unordered) pairs of intersecting discs
     */
    public int getNumberOfDiscIntersections(int[] srcArr) {
        int count = 0;
        for (int i = 0; i < srcArr.length - 1; i++) {
            for (int j = i + 1; j < srcArr.length; j++) {
                if (i + srcArr[i] >= j - srcArr[j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
