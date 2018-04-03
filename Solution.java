package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int temp = 0;
        int left;
        int right = nums.length-1;

        if ((nums == null || nums.length == 0 || nums.length < 3 || nums[temp] > 0 || nums[right] < 0))
            return result;

        for (; temp < nums.length-2 && nums[temp] <= 0;) {

            left = temp + 1;
            right = nums.length-1;
            while (left < right) {

                int sum = nums[temp] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[temp],nums[left],nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left-1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                }
                else if (sum > 0) {
                    right--;
                    while (left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                }
                else if (sum < 0) {
                    left++;
                    while (left < right && nums[left] == nums[left-1]) {
                        left++;
                    }
                }
            }

            temp++;
            while ((temp < nums.length-2) && (nums[temp] <= 0) && (nums[temp] == nums[temp-1])) {
                temp++;
            }

        }

        return result;
    }

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1, k = nums.length-1; j < k;) {
                int sum = nums[i] + nums[j] + nums[k];
                if (i == 0 && j == i + 1 && k == nums.length-1)
                    result = sum;
                result = (Math.abs(target-sum) < Math.abs(target-result) ? sum : result);
                if (sum > target)
                    k--;
                else if (sum < target)
                    j++;
                else
                    return sum;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = new int[]{-3,-2,-5,3,-4};
        System.out.println(solution.threeSumClosest(nums,-1));
    }

}

