package com.studio.leetcode.sum_two_numbers;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    // TODO: 暴力枚举，时间复杂度（N^2），空间复杂度（1）
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    // TODO: 哈希表，时间复杂度（N），空间复杂度（N）
    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }
}