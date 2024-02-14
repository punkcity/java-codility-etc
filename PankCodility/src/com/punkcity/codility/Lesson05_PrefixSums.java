package com.punkcity.codility;

/**
 * This covers 4 problems:
 * 1. PassingCars: Count the number of passing cars on the road.
 * 2. CountDiv: Compute number of integers divisible by k in range [a..b].
 * 3. GenomicRangeQuery: Find the minimal nucleotide from a range of sequence DNA.
 * 4. MinAvgTwoSlice: Find the starting position of the slice with the minimal average.
 */
public class Lesson05_PrefixSums {
    public static void main(String[] args) {
        new Lesson05_PrefixSums().runTestsForPrefixSums();
    }

    //Default constructor for the class.
    public Lesson05_PrefixSums() {
        super();
    }

    public void runTestsForPrefixSums() {
        System.out.println("*************** TESTS for - PREFIX SUMS problems ***************");
        //1. PassingCars
        System.out.printf("PassingCars for [0, 1, 0, 1, 1] Expected 5 . . Found: [%d]%n", getNumberOfPairsOfPassingCars(new int[]{0, 1, 0, 1, 1}));
        System.out.printf("PassingCars for [0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1] Expected 39 . . Found: [%d]%n",
                getNumberOfPairsOfPassingCars(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1}));
        System.out.println(" - - - - - - ");

        //2. CountDiv
        System.out.printf("CountDiv for A=6, B=11, K=2 Expected 3 . . Found: [%d]%n", getNumberOfIntegersDivisibleByK(6, 11, 2));
        System.out.printf("CountDiv for A=2, B=35, K=4 Expected 8 . . Found: [%d]%n", getNumberOfIntegersDivisibleByK(2, 35, 4));
        System.out.println(" - - - - - - ");

        //3. GenomicRangeQuery
        System.out.printf("GenomicRangeQuery for S=CAGCCTA, P=[2, 5, 0] and Q=[4, 5, 6] Expected [2, 4, 1] . . Found: [%s]%n",
                java.util.Arrays.toString(getMinimalNucleotides("CAGCCTA", new int[]{2, 5, 0}, new int[]{4, 5, 6})));
        System.out.println(" - - - - - - ");

        //4. MinAvgTwoSlice
        System.out.printf("MinAvgTwoSlice for [4, 2, 2, 5, 1, 5, 8] Expected 1 . . Found: [%d]%n",
                getStartingPositionOfSliceWithMinimalAverage(new int[]{4, 2, 2, 5, 1, 5, 8}));
        System.out.printf("MinAvgTwoSlice for [4, 2, 3, 1, 1, 1, 2, 5, 1, 5, 8] Expected 3 . . Found: [%d]%n",
                getStartingPositionOfSliceWithMinimalAverage(new int[]{4, 2, 3, 1, 1, 1, 2, 5, 1, 5, 8}));
        System.out.println(" - - - - - - ");
    }

    /**
     * A non-empty array A consisting of N integers is given. The consecutive elements of array A represent "consecutive" cars on a road.
     * Array A contains only 0s and/or 1s:
     *      0 represents a car traveling east,
     *      1 represents a car traveling west.
     * The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is
     * traveling to the east and Q is traveling to the west.
     *
     * For example, consider array A such that:
     *
     *   A[0] = 0
     *   A[1] = 1
     *   A[2] = 0
     *   A[3] = 1
     *   A[4] = 1
     * We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4). So the function should return 5.
     *
     * Write a function:    class Solution { public int solution(int[] A); }
     * that, given a non-empty array A of N integers, returns the number of pairs of passing cars.
     * The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.
     *
     * Write an efficient algorithm for the following assumptions:
     *      N is an integer within the range [1..100,000];
     *      each element of array A is an integer that can have one of the following values: 0, 1.
     * @param carsArr
     * @return
     */
    public int getNumberOfPairsOfPassingCars(int[] carsArr) {
        int westCars = 0;
        int passingCars = 0;
        for (int i = 0; i < carsArr.length; i++) {
            if (carsArr[i] == 0) {
                westCars++;
            } else {
                passingCars += westCars;
            }
            if (passingCars > 1000000000) {
                return -1;
            }
        }
        return passingCars;
    }

