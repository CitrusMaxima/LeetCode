package leetcode;

import java.util.*;

class Solution {

    //两数之和
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    //两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if(carry>0){
            curr.next=new ListNode(carry);
        }

        return dummyHead.next;
    }

    //三数之和
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

    //最接近的三数之和
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

    //无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {

        String temp = "";
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        int j = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (temp.contains(String.valueOf(s.charAt(i)))) {
                if (result.get(j) <= temp.length()) {
                    result.add(temp.length());
                    j++;
                }
                if (temp.length() == 1)
                    temp = String.valueOf(s.charAt(i));
                else {
                    temp = temp.substring(1,temp.length());
                    i--;
                }
            } else {
                temp += String.valueOf(s.charAt(i));
                result.set(j,temp.length());
            }
        }

        for (int k = 0; k < result.size(); k++) {
            if (max < result.get(k))
                max = result.get(k);
        }

        return max;
    }

    //最长回文子串
    private int max = 0;
    private String res = "";

    public String longestPalindrome(String s) {
        if (s.length() == 1) { return s; }
        for (int i = 0; i < s.length()-1; i++) {
            checkPalindromeExpand(s,i,i);
            checkPalindromeExpand(s,i,i+1);
        }
        return res;
    }

    public void checkPalindromeExpand(String s, int low, int high) {
        while (low >= 0 && high < s.length()) {
            if (s.charAt(low) == s.charAt(high)) {
                if (high - low + 1 > max) {
                    max = high - low + 1;
                    res = s.substring(low,high+1);
                }
                low--; high++;
            } else {
                return;
            }
        }
    }

    //颠倒整数
    public int reverse(int x) {

        boolean isNegative = false;
        String str = String.valueOf(x);
        if (x < 0) {
            isNegative = true;
            str = str.substring(1, str.length());
        }


        StringBuffer sb = new StringBuffer(str);
        String result = sb.reverse().toString();

        if (Long.parseLong(result) > Integer.MAX_VALUE) {
            result = "0";
        }

        return (isNegative == false ? Integer.parseInt(result) : -Integer.parseInt(result));
    }

    //回文数
    public boolean isPalindrome(int x) {

        String str = String.valueOf(x);
        if (x < 0) {
            return false;
        }

        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len-i-1)){
                return false;
            }
        }

        return true;
    }

    //盛水最多的容器
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length-1;
        int max = 0;
        while(left<right){
            max = Math.max(max, Math.min(height[left], height[right])*(right-left));
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return max;
    }

    //罗马数字转整数
    public int romanToInt(String s) {

        int result = 0;

        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == 'I'){
                if (i != s.length()-1 && (s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X'))
                    result -= 1;
                else
                    result += 1;
            }
            else if (s.charAt(i) == 'V')
                result += 5;
            else if (s.charAt(i) == 'X') {
                if (i != s.length()-1 && (s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C'))
                    result -= 10;
                else
                    result += 10;
            }
            else if (s.charAt(i) == 'L')
                result += 50;
            else if (s.charAt(i) == 'C') {
                if (i != s.length()-1 && (s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M'))
                    result -= 100;
                else
                    result += 100;
            }
            else if (s.charAt(i) == 'D')
                result += 500;
            else if (s.charAt(i) == 'M')
                result += 1000;
        }

        return result;
    }

    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {

        if(strs.length==0||strs[0].length()==0)
            return "";
        if(strs.length==1)
            return strs[0];
        int i=0;
        while(i<strs[0].length())
        {
            for(int j=1;j<strs.length;j++)
            {
                if(strs[j].length()<i+1||strs[j].charAt(i)!=strs[0].charAt(i))
                    return strs[0].substring(0,i);
            }
            i++;
        }
        return strs[0];
    }

    //有效的括号
    public boolean isValid(String s) {

        if(s == null || s.length() == 0 || s.length() % 2 != 0){
            return false;
        }

        Stack<Character> stack=new Stack<Character>();
        boolean flag = true;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    flag = false;
                    break;
                }
            } else if (s.charAt(i) == ']') {
                if (!stack.empty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    flag = false;
                    break;
                }
            } else if (s.charAt(i) == '}') {
                if (!stack.empty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if (!stack.empty()){
            flag = false;
        }

        return flag;
    }

    //搜索插入位置
    public int searchInsert(int[] nums, int target) {

        int i = 0;
        for(;i < nums.length;i++)
            if(nums[i] >= target)
                return i;
        return i;
    }

    //最大子序和
    public int maxSubArray(int[] nums) {

        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max)
                max = sum;
            else if (sum < 0)
                sum = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                return max;
        }
        return Arrays.stream(nums).max().getAsInt();
    }

    //搜索范围
    public int[] searchRange(int[] nums, int target) {

        int[] result = new int[]{-1,-1};
        if (nums == null || nums.length == 0)
            return result;

        int left = 0;
        int right = nums.length-1;
        int mid;

        if (nums[left] > target || nums[right] < target)
            return result;

        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }

        if (nums[right] != target)
            return result;
        result[0] = right;
        result[1] = right;
        right = nums.length-1;
        //left = 0;

        while (left <= right) {
            mid = ((left + right) / 2) + ((left + right) % 2);
            if (left == mid)
                break;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            else
                left = mid;
        }
        if (nums[left] != target)
            return result;
        result[1] = left;

        return result;
    }

    //组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);

        if(candidates.length == 0 || candidates[0] > target)
            return list;

        int len = 0;
        boolean isAdd = false;
        for(int i = 0; i < candidates.length;i++){
            if(candidates[i] == target){
                if(!isAdd){
                    List<Integer> al = new ArrayList<Integer>();
                    al.add(target);
                    list.add(al);
                    isAdd = true;
                }
            }else if(candidates[i] < target){
                candidates[len++] = candidates[i];
            }
        }

        if(candidates[0] > target)
            return list;

        for(int i = 0; i < len; i++){
            int[] b = Arrays.copyOfRange(candidates, i, len);
            if(candidates[i] > target)
                break;

            List<List<Integer>> newList = new ArrayList<List<Integer>>();
            newList = combinationSum(b,target-candidates[i]);
            if(newList.size() > 0){
                for(int j = 0; j < newList.size();j++){
                    newList.get(j).add(candidates[i]);
                    Collections.sort(newList.get(j));
                    list.add(newList.get(j));
                }
            }
        }
        return list;
    }

    //旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - 1 - i; ++j) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    //删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode p1, p2, pre;
        if ( head == null || n <= 0 ) {
            return null;
        }

        p1 = head;
        p2 = head;
        pre = head;

        for ( int i = 0; i < n-1; i++ ) {
            if ( p1.next != null ) {
                p1 = p1.next;
            } else {
                return null;
            }
        }

        while ( p1.next != null ) {
            p1 = p1.next;
            pre = p2;
            p2 = p2.next;
        }

        if ( p2 == head ) {
            head = head.next;
        } else {
            pre.next = pre.next.next;
        }

        return head;
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode p=head;
        int val1,val2;
        while(l1!=null || l2!=null){
            val1=Integer.MAX_VALUE;
            val2=Integer.MAX_VALUE;
            if(l1!=null){
                val1=l1.val;
            } else{
                p.next=l2;
                break;
            }
            if(l2!=null){
                val2=l2.val;
            } else{
                p.next=l1;
                break;
            }
            if(val1<val2){
                p.next=l1;
                l1=l1.next;
            } else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }
        return head.next;
    }

    //x的平方根
    public int mySqrt(int x) {

        if (x <= 1)
            return x;

        int left = 0, right = x;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x / mid >= mid)
                left = mid + 1;
            else
                right = mid;
        }

        return right - 1;
    }

    //交换相邻结点
    public ListNode swapPairs(ListNode head) {

        if(head == null)
            return head;

        ListNode cur = head;
        ListNode prev = cur;  // 连接每对节点

        if(cur.next != null)
            head = cur.next;

        while(cur != null && cur.next != null){
            prev.next = cur.next;
            ListNode next = cur.next.next;  // next用来保存下一对节点的开始节点
            cur.next.next = cur;
            cur.next = next;
            prev = cur;  // prev指向每一对反转之后节点的第二个节点
            cur = next;  // cur指向每一对节点的第一个节点
        }

        return head;
    }

    //括号生成
    public List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<String>();
        if (n == 0) {
            res.add("");
        } else {
            List<String> pre = generateParenthesis(n - 1);
            for (String str : pre) {
                for (int i = 0; i < str.length(); ++i) {
                    if (str.charAt(i) == '(') {
                        str = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length());
                        res.add(str);
                        str = str.substring(0, i + 1) +  str.substring(i + 3, str.length());
                    }
                }
                res.add("()" + str);
            }
        }
        return new ArrayList(res);
    }

    //字符串相乘
    public String multiply(String num1, String num2) {
        // 先把string翻转
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();

        int[] d = new int[n1.length()+n2.length()];     // 构建数组存放乘积
        for(int i=0; i<n1.length(); i++){
            for(int j=0; j<n2.length(); j++){
                d[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');      // 在正确位置累加乘积
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<d.length; i++){
            int digit = d[i]%10;        // 当前位
            int carry = d[i]/10;        // 进位
            if(i+1<d.length){
                d[i+1] += carry;
            }
            sb.insert(0, digit);        // prepend
        }

        // 移除前导零
        while(sb.charAt(0)=='0' && sb.length()>1){
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    //Pow(x, n)
    public double myPow(double x, int n) {

        if(n == 0)
            return 1.0;

        if(n < 0)
            if (n == Integer.MIN_VALUE)
                return Math.abs(1.0/myPow(x,Integer.MAX_VALUE));
            else
                return Math.abs(1.0/myPow(x,-n));


        double half = myPow(x,n>>1);

        if(n%2 == 0)
            return half*half;
        else
            return half*half*x;
    }

    //全排列
    public List<List<Integer>> permute(int[] nums) {

        // 最终返回的结果集
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        int len = nums.length;
        if (len==0||nums==null)  return res;

        // 采用前后元素交换的办法，dfs解题
        exchange(nums, 0, res);
        return res;
    }

    public void exchange(int[] nums, int i, List<List<Integer>> res) {
        int len = nums.length;
        // 将当前数组加到结果集中
        if(i==len-1) {
            List<Integer> list = new ArrayList<>();
            for (int j=0; j<len; j++){
                list.add(nums[j]);
            }
            res.add(list);
            return ;
        }
        // 将当前位置的数跟后面的数交换，并搜索解
        for (int j=i; j<len; j++) {
            swap(nums, i, j);
            exchange(nums, i+1, res);
            swap(nums, i, j);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<List<Integer>> newList = new ArrayList<List<Integer>>();
        list = combinationSum1(candidates,target);
        Set<List<Integer>> set = new HashSet<List<Integer>>();//去除重复
        for(int i = 0; i < list.size();i++){
            if(set.add(list.get(i))){//没有重复
                newList.add(list.get(i));//添加新的列表
            }
        }
        return newList;
    }

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);//数组排序
        //各种特殊情况
        if(candidates.length == 0 || candidates[0] > target)
            return list;

        int len = 0;
        boolean isAdd = false;//控制与t相等的数只添加一次
        for(int i = 0; i< candidates.length;i++){
            if(candidates[i] == target){
                if(!isAdd){//如果未添加
                    List<Integer> al = new ArrayList<Integer>();
                    al.add(target);
                    list.add(al);
                    isAdd = true;//标记已添加
                }
            }else if(candidates[i] < target){//只要比target小的值，大的值肯定不满足，排除
                candidates[len++] = candidates[i];//新数组
            }
        }
        //只存在a[0] < target 或 a[0] > target
        if(candidates[0] > target)//肯定已没有符合要求的组合
            return list;
        //a[0] < target

        for(int i = 0; i < len; i++){//循环搜索符合要求的数字组合
            int[] b = Arrays.copyOfRange(candidates, i+1, len);//不含>=t数据的新数组
            if(candidates[i] > target)//如果a[i]，肯定以后的数据已不符合，返回
                break;
            //相等于已经有了一个值a[0]了
            List<List<Integer>> newList = new ArrayList<List<Integer>>();
            if(i < len -1)
                newList = combinationSum1(b,target-candidates[i]);
            if(newList.size() > 0){//里面有符合要求的数据
                for(int j = 0; j < newList.size();j++){
                    newList.get(j).add(candidates[i]);//新返回的各个值添加a[0]
                    Collections.sort(newList.get(j));//排序
                    list.add(newList.get(j));//最终添加
                }
            }
        }
        return list;
    }

    //跳跃游戏
    public boolean canJump(int[] nums) {

        int reach=0;

        for (int i = 0; i < nums.length && i <= reach; i++) {
            reach=Math.max(reach,i+nums[i]);
        }

        return reach >= nums.length-1;
    }

    //合并区间
    public List<Interval> merge(List<Interval> intervals) {

        List<Interval> list = new ArrayList<Interval>();
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i=0; i<n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i=0, j=0; i<n; i++) {
            if (i==n-1 || starts[i+1]>ends[i]) {
                list.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }

        return list;
    }

    //旋转链表
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null)
            return null;

        int n = 1;
        ListNode cur = head;
        while (cur.next != null) {
            ++n;
            cur = cur.next;
        }

        cur.next = head;
        int m = n - k % n;

        for (int i=0; i<m; ++i) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;

        return newHead;
    }

    //不同路径
    public int uniquePaths(int m, int n) {

        int[][] result = new int[m][n];
        result[0][0] = 0;

        for (int i = 0; i < m; i++)
            result[i][0] = 1;
        for (int j = 0; j < n; j++)
            result[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }

        return result[m-1][n-1];

    }

    //不同路径 II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid[0][0] == 1)
            obstacleGrid[0][0] = 0;
        else
            obstacleGrid[0][0] = 1;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
                continue;
            }
            obstacleGrid[i][0] = obstacleGrid[i-1][0];
        }

        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                obstacleGrid[0][j] = 0;
                continue;
            }
            obstacleGrid[0][j] = obstacleGrid[0][j-1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }

        return obstacleGrid[m-1][n-1];
    }

}
