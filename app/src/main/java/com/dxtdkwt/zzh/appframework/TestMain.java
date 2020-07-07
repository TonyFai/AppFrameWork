package com.dxtdkwt.zzh.appframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import kotlin.reflect.KVariance;

public class TestMain {
    public static void main(String[] args) {
//        int[] guess = new int[]{1,2,3};
//        int[] answer = new int[]{1,2,3};
//        String s = "Hello";
        Solution solution = new Solution();
//        int game = solution.hammingDistance(93,73);
//
//        int a = 2;
//        int b = 1;
//        System.out.println(a/b);
//        System.out.println(game);

//        int i = solution.subtractProductAndSum(4421);
//        int numbers = solution.findNumbers(new int[]{12,345,2,6,7896});
//        int i = solution.minTimeToVisitAllPoints(new int[][]{{3, 2}, {-2, 2}});
//        int[] ints = solution.sumZero(5);
//        String s = solution.freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#");
//        int i = solution.hammingDistance(93,73);
//        int[] ints = solution.replaceElements(new int[]{17, 18, 5, 4, 6, 1});
//        int[][] ints = solution.flipAndInvertImage(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}});
//        for (int i = 0; i < ints.length; i++) {
//        int i = solution.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});
//        List<Integer> integers = solution.selfDividingNumbers(1, 22);
//        int[] ints = solution.diStringMatch("DDDI");
//        int i = solution.arrayPairSum(new int[]{1,4,3,2});
//        int i = solution.peakIndexInMountainArray(new int[]{0,2,1,0});
//        String s = solution.reverseWords("Let's take LeetCode contest");
//        String[] words = solution.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"});
//        System.out.println(Arrays.toString(words));
//        }
//        boolean and = solution.and(true, true, true);
//        boolean[] a = {true,true,true};
//        and(a);
//        boolean palindrome = solution.isPalindrome(-121);
//        int[] ints = solution.getLeastNumbers(new int[]{3,2,1},2);
//        boolean ints = solution.hasGroupsSizeX(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2});
//        String ints = solution.reverseLeftWords("abcdefg",2);
//        int ints = solution.numberOfSteps(14);
//        int[] ints = solution.smallerNumbersThanCurrent(new int[]{8,1,2,2,3});
        int ints = solution.minimumLengthEncoding(new String[]{"time", "me", "bell"});
        System.out.println(ints);
//        System.out.println(Arrays.toString(ints));
    }