/**
     * Compute number of integers divisible by k in range [a..b].
     * Write a function:
     *      class Solution { public int solution(int A, int B, int K); }
     * that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K,
     * i.e.: { i : A ≤ i ≤ B, i mod K = 0 }
     * For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers
     * divisible by 2 within the range [6..11], namely 6, 8 and 10.
     * Write an efficient algorithm for the following assumptions:
     *      A and B are integers within the range [0..2,000,000,000];
     *      K is an integer within the range [1..2,000,000,000];
     *      A ≤ B.
     * @param A
     * @param B
     * @param K
     * @return
     */
    public int getNumberOfIntegersDivisibleByK(int A, int B, int K) {
        int offset = A % K == 0 ? 1 : 0;
        return (B / K) - (A / K) + offset;
        //3 to 33. K=4, 3/4=0, 33/4=8, 8-0=8, 8+1=9  --- 4, 8 12 16 20 24 28 32
    }

    /**
     * 3: A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the
     * types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer.
     * Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer
     * several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
     *
     * The DNA sequence is given as a non-empty string S = S[0] S[1]...S[N-1] consisting of N characters.
     * There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers.
     * The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA
     * sequence between positions P[K] and Q[K] (inclusive).
     *
     * For example, consider string S = CAGCCTA and arrays P, Q such that:
     *     P[0] = 2    Q[0] = 4
     *     P[1] = 5    Q[1] = 5
     *     P[2] = 0    Q[2] = 6
     * The answers to these M = 3 queries are as follows:
     *
     * The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
     * The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
     * The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
     * Write a function:
     *
     * class Solution { public int[] solution(String S, int[] P, int[] Q); }
     *
     * that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
     *
     * Result array should be returned as an array of integers.
     *
     * For example, given the string S = CAGCCTA and arrays P, Q such that:
     *
     *     P[0] = 2    Q[0] = 4
     *     P[1] = 5    Q[1] = 5
     *     P[2] = 0    Q[2] = 6
     * the function should return the values [2, 4, 1], as explained above.
     *
     * Write an efficient algorithm for the following assumptions:
     *
     * N is an integer within the range [1..100,000];
     * M is an integer within the range [1..50,000];
     * each element of arrays P and Q is an integer within the range [0..N - 1];
     * P[K] ≤ Q[K], where 0 ≤ K < M;
     * string S consists only of upper-case English letters A, C, G, T.
     */
    public int[] getMinimalNucleotides(String S, int[] P, int[] Q) {
        int[][] impactFactors = new int[3][S.length() + 1];
        int a, c, g, t;
        for (int i = 0; i < S.length(); i++) {
            a = 0;
            c = 0;
            g = 0;
            if ('A' == S.charAt(i)) {
                a = 1;
            } else if ('C' == S.charAt(i)) {
                c = 1;
            } else if ('G' == S.charAt(i)) {
                g = 1;
            }
            impactFactors[0][i + 1] = impactFactors[0][i] + a;
            impactFactors[1][i + 1] = impactFactors[1][i] + c;
            impactFactors[2][i + 1] = impactFactors[2][i] + g;
        }
        int[] result = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            int from = P[i];
            int to = Q[i] + 1;
            if (impactFactors[0][to] - impactFactors[0][from] > 0) {
                result[i] = 1;
            } else if (impactFactors[1][to] - impactFactors[1][from] > 0) {
                result[i] = 2;
            } else if (impactFactors[2][to] - impactFactors[2][from] > 0) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }
        return result;
    }

    /**
     * 4: Minimum average of any slice
     * A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is
     * called a slice of array A (notice that the slice contains at least two elements).
     * The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice.
     * To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
     *
     * For example, array A such that:
     *     A[0] = 4
     *     A[1] = 2
     *     A[2] = 2
     *     A[3] = 5
     *     A[4] = 1
     *     A[5] = 5
     *     A[6] = 8
     * contains the following example slices:
     *      slice (1, 2), whose average is (2 + 2) / 2 = 2;
     *      slice (3, 4), whose average is (5 + 1) / 2 = 3;
     *      slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
     * The goal is to find the starting position of a slice whose average is minimal. For this set, return 1.
     *
     * Write a function:     class Solution { public int solution(int[] A); }
     * that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the
     * minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
     *
     * Write an efficient algorithm for the following assumptions:
     *      N is an integer within the range [2..100,000];
     *      each element of array A is an integer within the range [−10,000..10,000].
     */
    public int getStartingPositionOfSliceWithMinimalAverage(int[] A) {
        int minIndex = 0;
        double minAvg = (A[0] + A[1]) / 2.0;
        for (int i = 0; i < A.length - 2; i++) {
            double avg1 = (A[i] + A[i + 1]) / 2.0;
            double avg2 = (A[i] + A[i + 1] + A[i + 2]) / 3.0;
            if (avg1 < minAvg) {
                minAvg = avg1;
                minIndex = i;
            }
            if (avg2 < minAvg) {
                minAvg = avg2;
                minIndex = i;
            }
        }
        double avg3 = (A[A.length - 2] + A[A.length - 1]) / 2.0;
        if (avg3 < minAvg) {
            minIndex = A.length - 2;
        }
        return minIndex;
    }
}
