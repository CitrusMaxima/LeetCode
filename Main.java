package leetcode;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] nums = new int[]{7,7,7,8,8,9};
        int[] result = solution.searchRange(nums,8);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
