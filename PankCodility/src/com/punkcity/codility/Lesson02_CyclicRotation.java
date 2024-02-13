package com.punkcity.codility;

/**
 * Problem Statement: CyclicRotation: Rotate an array to the right by a given number of steps.
 *
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one
 * index, and the last element of the array is moved to the first place. For example, the rotation of array
 * A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
 *
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 * Write a function:
 *      class Solution { public int[] solution(int[] A, int K); }
 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *     A = [3, 8, 9, 7, 6]
 *     K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 *     [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 *     [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 *     [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 *
 * For another example, given
 *     A = [0, 0, 0]
 *     K = 1
 * the function should return [0, 0, 0]
 *
 * Given
 *     A = [1, 2, 3, 4]
 *     K = 4
 * the function should return [1, 2, 3, 4]
 *
 * Assume that:
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 */
public class Lesson02_CyclicRotation {
    public Lesson02_CyclicRotation() {
        super();
    }

    public void runTestsForCyclicRotation() {
        int[] srcArr = {3, 8, 9, 7, 6};
        int shiftBy = 3;
        compareAndPrintResults(srcArr, shiftBy, new int[]{9, 7, 6, 3, 8});

        srcArr = new int[]{0, 0, 0};
        shiftBy = 1;
        compareAndPrintResults(srcArr, shiftBy, new int[]{0, 0, 0});

        srcArr = new int[]{1, 2, 3, 4};
        shiftBy = 4;
        compareAndPrintResults(srcArr, shiftBy, new int[]{1, 2, 3, 4});

        srcArr = new int[]{11, 32, 23, 56, 78, 9, 6, 7, 55, 33, 4};
        shiftBy = 5;
        compareAndPrintResults(srcArr, shiftBy, new int[]{6, 7, 55, 33, 4, 11, 32, 23, 56, 78, 9});
    }

    /**
     * This method does the actual comparison of source array with the results of the CyclicRotation, and prints report
     * @param srcArr The source array to be rotated.
     * @param shiftBy The number of steps to rotate the array to the right.
     * @param expectedArr The expected array after the rotation.
     */
    private void compareAndPrintResults(int[] srcArr, int shiftBy, int[] expectedArr) {
        int[] result = this.rotateArray(srcArr, shiftBy);
        System.out.println("CyclicRotation for " + arrayToString(srcArr) + " and shift by " + shiftBy +
                " steps to the right; Expected " + arrayToString(expectedArr) + ". RESULT => " + arrayToString(result));
    }

    /**
     * This method compares the given array with the expected array
     * @param dataArr The array to be compared.
     * @param expectedArr The expected array.
     * @return True if the given array matches the expected array, false otherwise.
     */
    private static boolean compareWithExpectedArray(int[] dataArr, int[] expectedArr) {
        boolean result = true;
        if (dataArr.length != expectedArr.length) {
            result = false;
        } else {
            for (int i = 0; i < dataArr.length; i++) {
                if (dataArr[i] != expectedArr[i]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This is a utility method to converts a given array to a string.
     * @param arr The array to be converted to a string.
     * @return The string representation of the array.
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method rotates the array to the right by a given number of steps.
     * @param srcArr The source array to be rotated.
     * @param shiftCount The number of steps to rotate the array to the right.
     * @return The rotated array.
     */
    public int[] rotateArray(int[] srcArr, int shiftCount) {
        int[] result = new int[srcArr.length];
        for (int i = 0; i < srcArr.length; i++) {
            result[(i + shiftCount) % srcArr.length] = srcArr[i];
        }
        return result;
    }
}