    public static boolean and(boolean... booleans) {
        System.out.println("boolean");
        for (boolean b : booleans) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    //     函数4
    public static boolean and(Boolean... booleans) {
        System.out.println("Boolean");
        for (Boolean b : booleans) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    //tim  me#b
    //time me bell
    public int minimumLengthEncoding(String[] words) {
        List<String> strings = Arrays.asList(words);
        Set<String> set = new HashSet<>(strings);
        System.out.println("set集合的数据为："+set.toString());
        for (String s : words) {
            for (int j = 1; j < words.length; j++) {
                String substring = s.substring(j);
                System.out.println("substring的数据为："+substring);
                set.remove(substring);
                System.out.println("set循环"+j+"的数据为："+set.toString());
            }
        }
        System.out.println("set集合的数据为："+set.toString());
        int ans = 0;
        for (String word :set) {
            ans += word.length()+1;
        }
        return ans;
    }

//    public int[] smallerNumbersThanCurrent(int[] nums) {
//        int[] ints = new int[nums.length];
////        Collections.singleton(nums)
//        int [] arr = new int[nums.length];
//        System.arraycopy(nums, 0, arr, 0, arr.length);
//        TreeSet<Integer> set = new TreeSet<>();
////        for (int num : nums) {
////            list.add(num);
////        }
////
////        Arrays.sort(nums);
////        System.out.println(list.toString() + "  "+ Arrays.toString(nums) + "  "+ Arrays.toString(arr));
////        for (int i = 0; i < ints.length; i++) {
////            ints[i] = set.indexOf(nums[i]);
////        }
//        return ints;
//
////        int[] temp = new int[10];
////        for(int num : nums) {
////            temp[num]++;
////        }
////        System.out.println("数据为："+ Arrays.toString(temp));
////        int cnt = 0;
////        for (int i = 0; i <= 9; i ++) {
////            int tmp = temp[i];
////            temp[i] = cnt;
////            cnt += tmp;
////            System.out.println("循环为："+i + "  "+ tmp + "  "+ temp[i]+ "  "+ cnt);
////        }
////        System.out.println("数据为："+ Arrays.toString(temp));
////
////        int[] result = new int[nums.length];
////        for(int i = 0; i < nums.length; i++) {
////            result[i] = temp[nums[i]];
////        }
////        return result;
//
//
////        int [] ints = new int[nums.length];
////        for (int i = 0; i < nums.length; i++) {
////            for (int num : nums) {
////                if (nums[i] > num) {
////                    ints[i] += 1;
////                }
////            }
////        }
////        return ints;
//    }
//
//    public int numberOfSteps (int num) {
//        int count = 0;
//        while (num != 0){
//            if (num %2 == 0){
//                num /= 2;
//            }else{
//                num -=1;
//            }
//            count ++;
//        }
//        return count;
//    }
//
//    public String reverseLeftWords(String s, int n) {
//        return s.substring(n)+ s.substring(0, n);
//    }
//
//    public boolean hasGroupsSizeX(int[] deck) {
//        if (deck.length < 2) {
//            return false;
//        }
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int item : deck) {
//            Integer integer = map.get(item);
//            if (integer == null) {
//                map.put(item, 1);
//            } else {
//                map.put(item, integer + 1);
//            }
//        }
//        int t = 0;
//        for (int i:map.values()){
//            t = gys(t, i);
//        }
//        return t>=2;
//    }
//
//    public int gys(int a, int b) {
//        return b == 0 ? a : gys(b, a % b);
//    }
//
//    public int[] getLeastNumbers(int[] arr, int k) {
//        Arrays.sort(arr);
//        int[] ints = new int[k];
//        System.arraycopy(arr, 0, ints, 0, k);
//        return ints;
//    }
//
//    public String longestCommonPrefix(String[] strs) {
//        for (int i = 0; i < strs.length; i++) {
//            String str = strs[i];
//
//        }
//        return "";
//    }
//
////    public int romanToInt(String s) {
////
////    }
//
//    public boolean isPalindrome(int x) {
//        String s = String.valueOf(x);
//        for (int i = 0, k = s.length() - 1; i < k; i++, k--) {
//            if (s.charAt(i) != s.charAt(k)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean and(boolean... booleans) {
//        System.out.println("boolean");
//        for (boolean b : booleans) {
//            if (!b) {
//                return false;
//            }
//        }
//        return true;
//    }
//    // 函数4
////    public boolean and(Boolean... booleans) {
////        System.out.println("Boolean");
////        for (Boolean b : booleans) {
////            if (!b) {
////                return false;
////            }
////        }
////        return true;
////    }
//
//    public String[] findWords(String[] words) {
//        String regex = "^([QWERTYUIOPqwertyuiop]+|[ASDFGHJKLasdfghjkl]+|[ZXCVBNMzxcvbnm]+)$";
//        List<String> list = new ArrayList<>();
//        for (String word : words) {
//            if (word.matches(regex)) {
//                list.add(word);
//            }
//        }
//        return list.toArray(new String[0]);
//    }
//
//    public int minDeletionSize(String[] A) {
//
//
//        return 0;
//    }
//
//    public void reverseString(char[] s) {
//        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
//            s[i] ^= s[j];
//            s[j] ^= s[i];
//            s[i] ^= s[j];
//        }
//    }
//
//    public String reverseWords(String s) {
//        String[] s1 = s.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (String value : s1) {
//            StringBuilder strsb = new StringBuilder();
//            strsb.append(value);
//            strsb.reverse();
//            sb.append(strsb.toString()).append(" ");
//        }
//        return sb.toString().trim();
//    }
//
//    public int peakIndexInMountainArray(int[] A) {
//        int num = 0, max = A[0];
//        for (int i = 0; i < A.length; i++) {
//            if (max < A[i]) {
//                num = i;
//                max = A[i];
//            }
//        }
//        return num;
//    }
//
//    public int arrayPairSum(int[] nums) {
//        int num = 0;
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i += 2) {
//            num += (nums[i] <= nums[i + 1]) ? nums[i] : nums[i + 1];
//        }
//        return num;
//    }
//
//    //输出："IDID"
//    //输出：[0,4,1,3,2]
//    public int[] diStringMatch(String S) {
//        int length = S.length();
//        int[] ints = new int[length + 1];
//        int start = 0;
//        int end = length;
//        for (int i = 0; i < length; i++) {
//            boolean b = S.charAt(i) == 'I';
//            if (b) {
//                ints[i] = start;
//                start++;
//            } else {
//                ints[i] = end;
//                end--;
//            }
//        }
//        ints[length] = start;
//        return ints;
//    }
//
//    public int[] sortedSquares(int[] A) {
//        for (int i = 0; i < A.length; i++) {
//            A[i] *= A[i];
//        }
//        Arrays.sort(A);
//        return A;
//    }
//
//    public List<Integer> preorder(Node root) {
//        List<Integer> list = new ArrayList<>();
//        if (root == null) {
//            return list;
//        }
//        list.add(root.val);
//        List<Node> children = root.children;
//        for (int i = 0; i < children.size(); i++) {
//            List<Integer> preorder = preorder(children.get(i));
//            list.addAll(preorder);
//        }
//        return list;
//    }
//
//    public int maxDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int left = 0;
//        left += maxDepth(root.left);
//        int right = 0;
//        right += maxDepth(root.right);
//        return (left >= right) ? left : right;
//    }
//
//    public int heightChecker(int[] heights) {
//        int[] ints = new int[heights.length];
//        System.arraycopy(heights, 0, ints, 0, ints.length);
//        int num = 0;
//        Arrays.sort(heights);
//        for (int i = 0; i < heights.length; i++) {
//            if (heights[i] != ints[i]) {
//                num += 1;
//            }
//        }
//        return num;
//    }
//
//    public List<Integer> selfDividingNumbers(int left, int right) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = left; i <= right; i++) {
//            int num = i;
//            boolean is = true;
//            while (num > 0) {
//                int i1 = num % 10;
//                System.out.println(i1 + "  " + i + "   " + num);
//                num /= 10;
//                if (i1 == 0 || i % i1 != 0) {
//                    is = false;
//                    break;
//                }
//            }
//            if (is) {
//                list.add(i);
//            }
//        }
//        return list;
//    }
//
//    public boolean judgeCircle(String moves) {
//        int ud = 0;
//        int rl = 0;
//        for (char c : moves.toCharArray()) {
//            switch (c) {
//                case 'U':
//                    ud += 1;
//                    break;
//                case 'D':
//                    ud -= 1;
//                    break;
//                case 'R':
//                    rl += 1;
//                    break;
//                case 'L':
//                    rl -= 1;
//                    break;
//            }
//        }
//        return ud == 0 && rl == 0;
//    }
//
//    public TreeNode invertTree(TreeNode root) {
//
//        if (root.right == null && root.left == null) {
//            return root;
//        }
//        if (root.right == null) {
//            return root.left;
//        }
//        if (root.left == null) {
//            return root.right;
//        }
//        TreeNode node = new TreeNode(root.val);
//        node.left = invertTree(root.right);
//        node.right = invertTree(root.left);
//        return node;
//    }
//
//    String[] str = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.",
//            "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
//            ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
//            "-.--", "--.."};
//
//
//    public int uniqueMorseRepresentations(String[] words) {
//        Set<String> set = new HashSet<>();
//        for (String word : words) {
//            char[] chars = word.toCharArray();
//            StringBuilder sb = new StringBuilder();
//            for (char aChar : chars) {
//                sb.append(str[aChar - 97]);
//            }
//            set.add(sb.toString());
//        }
//        return set.size();
//    }
//
//    public int[][] flipAndInvertImage(int[][] A) {
//        for (int i = 0; i < A.length; i++) {
//            for (int j = 0, k = A[i].length - 1; j <= k; j++, k--) {
//                if (A[i][j] == A[i][k] && A[i][j] == 0) {
//                    A[i][j] = 1;
//                    A[i][k] = 1;
//                } else if (A[i][j] == A[i][k] && A[i][j] == 1) {
//                    A[i][j] = 0;
//                    A[i][k] = 0;
//                }
//            }
//        }
//        return A;
//    }
//
//    //逆序更好
//    public int[] replaceElements(int[] arr) {
////        int[] ints = new int[arr.length];
////        for (int i = 0; i < arr.length-1; i++) {
////            int max = arr[i+1];
////            for (int j = i+1; j < arr.length; j++) {
////                if (max < arr[j]){
////                    max = arr[j];
////                }
////            }
////            ints[i] = max;
////        }
////        ints[arr.length-1] = -1;
////        return ints;
//
//        int max = -1;
//        for (int i = arr.length - 1; i >= 0; i--) {
//            int temp = arr[i];
//            arr[i] = max;
//            if (temp > max) {
//                max = temp;
//            }
//        }
//        return arr;
//    }
//
//    public int[] sumZero(int n) {
//        int[] ints = new int[n];
//        if (n == 1) {
//            ints[0] = 0;
//            return ints;
//        }
//        int j = 1;
//        for (int i = 0; i < n / 2 * 2; i++) {
//            if (i % 2 == 0) {
//                ints[i] = j;
//            } else {
//                ints[i] = -j;
//                j++;
//            }
//        }
//        return ints;
//    }
//
//    public String freqAlphabets(String s) {
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//        while (i < s.length()) {
//            if (s.charAt(i + 2) == '#') {
//                sb.append((char) ('a' + Integer.parseInt(s.substring(i, i + 2)) - 1));
//                i += 3;
//            } else {
//                sb.append((char) ('a' + Integer.parseInt(s.substring(i, i + 1)) - 1));
//                i++;
//            }
//        }
//        return sb.toString();
//    }
//
////    public int[] sumZero(int n) {
////
////    }
//
//    public int getDecimalValue(ListNode head) {
//        int ans = 0;
//        while (head != null) {
//            ans <<= 1;
//            ans += head.val;
//            head = head.next;
//        }
//        return ans;
//    }
//
//    public int minTimeToVisitAllPoints(int[][] points) {
//        int num = 0;
//        for (int i = 0; i < points.length - 1; i++) {
//            int[] point = points[i];
//            int[] point1 = points[i + 1];
//            int i1 = point1[0] - point[0];
//            int i2 = point1[1] - point[1];
//            int i3 = (i1 < 0) ? -i1 : i1;
//            int i4 = (i2 < 0) ? -i2 : i2;
//            num += (i3 > i4 ? i3 : i4);
//        }
//        return num;
//    }
//
//    public int findNumbers(int[] nums) {
//        int num = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if ((nums[i] + "").length() % 2 == 0) {
//                num++;
//            }
//        }
//        return num;
//    }
//
//    public int subtractProductAndSum(int n) {
//        int sum = 0;
//        int product = 1;
//        while (n > 0) {
//            int i = n % 10;
//            sum += i;
//            product *= i;
//            n /= 10;
//        }
//        System.out.println(sum + "  " + product);
//        return product - sum;
//    }
//
//    public int hammingDistance(int x, int y) {
//        String i = Integer.toBinaryString(x);
//        String s = Integer.toBinaryString(y);
//        int i1 = x ^ y;
//        System.out.println(i + "  " + s + "  " + i1);
//
//        return Math.abs(i.length() - s.length());
//    }
//
//    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//        if (t1 == null && t2 == null) {
//            return null;
//        }
//        if (t1 == null) {
//            return t2;
//        }
//        if (t2 == null) {
//            return t1;
//        }
//        TreeNode treeNode = new TreeNode(t1.val + t2.val);
//        treeNode.left = mergeTrees(t1.left, t2.left);
//        treeNode.right = mergeTrees(t1.right, t2.right);
//        return treeNode;
//    }
//
//    public String toLowerCase(String str) {
//        char[] chars = str.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] >= 'A' && chars[i] <= 'Z') {
//                chars[i] += 32;
//            }
//        }
//        return new String(chars);
//    }
//
//    public int rangeSumBST(TreeNode root, int L, int R) {
//        if (root == null) {
//            return 0;
//        }
//        int number = 0;
//        if (root.val > L && root.val < R) {
//            number += root.val;
//        }
//        if (root.left.val < L) {
//            return 0;
//        } else {
//            number += rangeSumBST(root.left, L, R);
//        }
//        if (root.right.val > R) {
//            return 0;
//        } else {
//            number += rangeSumBST(root.right, L, R);
//        }
//        return number;
//    }
//
//    public String removeOuterParentheses(String S) {
//        char[] chars = S.toCharArray();
//        StringBuilder parentheses = new StringBuilder();
//        int num = -1;
//        for (char ch : chars) {
//            if (ch == '(') {
//                num++;
//            } else {
//                num--;
//            }
//            if (num == 0 && ch == '(' || num == -1 && ch == ')') {
//                continue;
//            }
//            parentheses.append(ch);
//        }
//        return parentheses.toString();
//    }
//
//    public int balancedStringSplit(String s) {
//        int number = 0;
//        char[] chars = s.toCharArray();
//        int num = 0;
//        for (char ch : chars) {
//            if (ch == 'L') {
//                num++;
//            } else {
//                num--;
//            }
//            if (num == 0) {
//                number++;
//            }
//        }
//        return number;
//    }


}

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int x) {
//        val = x;
//    }
//}
//
//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode(int x) {
//        val = x;
//    }
//}
//
//class Node {
//    public int val;
//    public List<Node> children;
//
//    public Node() {
//    }
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, List<Node> _children) {
//        val = _val;
//        children = _children;
//    }
//};

