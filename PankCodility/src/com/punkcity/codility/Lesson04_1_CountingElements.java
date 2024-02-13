package com.punkcity.codility;

public class Lesson04_1_CountingElements {
    public static void main(String[] args) {
        new Lesson04_1_CountingElements().runTestsForCountingElements();
    }

    /**
     * Default constructor for the class.
     */
    public Lesson04_1_CountingElements() {
        super();
    }

    public void runTestsForCountingElements() {
        System.out.println("*************** TESTS for - COUNTING ELEMENTS problems ***************");
        //1. PermCheck
        System.out.printf("PermCheck for [4, 1, 3, 2, 7, 5, 6] Expected 1 . . Found: [%d]%n", isPermutation(new int[]{4, 1, 3, 2, 7, 5, 6}));
        System.out.printf("PermCheck for [4, 1, 3] Expected 0 . . Found: [%d]%n", isPermutation(new int[]{4, 1, 3}));
        System.out.printf("PermCheck for [1, 1, 1, 1] Expected 0 . . Found: [%d]%n", isPermutation(new int[]{1, 1, 1, 1}));
        System.out.println(" - - - - - - ");

        //2. FrogRiverOne
        System.out.printf("FrogRiverOne for X=5, A=[1, 3, 1, 4, 2, 3, 5, 4] Expected 6 . . Found: [%d]%n",
                getEarliestTimeForFrogJump(5, new int[]{1, 3, 1, 4, 2, 3, 5, 4}));
        System.out.printf("FrogRiverOne for X=5, A=[1, 3, 1, 4, 2, 3, 2, 4, 3, 3, 1, 2, 2] Expected -1 . . Found: [%d]%n",
                getEarliestTimeForFrogJump(5, new int[]{1, 3, 1, 4, 2, 3, 2, 4, 3, 3, 1, 2, 2}));
    }

    /**
     * PERM_CHECK: This method checks if the given array is a permutation.
     * A permutation is a sequence containing each element from 1 to N once, and only once.
     * For example, array A such that:
     *     A[0] = 4
     *     A[1] = 1
     *     A[2] = 3
     *     A[3] = 2
     * is a permutation, but array A such that:
     *     A[0] = 4
     *     A[1] = 1
     *     A[2] = 3
     * is not a permutation, because value 2 is missing.
     * @param srcArr - the array to be checked
     * @return - 1 if the array is a permutation, 0 otherwise
     */
    public int isPermutation(int[] srcArr) {
        int total = srcArr.length;
        int[] counterArray = new int[total];
        for (int i = 0; i < total; i++) {
            //If any number is greater than the total, then it is not a permutation
            if (srcArr[i] > total) {
                return 0;
            }
            //If the number is repeated, then it is not a permutation
            if (counterArray[srcArr[i] - 1] > 0) {
                return 0;
            }
            //Save the number in the counterArray
            counterArray[srcArr[i] - 1] = 1;
        }
        return 1;
    }

    /*
    A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0)
    and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.
    You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position
    where one leaf falls at time K, measured in seconds.
    The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only
    when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when
    all the positions from 1 to X are covered in leaves). You may assume that the speed of the current in the river is
    negligibly small, i.e. the leaves do not change their positions once they fall in the river.

    For example, you are given integer X = 5 and array A such that:
          A[0] = 1
          A[1] = 3
          A[2] = 1
          A[3] = 4
          A[4] = 2
          A[5] = 3
          A[6] = 5
          A[7] = 4
    In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.
    Write a function:
        class Solution { public int solution(int X, int[] A); }
    that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

    If the frog is never able to jump to the other side of the river, the function should return −1.

    Write an efficient algorithm for the following assumptions:
        N and X are integers within the range [1..100,000];
        each element of array A is an integer within the range [1..X].
   */

    /**
     * FromRiverOne - Find the earliest time when a frog can jump to the other side of a river.
     */
    public int getEarliestTimeForFrogJump(int X, int[] src) {
        int n = src.length;
        int[] positions = new int[X];
        int steps = X;
        for (int i = 0; i < n; i++) {
            if (positions[src[i] - 1] == 0) {
                positions[src[i] - 1] = 1;
                steps--;
            }
            if (steps == 0) {
                return i;
            }
        }
        return -1;
    }
}
