package leetcode;

public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] metrix = {{0,0,1,1},{0,0,1,0},{0,0,0,0}};

        int result = solution.uniquePathsWithObstacles(metrix);
        System.out.println(result);
    }
}
