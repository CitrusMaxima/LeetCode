package leetcode;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] x = new int[]{1,2,3};
        List<List<Integer>> result = solution.permute(x);
        System.out.println(result);
    }
}
