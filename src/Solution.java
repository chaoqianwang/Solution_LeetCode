import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int length = nums.length;
        int[] sortedNums = nums.clone();
        int value1Index = -1, value2Index = -1;
        int answer1 = -1, answer2 = -1;

        Arrays.sort(sortedNums);
        for (int i = 0; i < length; i++) {
            int secondNumber = target - sortedNums[i];

            int left = i + 1, right = length - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (sortedNums[middle] == secondNumber) {
                    value1Index = i;
                    value2Index = middle;
                    break;
                } else if (sortedNums[middle] > secondNumber) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            if (value1Index != -1 && value2Index != -1) {
                break;
            }
        }
        if (value1Index != -1 && value2Index != -1) {
            int index = 0;
            for (; index < length; index++) {
                if (nums[index] == sortedNums[value1Index] || nums[index] == sortedNums[value2Index]) {
                    answer1 = index;
                    break;
                }
            }
            for (index++; index < length; index++) {
                if (nums[index] == sortedNums[value1Index] || nums[index] == sortedNums[value2Index]) {
                    answer2 = index;
                    break;
                }
            }
            return new int[]{answer1, answer2};
        }
        return new int[]{-1, -1};
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        int carry = 0;
        int sum = 0;
        ListNode root = null, node = null;

        while (l1 != null || l2 != null) {
            int l1Value = l1 == null ? 0 : l1.val;
            int l2Value = l2 == null ? 0 : l2.val;
            sum = l1Value + l2Value + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            if (root == null) {
                root = new ListNode(sum);
                node = root;
            } else {
                node.next = new ListNode(sum);
                node = node.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry != 0) {
            node.next = new ListNode(carry);
        }

        return root;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode l1 = head, l2 = head.next;

        while (l1 != null && l2 != null) {
            if (l1 == l2) return true;
            l1 = l1.next;
            if (l2.next != null) l2 = l2.next.next;
            else {
                l2 = null;
            }
        }
        return false;
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return new int[0];
        Stack<Pair<Integer, Integer>> myStack = new Stack<>();
        int length = T.length;
        int[] answer = new int[length];

        for (int elem : answer) {
            elem = 0;
        }

        myStack.push(new Pair<>(T[0], 0));
        for (int i = 1; i < length; i++) {
            while (!myStack.empty() && T[i] > myStack.peek().getKey()) {
                answer[myStack.peek().getValue()] = i - myStack.peek().getValue();
                myStack.pop();
            }
            myStack.push(new Pair<>(T[i], i));
        }
        return answer;
    }

    public static int[] swap(int o1, int o2) {
        int[] swap = {o2, o1};
        return swap;
    }

    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0) return -1;

        int answer = 0, total = tasks.length;
        int[] taskCount = new int[26];


        for (char elem : tasks) {
            taskCount[elem - 'A'] += 1;
        }

        while (total != 0) {
            //任务数量降序排序
            Arrays.sort(taskCount);
            for (int i = 0; i < 25 / 2; i++) {
                int tmp = taskCount[25 - i];
                taskCount[25 - i] = taskCount[i];
                taskCount[i] = tmp;
            }

            //执行一轮任务
            for (int i = 0; i < n + 1; i++) {
                if (i < 26 && taskCount[i] > 0) {
                    total--;
                    taskCount[i]--;
                }
                answer++;
                //全部任务执行完毕，退出
                if (total == 0) {
                    break;
                }
            }
        }

        return answer;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        TreeNode t3 = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        t3.left = mergeTrees(t1 == null ? t1 : t1.left, t2 == null ? t2 : t2.left);
        t3.right = mergeTrees(t1 == null ? t1 : t1.right, t2 == null ? t2 : t2.right);

        return t3;
    }

