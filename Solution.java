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
}

