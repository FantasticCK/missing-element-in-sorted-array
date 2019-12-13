package com.CK;

public class Main {

    public static void main(String[] args) {
        new Solution().missingElement(new int[]{1,2,3}, 3);
    }
}

// Binary Search
class Solution {
    public int missingElement(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            int tmp = nums[mid] - nums[left] - (mid - left);
            if (tmp >= k) {
                right =mid;
            } else {
                left = mid;
                k -= tmp;
            }
        }

        if (nums[left] + k >= nums[right]) {
            return nums[left] + k + 1;
        }
        return nums[left] + k;
    }
}

// One Pass
class Solution {
    // Return how many numbers are missing until nums[idx]
    int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        // If kth missing number is larger than
        // the last element of the array
        if (k > missing(n - 1, nums))
            return nums[n - 1] + k - missing(n - 1, nums);

        int idx = 1;
        // find idx such that
        // missing(idx - 1) < k <= missing(idx)
        while (missing(idx, nums) < k) idx++;

        // kth missing number is larger than nums[idx - 1]
        // and smaller than nums[idx]
        return nums[idx - 1] + k - missing(idx - 1, nums);
    }
}
