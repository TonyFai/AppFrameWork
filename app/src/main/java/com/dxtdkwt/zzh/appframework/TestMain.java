package com.dxtdkwt.zzh.appframework;

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
        int i = solution.hammingDistance(93,73);
        System.out.println(s);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {

    public int hammingDistance(int x, int y) {
        String i = Integer.toBinaryString(x);
        String s = Integer.toBinaryString(y);
        return Math.abs(i.length() - s.length());
    }

    public int[] sumZero(int n) {
        int[] ints = new int[n];
        if (n == 1) {
            ints[0] = 0;
            return ints;
        }
        int j = 1;
        for (int i = 0; i < n / 2 * 2; i++) {
            if (i % 2 == 0) {
                ints[i] = j;
            } else {
                ints[i] = -j;
                j++;
            }
        }
        return ints;
    }

    public String freqAlphabets(String s) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i + 2) == '#') {
                sb.append((char) ('a' + Integer.parseInt(s.substring(i, i + 2)) - 1));
                i += 3;
            } else {
                sb.append((char) ('a' + Integer.parseInt(s.substring(i, i + 1)) - 1));
                i++;
            }
        }
        return sb.toString();
    }

//    public int[] sumZero(int n) {
//
//    }

    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans <<= 1;
            ans += head.val;
            head = head.next;
        }
        return ans;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int num = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int[] point = points[i];
            int[] point1 = points[i + 1];
            int i1 = point1[0] - point[0];
            int i2 = point1[1] - point[1];
            int i3 = (i1 < 0) ? -i1 : i1;
            int i4 = (i2 < 0) ? -i2 : i2;
            num += (i3 > i4 ? i3 : i4);
        }
        return num;
    }

    public int findNumbers(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] + "").length() % 2 == 0) {
                num++;
            }
        }
        return num;
    }

    public int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;
        while (n > 0) {
            int i = n % 10;
            sum += i;
            product *= i;
            n /= 10;
        }
        System.out.println(sum + "  " + product);
        return product - sum;
    }

    public int hammingDistance(int x, int y) {
        String i = Integer.toBinaryString(x);
        String s = Integer.toBinaryString(y);
        int i1 = x ^ y;
        System.out.println(i + "  " + s + "  " + i1);

        return Math.abs(i.length() - s.length());
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode treeNode = new TreeNode(t1.val + t2.val);
        treeNode.left = mergeTrees(t1.left, t2.left);
        treeNode.right = mergeTrees(t1.right, t2.right);
        return treeNode;
    }

    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 32;
            }
        }
        return new String(chars);
    }

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int number = 0;
        if (root.val > L && root.val < R) {
            number += root.val;
        }
        if (root.left.val < L) {
            return 0;
        } else {
            number += rangeSumBST(root.left, L, R);
        }
        if (root.right.val > R) {
            return 0;
        } else {
            number += rangeSumBST(root.right, L, R);
        }
        return number;
    }

    public String removeOuterParentheses(String S) {
        char[] chars = S.toCharArray();
        StringBuilder parentheses = new StringBuilder();
        int num = -1;
        for (char ch : chars) {
            if (ch == '(') {
                num++;
            } else {
                num--;
            }
            if (num == 0 && ch == '(' || num == -1 && ch == ')') {
                continue;
            }
            parentheses.append(ch);
        }
        return parentheses.toString();
    }

    public int balancedStringSplit(String s) {
        int number = 0;
        char[] chars = s.toCharArray();
        int num = 0;
        for (char ch : chars) {
            if (ch == 'L') {
                num++;
            } else {
                num--;
            }
            if (num == 0) {
                number++;
            }
        }
        return number;
    }


}