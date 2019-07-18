package com.study.test;

/**
 * Created by LiBingyi on 2019/7/18 10:16
 */
public class BinarySearch {

    public int binarySearch(int[] arr, int target, int n) {
        int low = 0;
        int high = n;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}