//    public int findUnsortedSubarray(int[] nums) {
//        if(nums==null||nums.length==0) return -1;
//
//        int length=nums.length;
//        int answer=0;
//        List<Pair<Integer,Integer>> map=new ArrayList<>();
//        ArrayList<Integer> sortedIndex=new ArrayList<>();
//
//        for(int i=0;i<length;i++){
//            map.add(new Pair<>(nums[i],i));
//        }
//        map.sort(new Comparator<Pair<Integer, Integer>>() {
//            @Override
//            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
//                return o1.getKey()-o2.getKey();
//            }
//        });
//        for(Pair<Integer,Integer> pair:map){
//            sortedIndex.add(pair.getValue());
//        }
//
//        for(int i=0;i<length;i++){
//            if(i!=sortedIndex.get(i)) break;
//            answer++;
//        }
//        int stopIndex=answer;
//        for(int i=length-1;i>=stopIndex;i--){
//            if(i!=sortedIndex.get(i)) break;
//            answer++;
//        }
//
//        answer=length-answer;
//        return answer;
//    }

//    public int findUnsortedSubarray(int[] nums) {
//        if(nums==null||nums.length==0) return -1;
//
//        int[] sortedNums=nums.clone();
//        int length=nums.length,index=0;
//        int antiAnswer=0;
//
//        Arrays.sort(sortedNums);
//        for(;index<length;index++){
//            if(nums[index]!=sortedNums[index]){
//                break;
//            }
//            antiAnswer++;
//        }
//        for(int i=length-1;i>=index;i--){
//            if(nums[i]!=sortedNums[i]){
//                break;
//            }
//            antiAnswer++;
//        }
//
//        return length-antiAnswer;
//    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int length = nums.length;
        int left = length, right = -1;

        for (int i = 0; i < length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                left = i;
            }
        }

        return right >= left ? right - left + 1 : 0;
    }

    public static int numSquares(int n) {
        if (n <= 0) return 0;
        int[] fn = new int[n + 1];
        fn[0] = 0;
        fn[1] = 1;
        numSquaresCore(n, fn);
        int answer = fn[n];
        return answer;
    }

    public static int numSquaresCore(int index, int[] fn) {
        if (index <= 0) return 0;
        if (index == 1) return 1;
        if (fn[index] != 0) return fn[index];

        int min = index;
        int border = (int) (Math.pow(index, 0.5) + 0.5);
        for (int i = 1; i <= border; i++) {
            if (index - i * i == 0) {
                min = 1;
            } else if (index - i * i > 0) {
                if (fn[index - i * i] == 0) {
                    fn[index - i * i] = numSquaresCore(index - i * i, fn);
                }
                if (min > 1 + fn[index - i * i]) {
                    min = 1 + fn[index - i * i];
                }
            }
        }

        fn[index] = min;
        return min;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int answer = 0;
        int length = s.length();
        LinkedList<Character> window = new LinkedList<>();

        for (int i = 0; i < length; i++) {
            if (window.contains(s.charAt(i))) {
                answer = answer < window.size() ? window.size() : answer;
                while (!window.isEmpty() && window.poll() != s.charAt(i)) ;
            }
            window.add(s.charAt(i));
        }
        return Math.max(answer, window.size());
    }


    public static int numSquares1(int n) {
        if (n <= 0) return 0;
        int answer = 0;
        int[] fn = new int[n + 1];
        fn[0] = 0;
        fn[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            int min = i;
            for (int j = 1; j * j <= i; j++) {
                if (min > 1 + fn[i - j * j]) {
                    min = 1 + fn[i - j * j];
                }
            }
            fn[i] = min;
        }

        return fn[n];
    }

    public double getMiddleNum(int[] nums, int left, int right) {
        if (left > right) return -1;

        int middle = (left + right) / 2;
        boolean flag = (right - left + 1) % 2 == 0 ? true : false;
        if (flag) {
            return (nums[middle] + nums[middle + 1]) / 2.0;
        } else {
            return nums[middle];
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) return 0.0;
        if (nums1.length == 0) return getMiddleNum(nums2, 0, nums2.length - 1);
        if (nums2.length == 0) return getMiddleNum(nums1, 0, nums1.length - 1);
        int totalLength = nums1.length + nums2.length;

        //设置较长数组与较短数组及其长度
        int[] shortArray, longArray;
        int shortLength, longLength;
        if (nums1.length < nums2.length) {
            shortArray = nums1;
            longArray = nums2;
        } else {
            shortArray = nums2;
            longArray = nums1;
        }
        shortLength = shortArray.length;
        longLength = longArray.length;

        //搜索边界为[-1,短数组的最后一个元素]，索引为i时，隔板放在第i个元素之后
        int left = -1, right = shortLength - 1;
        int i, j;
        //短数组左半部分最大值，长数组左半部分最大值，短数组右半部分最小值，长数组右半部分最小值
        int max1 = 0, max2 = 0, min1 = 0, min2 = 0;
        //左半部分最大值，右半部分最小值
        int leftMax = 0, rightMin = 0;

        while (left <= right) {
            i = (left + right) / 2;
            j = (int) (totalLength / 2.0 + 0.5) - (i + 1) - 1;
            //获得左半部分的最大值
            max1 = i >= 0 && i < shortLength ? shortArray[i] : Integer.MIN_VALUE;
            max2 = j >= 0 && j < longLength ? longArray[j] : Integer.MIN_VALUE;
            leftMax = Math.max(max1, max2);
            //获得右半部分的最小值
            min1 = (i + 1) >= 0 && (i + 1) < shortLength ? shortArray[i + 1] : Integer.MAX_VALUE;
            min2 = (j + 1) >= 0 && (j + 1) < longLength ? longArray[j + 1] : Integer.MAX_VALUE;
            rightMin = Math.min(min1, min2);

            if (leftMax <= rightMin) {
                break;
            } else if (max1 > min2) {
                right = i - 1;
            } else if (max2 > min1) {
                left = i + 1;
            }
        }
        return totalLength % 2 == 0 ? (leftMax + rightMin) / 2.0 : leftMax;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        StringBuffer answer = new StringBuffer();
        int length = s.length();
        int preI, afterI;
        LinkedList<Character> answerList = new LinkedList<>();
        LinkedList<Character> currentList;

        for (int i = 0; i < length; i++) {
            currentList = new LinkedList<>();
            currentList.add(s.charAt(i));
            preI = i - 1;
            afterI = i + 1;
            while (preI >= 0 && afterI < length && s.charAt(preI) == s.charAt(afterI)) {
                currentList.addFirst(s.charAt(preI));
                currentList.addLast(s.charAt(afterI));
                preI--;
                afterI++;
            }
            if (currentList.size() > answerList.size()) {
                answerList = (LinkedList<Character>) currentList.clone();
            }

            currentList = new LinkedList<>();
            preI = i;
            afterI = i + 1;
            while (preI >= 0 && afterI < length && s.charAt(preI) == s.charAt(afterI)) {
                currentList.addFirst(s.charAt(preI));
                currentList.addLast(s.charAt(afterI));
                preI--;
                afterI++;
            }
            if (currentList.size() > answerList.size()) {
                answerList = (LinkedList<Character>) currentList.clone();
            }
        }

        for (Character elem : answerList) {
            answer.append(elem);
        }
        return answer.toString();
    }

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        int answer = 0;
        int length = s.length();
        boolean[][] f = new boolean[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; i + j < length; j++) {
                if (j == i + j) {
                    f[j][i + j] = true;
                    continue;
                }
                if (s.charAt(j) == s.charAt(i + j)) {
                    if (j + 1 > i + j - 1) f[j][i + j] = true;
                    else f[j][i + j] = f[j + 1][i + j - 1];
                    continue;
                }
                f[j][i + j] = false;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (f[i][j]) answer++;
            }
        }

        return answer;
    }

    public boolean isMatchCore(String s, int strIndex, String p, int patIndex) {
        //字符串没结束，模式结束了，false;
        if (strIndex < s.length() && patIndex >= p.length()) return false;
        //字符串结束了，模式还有剩余，先对模式处理
        while (strIndex >= s.length() && patIndex + 1 < p.length() && p.charAt(patIndex + 1) == '*') patIndex += 2;
        ////二者都结束了，true;
        if (strIndex >= s.length() && (patIndex >= p.length())) return true;
        ////字符串结束了，模式仍有剩余，false;
        if (strIndex >= s.length() && (patIndex < p.length())) return false;

        if (s.charAt(strIndex) == p.charAt(patIndex) || p.charAt(patIndex) == '.') {
            if (patIndex + 1 < p.length() && p.charAt(patIndex + 1) == '*') {
                return isMatchCore(s, strIndex, p, patIndex + 2) || isMatchCore(s, strIndex + 1, p, patIndex) || isMatchCore(s, strIndex + 1, p, patIndex + 2);
            }
            return isMatchCore(s, strIndex + 1, p, patIndex + 1);
        } else if (patIndex + 1 < p.length() && p.charAt(patIndex + 1) == '*') {
            return isMatchCore(s, strIndex, p, patIndex + 2);
        }
        return false;
    }

    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) return isMatchCore("", 0, p, 0);
        return isMatchCore(s, 0, p, 0);
    }

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;

        int answer = 0;
        int area = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            area = Math.min(height[left], height[right]) * (right - left);
            answer = answer > area ? answer : area;
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return answer;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        List<List<Integer>> answer = new ArrayList<>();
        ArrayList<Integer> currentAnswer = new ArrayList<>();
        HashSet<Integer> currentSet = new HashSet<>();
        HashSet<Integer> firstNumSet = new HashSet<>();
        int length = nums.length;

        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        for (int i = 0; i < length; i++) {
            if (firstNumSet.contains(sortedNums[i])) continue;
            currentSet = new HashSet<>();
            firstNumSet.add(sortedNums[i]);
            int firstNum = sortedNums[i];
            int restSum = 0 - firstNum;
            int left = i + 1, right = length - 1;
            while (left < right) {
                if (sortedNums[left] + sortedNums[right] == restSum && !currentSet.contains(sortedNums[left]) && !currentSet.contains(sortedNums[right])) {
                    currentSet.add(sortedNums[left]);
                    currentSet.add(sortedNums[right]);
                    currentAnswer = new ArrayList<>();
                    currentAnswer.add(firstNum);
                    currentAnswer.add(sortedNums[left]);
                    currentAnswer.add(sortedNums[right]);
                    answer.add(currentAnswer);
                }
                if (sortedNums[left] + sortedNums[right] <= restSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return answer;
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        HashMap<Character, Character> map = new HashMap<>();
        map.put('2', 'a');
        map.put('3', 'd');
        map.put('4', 'g');
        map.put('5', 'j');
        map.put('6', 'm');
        map.put('7', 'p');
        map.put('8', 't');
        map.put('9', 'w');

        List<String> answer = new ArrayList<>();
        List<String> subAnswer = letterCombinations(digits.substring(1));
        char firstChar = digits.charAt(0);
        int letterNumbers = firstChar == '7' || firstChar == '9' ? 4 : 3;

        for (int i = 0; i < letterNumbers; i++) {
            if (subAnswer.size() == 0) {
                answer.add(((char) (map.get(firstChar) + i)) + "");
            } else {
                for (int j = 0; j < subAnswer.size(); j++) {
                    answer.add(((char) (map.get(firstChar) + i)) + subAnswer.get(j));
                }
            }
        }

        return answer;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode pointer0 = head, pointer1 = head, pointer2 = head;
        int index = n;

        while (index-- > 0 && pointer2 != null) pointer2 = pointer2.next;
        if (index >= 0) return null;
        if (pointer2 == null) {
            pointer1 = pointer0.next;
            pointer0.next = null;
            return pointer1;
        } else {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        while (pointer2 != null) {
            pointer0 = pointer0.next;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        pointer0.next = pointer1.next;

        return head;
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        LinkedList<Character> stack = new LinkedList<>();
        int length = s.length();
        stack.addLast(s.charAt(0));

        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.addLast(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (s.charAt(i) == ')' && stack.peekLast() == '(' ||
                    s.charAt(i) == ']' && stack.peekLast() == '[' ||
                    s.charAt(i) == '}' && stack.peekLast() == '{') {
                    stack.removeLast();
                }else{
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;

        ListNode head=null;
        ListNode p1=l1,p2=l2,p3=head;
        ListNode min;

        while (p1 != null && p2 != null) {
            if(p1.val<p2.val){
                min=p1;
                p1=p1.next;
            }else{
                min=p2;
                p2=p2.next;
            }

            if(head==null){
                head=min;
                p3=head;
            }else{
                p3.next=min;
                p3=p3.next;
            }
        }

        if(p1!=null) p3.next=p1;
        if(p2!=null) p3.next=p2;

        return head;
    }

    public static void main(String[] args) {

        System.out.println(new Solution().isValid("{[]}"));
    }
}
