package leetcode;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] metrix = {{1}};

        boolean result = solution.searchMatrix(metrix,0);
        System.out.println(result);
    }
}
