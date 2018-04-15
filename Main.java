package leetcode;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] x = new int[]{10,1,2,7,6,1,5};
        List<List<Integer>> result = solution.combinationSum2(x,8);
        System.out.println(result);
    }
}
