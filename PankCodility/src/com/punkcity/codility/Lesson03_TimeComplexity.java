package com.punkcity.codility;

/**
 * This covers 3 problems on Time Complexity:
 * 1. FrogJmp: Count minimal number of jumps from position X to Y.
 * 2. PermMissingElem: Find the missing element in a given permutation.
 * 3. TapeEquilibrium: Minimize the value |(A[0] + A[1] + ... + A[P-1]) - (A[P] + A[P+1] + ... + A[N-1])|.
 */
public class Lesson03_TimeComplexity {
    public Lesson03_TimeComplexity() {
        super();
    }

    public void runTestsForTimeComplexity() {
        System.out.println("*************** TESTS for - TIME COMPLEXITY problems ***************");
        //1. FrogJmp
        System.out.printf("FrogJmp for START=10, END=85, STEP=30 Expected 3 . . Found: [%d]%n", getMinimalJumpsBitShift(10, 85, 30));
        System.out.printf("FrogJmp for START=10, END=50, STEP=10 Expected 4 . . Found: [%d]%n", getMinimalJumpsBitShift(10, 50, 10));
        System.out.printf("FrogJmp for START=10, END=50, STEP=10 Expected 4 . . Found: [%d]%n", getMinimalJumpsBitShift(10, 50, 10));
        System.out.println(" - - - - - - ");

        //2. PermMissingElem
        System.out.printf("PermMissingElem for [2, 3, 1, 5] Expected 4 . . Found: [%d]%n", findMissingElementV1(new int[]{2, 3, 1, 5}));
        System.out.printf("PermMissingElem APPROACH-2  [2, 3, 1, 5] Expected 4 . . Found: [%d]%n", findMissingElement_V2(new int[]{2, 3, 1, 5}));
        System.out.printf("PermMissingElem APPROACH-3  [2, 3, 1, 5, 6, 9, 4, 8] Expected 7 . . Found: [%d]%n", findMissingElement_V3(new int[]{2, 3, 1, 5, 6, 9, 4, 8}));
        compareMissingElementPerformances();
        System.out.println(" - - - - - - ");

        //3. TapeEquilibrium
        System.out.printf("TapeEquilibrium for [3, 1, 2, 4, 3] Expected 1 . . Found: [%d]%n", getMinValueForTapeEquilibrium(new int[]{3, 1, 2, 4, 3}));
    }

    /**
     * This method calculates the minimal number of jumps from position X to Y.
     * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants
     * to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
     * Count the minimal number of jumps that the small frog must perform to reach its target.
     *
     * Write a function: class Solution { public int solution(int X, int Y, int D); }
     * that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
     * For example, given:
     *   X = 10
     *   Y = 85
     *   D = 30
     * the function should return 3, because the frog will be positioned as follows:
     *      after the first jump, at position 10 + 30 = 40
     *      after the second jump, at position 10 + 30 + 30 = 70
     *      after the third jump, at position 10 + 30 + 30 + 30 = 100
     * Write an "efficient" algorithm for the following assumptions:
     *      X, Y and D are integers within the range [1..1,000,000,000];
     *      X ≤ Y.
     * @param start - the starting position of the frog
     * @param end - the position the frog wants to reach
     * @param step - the fixed distance the frog jumps
     * @return - the minimal number of jumps the frog must perform to reach its target
     */
    public int getMinimalJumpsBitShift(int start, int end, int step) {
        int distance = end - start;
        int jumps = distance / step;
        if (distance % step > 0) {
            jumps++;
        }
        return jumps;
    }

    /**
     * Find the missing element in a given permutation.
     * An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)],
     * which means that exactly one element is missing.
     * Your goal is to find that missing element.
     *
     * Write a function:    class Solution { public int solution(int[] A); }
     * that, given an array A, returns the value of the missing element.
     * For example, given array A such that:
     *   A[0] = 2
     *   A[1] = 3
     *   A[2] = 1
     *   A[3] = 5
     * the function should return 4, as it is the missing element.
     *
     * Write an efficient algorithm for the following assumptions:
     *
     * N is an integer within the range [0..100,000];
     * the elements of A are all distinct;
     * each element of array A is an integer within the range [1..(N + 1)].
     * @param arr - the array of integers
     * @return - the missing element in the array
     */
    public int findMissingElementV1(int[] arr) {
        int missingElement = arr.length + 1;
        for (int i = 0; i < arr.length; i++) {
            missingElement ^= arr[i];
            missingElement ^= (i + 1);
        }
        return missingElement;
    }

    public int findMissingElement_V2(int[] arr) {
        // formula which Carl Friedrich Gauss discovered as a kid,
        //   sum of first N natural numbers = (N * (N + 1))/2.
        int idealSum = (arr.length + 1) * (arr.length + 2) / 2;
        int actualSum = 0;
        for (int i = 0; i < arr.length; i++) {
            actualSum += arr[i];
        }
        return idealSum - actualSum;
    }

    /**
     * Generally finding this to be a faster approach than the previous ones.
     * @param arr
     * @return
     */
    public int findMissingElement_V3(int[] arr) {
        int idealSum = arr.length + 1; // Because ideally the array should have N+1 elements.
        int actualSum = 0;
        for (int i = 0; i < arr.length; i++) {
            actualSum += arr[i];
            idealSum += (i + 1); //Because array values start from 1; index starts from zero
        }
        return idealSum - actualSum;
    }

    public void compareMissingElementPerformances() {
        int[] arr = new int[]{2, 3, 1, 5, 9, 8, 4, 6};
        int iterations = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            findMissingElementV1(arr);
        }
        System.out.println("Running million times - findMissingElementV1 - took: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            findMissingElement_V2(arr);
        }
        System.out.println("Running million times - findMissingElement V2 - took: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            findMissingElement_V3(arr);
        }
        System.out.println("Running million times - findMissingElement LAST APPROACH - took: " + (System.currentTimeMillis() - start) + "ms");

    }

    /*
        A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.
        Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

        The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
        In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
        For example, consider array A such that:
              A[0] = 3
              A[1] = 1
              A[2] = 2
              A[3] = 4
              A[4] = 3
        We can split this tape in four places:
            P = 1, difference = |3 − 10| = 7
            P = 2, difference = |4 − 9| = 5
            P = 3, difference = |6 − 7| = 1
            P = 4, difference = |10 − 3| = 7
        Write a function:
            class Solution { public int solution(int[] A); }
        that, given a non-empty array A of N integers, returns the "MINIMAL" difference that can be achieved.

        For example, given:
          A[0] = 3
          A[1] = 1
          A[2] = 2
          A[3] = 4
          A[4] = 3
        the function should return 1, as explained above.

        Write an efficient algorithm for the following assumptions:
            N is an integer within the range [2..100,000];
            each element of array A is an integer within the range [−1,000..1,000].
     */
    public int getMinValueForTapeEquilibrium(int[] arr) {
        int minimalDifference = Integer.MAX_VALUE;
        int leftSum = 0;
        int rightSum = 0;
        //First calculate the sum of all elements in the array
        for (int i = 0; i < arr.length; i++) {
            rightSum += arr[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            leftSum += arr[i];
            rightSum -= arr[i];
            int difference = Math.abs(leftSum - rightSum);
            if (difference < minimalDifference) {
                minimalDifference = difference;
            }
        }
        return minimalDifference;
    }
}
