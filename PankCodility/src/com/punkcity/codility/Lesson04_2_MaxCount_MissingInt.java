package com.punkcity.codility;

public class Lesson04_2_MaxCount_MissingInt {

    public static void main(String[] args) {
        new Lesson04_2_MaxCount_MissingInt().runTestsForMaxCount_MissingInt();
    }

    public Lesson04_2_MaxCount_MissingInt() {
        super();
    }

    public void runTestsForMaxCount_MissingInt() {
        System.out.println("*************** (Part 2) TESTS for - COUNTING ELEMENTS problems ***************");
        //3. MaxCounters
        System.out.printf("MaxCounters for N=5, A=[3, 4, 4, 6, 1, 4, 4] Expected [3, 2, 2, 4, 2] . . Found: [%s]%n",
                java.util.Arrays.toString(getMaxCounters(5, new int[]{3, 4, 4, 6, 1, 4, 4})));
        System.out.printf("MaxCounters for N=5, A=[3, 4, 4, 6, 1, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6] Expected [4, 4, 4, 4, 4] . . Found: [%s]%n",
                java.util.Arrays.toString(getMaxCounters(5, new int[]{3, 4, 4, 6, 1, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6})));
        System.out.println(" - - - - - - ");

        //4. MissingInteger
        System.out.printf("MissingInteger for [1, 3, 6, 4, 1, 2] Expected 5 . . Found: [%d]%n", getMissingInteger(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.printf("MissingInteger for [1, 2, 3] Expected 4 . . Found: [%d]%n", getMissingInteger(new int[]{1, 2, 3}));
        System.out.printf("MissingInteger for [-1, -3] Expected 1 . . Found: [%d]%n", getMissingInteger(new int[]{-1, -3}));
        System.out.println(" - - - - - - ");
    }

    /**
     * MAX_COUNTERS: This method calculates the value of every counter after all operations.
     * You are given N counters, initially set to 0, and you have two possible operations on them:
     *      increase(X) − counter X is increased by 1,
     *      max counter − all counters are set to the maximum value of any counter.
     * A non-empty array A of M integers is given. This array represents consecutive operations:
     *      if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
     *      if A[K] = N + 1 then operation K is max counter.
     * For example, given integer N = 5 and array A such that:
     *      A[0] = 3
     *      A[1] = 4
     *      A[2] = 4
     *      A[3] = 6
     *      A[4] = 1
     *      A[5] = 4
     *      A[6] = 4
     * the values of the counters after each consecutive operation will be:
     *      (0, 0, 1, 0, 0)
     *      (0, 0, 1, 1, 0)
     *      (0, 0, 1, 2, 0)
     *      (2, 2, 2, 2, 2)
     *      (3, 2, 2, 2, 2)
     *      (3, 2, 2, 3, 2)
     *      (3, 2, 2, 4, 2)
     * The goal is to calculate the value of every counter after all operations.
     * Write a function: class Solution { public int[] solution(int N, int[] A); }
     * that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing the values of the counters.
     * Result array should be returned as an array of integers.
     * For example, given:
     *      A[0] = 3
     *      A[1] = 4
     *      A[2] = 4
     *      A[3] = 6
     *      A[4] = 1
     *      A[5] = 4
     *      A[6] = 4
     * the function should return [3, 2, 2, 4, 2], as explained above.
     *
     * Write an efficient algorithm for the following assumptions:
     *      N and M are integers within the range [1..100,000];
     *      each element of array A is an integer within the range [1..N + 1].
     */
    public int[] getMaxCounters(int N, int[] A) {
        int[] counters = new int[N];
        int maxCounter = 0;
        int lastMaxCounter = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == N + 1) {
                lastMaxCounter = maxCounter;
            } else {
                int index = A[i] - 1;
                if (counters[index] < lastMaxCounter) {
                    counters[index] = lastMaxCounter + 1;
                } else {
                    counters[index]++;
                }
                if (counters[index] > maxCounter) {
                    maxCounter = counters[index];
                }
            }
        }
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] < lastMaxCounter) {
                counters[i] = lastMaxCounter;
            }
        }
        return counters;
    }

    /**
     * MissingInteger: This method finds the smallest positive integer that does not occur in a given sequence.
     * Write a function:
     *      class Solution { public int solution(int[] A); }
     * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
     *
     * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
     * Given A = [1, 2, 3], the function should return 4.
     * Given A = [−1, −3], the function should return 1.
     *
     * Write an efficient algorithm for the following assumptions:
     *      - N is an integer within the range [1..100,000];
     *      - each element of array A is an integer within the range [−1,000,000..1,000,000].
     * @param srcArr
     * @return
     */
    public int getMissingInteger(int[] srcArr) {
        int[] counterArray = new int[srcArr.length];
        for (int i = 0; i < srcArr.length; i++) {
            if (srcArr[i] > 0 && srcArr[i] <= srcArr.length) {
                counterArray[srcArr[i] - 1] = 1;
            }
        }
        for (int i = 0; i < counterArray.length; i++) {
            if (counterArray[i] == 0) {
                return i + 1;
            }
        }
        //If not found in the array, means the missing number is the next number after the last number in the array
        // OR 1 if array was empty
        return srcArr.length + 1;
    }
}
