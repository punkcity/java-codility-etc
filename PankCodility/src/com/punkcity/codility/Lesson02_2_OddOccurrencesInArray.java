package com.punkcity.codility;

/**
 * Solve the Codility problem "OddOccurrencesInArray: Find value that occurs in odd number of elements"
 * The problem is described in the following comments.
 *
 * A non-empty array A consisting of N integers is given. The array contains an odd number of elements, and each element
 * of the array can be paired with another element that has the same value, except for one element that is left unpaired.
 *
 * For example, in array A such that:
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 *  - the elements at indexes 0 and 2 have value 9,
 *  - the elements at indexes 1 and 3 have value 3,
 *  - the elements at indexes 4 and 6 have value 9,
 *  - the element at index 5 has value 7 and is unpaired.
 * Write a function:
 *      class Solution { public int solution(int[] A); }
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 *
 * For example, given array A such that:
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 * the function should return 7, as explained in the example above.
 *
 * Write an "EFFICIENT" algorithm for the following assumptions:
 *      - N is an odd integer within the range [1..1,000,000];
 *      - each element of array A is an integer within the range [1..1,000,000,000];
 *      - all but one of the values in A occur an even number of times.
 */
public class Lesson02_2_OddOccurrencesInArray {
    /**
     * This is the default constructor for the class.
     */
    public Lesson02_2_OddOccurrencesInArray() {
        super();
    }

    /**
     * Run ALL the "in-built" tests for the OddOccurrencesInArray problem.
     */
    public void runTestsForOddOccurrencesInArray() {
        int[] srcArr = {9, 3, 9, 3, 7};
        System.out.println("OddOccurrencesInArray for [9, 3, 9, 3, 9, 7, 9] Expected: '7' . . . and we got => " + getUnpairedElement(srcArr));

        srcArr = new int[]{9, 3, 9, 3,   9, 7, 9, 7,   5, 5, 5, 5,   5, 5, 5, 5,  5};
        System.out.println("OddOccurrencesInArray for [9, 3, 9, 3, 9, 7, 9, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5] Expected: '5' . . . and we got => " + getUnpairedElement(srcArr));

        srcArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("OddOccurrencesInArray for [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9] Expected: '10' . . . and we got => " + getUnpairedElement(srcArr));

        srcArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println("OddOccurrencesInArray for [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11] Expected: '11' . . . and we got => " + getUnpairedElement(srcArr));

        srcArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12};
        System.out.println("OddOccurrencesInArray for [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12] Expected: '12' . . . and we got => " + getUnpairedElement(srcArr));
    }

    /**
     * This is a VERY EFFICIENT solution to find the unpaired element in the given array
     * The XOR (exclusive OR) operation has certain properties that make it useful for this problem:
     *      - XOR of a number with itself is 0.
     *      - XOR of a number with 0 is the number itself.
     *      - XOR operation is associative and commutative, which means the order of the numbers does not matter.
     * So, if we have an array where each element occurs an even number of times except for one element, we can find that one element using XOR.
     *
     * Here's how it works:
     *  - We start with a variable unpaired set to 0.
     *  - We iterate over each element in the array, applying the XOR operation between the unpaired variable and the current element.
     *  - If an element occurs twice in the array, it will effectively be cancelled out, because X XOR X results in 0.
     *  - The unpaired variable will collect the XOR of all elements in the array. Elements occurring in pairs will
     *        cancel out to 0, leaving only the element that occurs an odd number of times.
     *  - At the end of the loop, unpaired will hold the value of the element that appears an odd number of times in the array.
     *
     * This is a very efficient solution, as it ONLY REQUIRES A SINGLE PASS over the array & uses a CONSTANT amount of space.
     * @param A The array of integers where each element occurs an even number of times except for one element.
     * @return The value of the unpaired element.
     */
    public int getUnpairedElement(int[] A) {
        int unpairedElement = 0;
        for (int i = 0; i < A.length; i++) {
            unpairedElement ^= A[i];
//            System.out.println("i = " + i + " unpairedElement = " + unpairedElement);
        }
        return unpairedElement;
    }
}
