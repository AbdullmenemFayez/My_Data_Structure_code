package com.company;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BTS {
    TreeNode root;

    void insert(int val) {
        insert(root, val);
    }

    private void insert(TreeNode root, int val) {
        if (root == null) {
            this.root = new TreeNode(val);
        } else {
            if (root.val > val) {
                if (root.left == null) root.left = new TreeNode(val);
                else insert(root.left, val);
            } else if (root.val < val) {
                if (root.right == null) root.right = new TreeNode(val);
                else insert(root.right, val);
            }

        }
    }

    void remove(int val) {
        root = remove(root, val);
    }

    private TreeNode remove(TreeNode root, int val) {
        if (root == null) return root;
        if (root.val < val) root.right = remove(root.right, val);
        else if (root.val > val) root.left = remove(root.left, val);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;


            root.val = MaxValue(root.left);
            root.left = remove(root.left, root.val);
        }
        return root;

    }

    int MaxValue(TreeNode root) {
        int ret = Integer.MIN_VALUE;
        while (root != null) {
            ret = Math.max(ret, root.val);
            root = root.right;
        }
        return ret;
    }

    void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.val + " ");
        }
    }

    void BFS() {
        if (root != null) {
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curr = q.poll();
                    if (curr.left != null) q.add(curr.left);
                    if (curr.right != null) q.add(curr.right);

                    System.out.print(curr.val + " ");
                }
                //    System.out.println();
            }
        }

    }

    void printLevelOrder(TreeNode root) {
        int h = height(root);
        for (int i = 0; i < h; i++) {
            printLevelOrder(root, i);
        }
    }

    void printLevelOrder(TreeNode root, int level) {
        if (root == null) return;
        if (level == 0) System.out.print(root.val + " ");
        else {
            printLevelOrder(root.left, level - 1);
            printLevelOrder(root.right, level - 1);
        }

    }


    int height(TreeNode p) {
        if (p == null)
            return 0;

        return 1 + Math.max(height(p.left), height(p.right));
    }

   /* public boolean isSubPath(ListNode head, TreeNode root) {
        Stack<ListNode> st = new Stack<>();
        while (root != null) {
            if (head == null)
                return true;

            if (!st.isEmpty() && root.val != head.val) {
                return false;
            }
            if (root.val == head.val) {
                st.push(head);
                head = head.next;
            }
            if (head == null)
                return true;
            if (root.val < head.val) {
                root = root.right;
            } else if (root.val > head.val)
                root = root.left;

        }

        return false;

    }
*/

   /* public void myStrey(TreeNode root, int n1, int n2) {
        while (root != null) {
            if (root.val > n1 && root.val > n2) root = root.left;
            else if (root.val < n1 && root.val < n2) root = root.right;
            else break;
        }
        System.out.println(root.val);
    }*/

    void fun(TreeNode node) {
        TreeNode ol;
        if (node == null) return;
        fun(node.left);
        fun(node.right);
        ol = node.left;
        node.left = new TreeNode(node.val);
        node.left.left = ol;
    }

    void mirror(TreeNode root) {
        if (root != null) {
            mirror(root.right);
            System.out.print(root.val + " ");
            mirror(root.left);
        }
    }

    //static int sum = 0;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    List<Integer> getSortedList(TreeNode root) {
        return helper(root, new ArrayList<>());
    }

    List<Integer> helper(TreeNode root, ArrayList<Integer> list) {

        if (root != null) {
            helper(root.left, list);
            list.add(root.val);
            helper(root.left, list);
        }
        return list;
    }

    private boolean isFull(TreeNode root) {
        if (root == null) return true;
        boolean ans = true;
        ans = (root.left != null || root.right == null) && (root.left == null || root.right != null);

        return ans && isFull(root.left) && isFull(root.right);
    }


    boolean helper(int[] pre) {
        Stack<Integer> s = new Stack<>();
        int root = Integer.MIN_VALUE;
        for (int i = 0; i < pre.length; i++) {
            if (pre[i] < root) {
                return false;
            }
            while (!s.isEmpty() && s.peek() < pre[i]) {
                root = s.pop();

            }
            s.push(pre[i]);
        }
        return true;
    }

    void removeRange(int low, int high) {
        helperx(root, low, high);
    }

    TreeNode helperx(TreeNode root, int low, int high) {
        if (root != null) {
            if (root.val > high || root.val < low) {
                System.out.print(root.val + " ");
                remove(root.val);
            }
            helperx(root.left, low, high);
            helperx(root.right, low, high);
        }
        return root;
    }


    TreeNode fun(TreeNode root, int target) {

        if (root == null) return null;
        if (root.left != null) root.left = fun(root.left, target);
        if (root.right != null) root.right = fun(root.right, target);
        if ((root.val == target) && (root.left == null && root.right == null)) return null;
        return root;
    }

    boolean fun(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return (root1.val == root2.val) && fun(root1.left, root2.left) && fun(root1.right, root2.right);
    }

    int helper(TreeNode root, int low, int high) {
        return 0;

    }

    public int findTilt(TreeNode root) {
        int[] arr = {0};
        helper(root, arr);
        return arr[0];
    }

    int helper(TreeNode root, int[] arr) {
        if (root == null) return 0;
        int left = helper(root.left, arr);
        int right = helper(root.right, arr);
        arr[0] += Math.abs(left - right);
        return left + right + root.val;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                ans += root.left.val;
            else
                ans += sumOfLeftLeaves(root.left);
        }
        ans += sumOfLeftLeaves(root.right);

        return ans;
    }
  /*  boolean fun(TreeNode root, String x) {
        if (root.val == x.intAt(0))
            return helper(root, x, 1);
        return false;
    }

    boolean helper(TreeNode root, String x, int ind) {
        if (ind == x.length()) return true;
        boolean flag = false;
        if (root.left != null && root.left.val == x.intAt(ind))
            flag = flag || helper(root.left, x, ind + 1);
        if (root.right != null && root.right.val == x.intAt(ind))
            flag = flag || helper(root.right, x, ind + 1);
        return flag;
    }*/

    /*String Solve(String S) {
        if (this.root.val.equals(S.intAt(0))) {
            String Ans = "Root ";
            Ans += Solve(this.root, S, 1);
            if (Ans.intAt(Ans.length() - 1) == '0') return " Not.Found";
            else return Ans;
        } else return "Not Found";
    }

    String Solve(TreeNode Curr, String S, int Idx) {
        if (Idx == S.length()) return "1";
        if (Curr.left != null && Curr.left.val.equals(S.intAt(Idx))) {
            String Ans = "Left " + Solve(Curr.left, S, Idx + 1);
            if (Ans.intAt(Ans.length() - 1) == '1') return Ans;
        }
        if (Curr.right != null && Curr.right.val.equals(S.intAt(Idx))) {
            String Ans = "Right " + Solve(Curr.right, S, Idx + 1);
            if (Ans.intAt(Ans.length() - 1) == '1') return Ans;
        }
        return "0";

    }

*/
   /* boolean hasPathSum(TreeNode node, int sum) {
        boolean ans = false;
        Integer x = node.val;
        int subSum = sum - x;
        if (subSum == 0 && node.left == null && node.right == null) {
            return ans = true;
        }
        if (node.left != null)
            ans = ans || hasPathSum(node.left, subSum);
        if (node.right != null)
            ans = ans || hasPathSum(node.right, subSum);
        return ans;
    }*/
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }

    boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        return (left.val == right.val) && helper(left.left, right.right) && helper(left.right, right.left);
    }

    TreeNode invertTree(TreeNode root) {
        return helper(root);
    }

    TreeNode helper(TreeNode root) {
        if (root != null) {
            TreeNode left = helper(root.left);
            root.left = helper(root.right);
            root.right = left;
        }
        return root;
    }

    public TreeNode sortedArrayToBST(int[] x) {

        return sortedArrayToBST(x, 0, x.length - 1);
    }

    TreeNode sortedArrayToBST(int[] x, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(x[mid]);

        root.left = sortedArrayToBST(x, left, mid - 1);
        root.right = sortedArrayToBST(x, mid + 1, right);
        return root;
    }

    public boolean isValidBST(TreeNode root) {
        return helperx(root);
    }

    boolean helperx(TreeNode root) {
        if (root != null) return true;
        boolean ans = false;
        if (root.left != null && !(root.left.val >= root.val)) ans = true;
        if (root.right != null && !(root.right.val <= root.val)) ans = true;

        ans = ans && helperx(root.left) && helperx(root.right);
        return ans;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int In = inorder.length - 1;
        int pre = 0;
        if (preorder.length == 0 || In + 1 == 0 || In + 1 != preorder.length) return null;
        TreeNode root = new TreeNode(preorder[pre++]);
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        TreeNode prev = null;
        while (pre < preorder.length) {
            while (!st.isEmpty() && st.peek().val == inorder[In]) {
                prev = st.pop();
                --In;
            }
            TreeNode newNode = new TreeNode(preorder[pre++]);
            if (prev != null) {
                prev.left = newNode;
            } else if (!st.isEmpty()) {
                prev = st.peek();
                prev.right = newNode;
            }
            st.push(newNode);
        }
        return root;
    }

    /* public TreeNode buildTree(int[] preorder, int[] inorder) {
         int In = 0;
         int pre = 0;
         if (preorder.length == 0) return null;
         TreeNode root = new TreeNode(preorder[pre++]);
         Stack<TreeNode> st = new Stack<>();
         st.push(root);
         TreeNode prev = root;
         while (pre < preorder.length) {
             TreeNode newNode = new TreeNode(preorder[pre++]);
             if (prev.val != inorder[In]) {
                 {
                     prev.left = newNode;
                     prev = prev.left;
                 }
             } else {
                 while (!st.isEmpty() && st.peek().val == inorder[In]) {
                     prev = st.pop();
                     ++In;
                 }
                 prev.right = newNode;
                 prev = prev.right;
             }
             st.push(newNode);

         }
         return root;
     }*/
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[0]);
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        for (int i = 1, j = 0; i < pre.length; i++) {
            TreeNode node = new TreeNode(pre[i]);
            while (st.peek().val == post[j]) {
                st.pop();
                j++;
            }

            if (st.peek().left == null)
                st.peek().left = node;
            else st.peek().right = node;
            st.push(node);
        }

        return root;
    }

    /*public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node curr = null, next = null;
            for (int i = 0; i < size; i++) {
                curr = q.poll();
                if (i < size - 1) {
                    next = q.peek();
                    curr.next = next;
                }
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return root;

    }*/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > q.val && root.val > p.val)
                root = root.left;
            else if (root.val < q.val && root.val < p.val)
                root = root.right;
            else break;

        }
        return root;

    }

    static ArrayList<String> generate(int N) {
        Queue<String> q = new LinkedList<>();
        q.add("1");
        ArrayList<String> list = new ArrayList<>();
        //list.add("1");
        while (N-- > 0) {
            String curr = q.poll();
            list.add(curr);
            q.add(curr + "0");
            q.add(curr + "1");
        }
        return list;
    }

    public boolean isBalanced(TreeNode root) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        height(root, ans);
        return ans[0];

    }

    int height(TreeNode root, boolean[] ans) {
        if (root == null) return 0;
        int L = height(root.left, ans);
        int R = height(root.right, ans);
        if (Math.abs(L - R) > 1) ans[0] = false;


        return 1 + Math.max(R, L);
    }

    /*public int maxSumBST(TreeNode root) {
        int[] arr = new int[1];
        arr[0] = Integer.MIN_VALUE;
        helperx(root, arr);
        return arr[0];

    }

    void helperx(TreeNode root, int[] arr) {
        if (root == null) return;
        ArrayList<Integer> list = new ArrayList<>();
        if (isBST(root, list)) {
            arr[0] = Math.max(arr[0], list.get(list.size() - 1));
        }
        helperx(root.left, arr);
        helperx(root.right, arr);
    }

    boolean isBST(TreeNode root, ArrayList<Integer> list) {
        list = helperx(root, list);
        int sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) >= list.get(i - 1)) return false;
            sum += list.get(i);
        }

        list.add(sum);
        return true;

    }

    ArrayList<Integer> helperx(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {
            helperx(root.left, list);
            list.add(root.val);
            helperx(root.right, list);
        }
        return list;
    }
*/
    public int maxSumBST(TreeNode root) {
        int[] max = new int[1];

        maxSumBST(root, max);
        return max[0];
    }

    int[] maxSumBST(TreeNode root, int[] max) {
        if (root == null) return new int[]{1, Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        int[] L = maxSumBST(root.left, max);
        int[] R = maxSumBST(root.right, max);
        boolean isBST = L[0] == 1 && R[0] == 1 && root.val > L[1] && root.val < R[2];
        int sum = L[3] + R[3] + root.val;
        if (isBST) max[0] = Math.max(max[0], sum);
        return new int[]{isBST ? 1 : 0, Math.max(root.val, R[1]), Math.min(root.val, L[2]), sum};
    }

    String path(TreeNode root, int val) {
        String ans = "";
        while (root != null && root.val != val) {
            ans += root.val + "->";
            if (root.val < val) {

                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root == null ? "" : ans + root.val;
    }

    static int findDepth(TreeNode root, int x) {

        if (root == null)
            return -1;

        int dist = -1;
        if ((root.val == x) || (dist = findDepth(root.left, x)) >= 0 || (dist = findDepth(root.right, x)) >= 0)
            return dist + 1;

        return dist;
    }


    public boolean isCompleteTree(TreeNode root) {
        return isComplete(root, 0, countNodes(root));
    }

    boolean isComplete(TreeNode root, int index, int number_nodes) {
        if (root == null)
            return true;
        if (index >= number_nodes)
            return false;
        return (isComplete(root.left, 2 * index + 1, number_nodes) && isComplete(root.right, 2 * index + 2, number_nodes));
    }

    int countNodes(TreeNode root) {
        if (root == null)
            return (0);
        return (1 + countNodes(root.left) + countNodes(root.right));
    }

    int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        int[] arr = new int[1];
        count = 0;
        helper(root, k, arr);
        return arr[0];
    }

    void helper(TreeNode root, int k, int[] arr) {
        if (root != null) {
            helper(root.left, k, arr);
            ++count;
            if (count == k) arr[0] = root.val;
            helper(root.right, k, arr);
        }
    }

    public int kthLargest(TreeNode root, int k) {
        int[] arr = new int[1];
        count = 0;
        helperLargest(root, k, arr);
        return arr[0];
    }

    void helperLargest(TreeNode root, int k, int[] arr) {
        if (root != null) {
            helperLargest(root.right, k, arr);
            ++count;
            if (count == k) arr[0] = root.val;
            helperLargest(root.left, k, arr);
        }
    }


    public static boolean findSubTrees(TreeNode root, int low, int high, int[] count) {
        if (root == null) {
            return true;
        }
        boolean left = findSubTrees(root.left, low, high, count);
        boolean right = findSubTrees(root.right, low, high, count);

        if (left && right && (root.val >= low && root.val <= high)) {
            count[0]++;
            return true;
        }

        return false;
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val > high) return trimBST(root.left, low, high);
        if (root.val < low) return trimBST(root.right, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = inOrder(root, new ArrayList<>());
        return helper(list, 0, list.size() - 1);
    }

    ArrayList<Integer> inOrder(TreeNode root, ArrayList<Integer> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
        return list;
    }

    TreeNode helper(ArrayList<Integer> list, int Start, int End) {
        if (Start >= End) return null;
        int mid = Start + (End - Start) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(list, Start, mid - 1);
        root.right = helper(list, mid + 1, End);
        return root;
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        return helper(root, 0, new ArrayList<>());
    }

    List<Integer> helper(TreeNode root, int level, List<Integer> list) {
        if (root == null) return list;
        if (level == list.size())
            list.add(root.val);
        helper(root.right, level + 1, list);
        helper(root.left, level + 1, list);
        return list;
    }

    String ans = "~";

    public String smallestFromLeaf(TreeNode root) {
        helper(root, "");
        return new String(new StringBuilder(ans).reverse());
    }

    void helper(TreeNode root, String str) {
        if (root == null) return;
        str += ((char) (root.val + 'a'));
        if (root.left == null && root.right == null && (new String(new StringBuilder(str).reverse()).compareTo(ans) < 0)) {
            ans = str;
        }
        helper(root.left, str);
        helper(root.right, str);
        str = str.substring(0, str.length() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return helper(root, targetSum, new LinkedList<>(), new LinkedList<>());
    }

    List<List<Integer>> helper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> ans) {
        if (root == null) return ans;
        list.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            ans.add(new LinkedList<>(list));
            list.remove(list.size() - 1);
            return ans;
        } else {
            helper(root.left, sum - root.val, list, ans);
            helper(root.right, sum - root.val, list, ans);
        }
        list.remove(list.size() - 1);
        return ans;
    }

    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) stringBuilder.append("[]");
        serialize(root, stringBuilder);
        return stringBuilder.toString();
    }

    void serialize(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("null").append(",");
        } else {
            str.append(root.val).append(",");
            serialize(root.left, str);
            serialize(root.right, str);
        }
    }

    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<String>(Arrays.asList(data.split(",")));

        return deserialize(q);
    }

    TreeNode deserialize(Queue<String> q) {
        String val = q.remove();
        if (val.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(q);
        root.right = deserialize(q);
        return root;

    }

    public boolean isValidSerialization(String preorder) {
        String[] tree = preorder.split(",");
        int i = 1;
        for (String j : tree) {
            if (--i < 0) return false;
            if (!j.equals("#")) i += 2;
        }
        return i == 0;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null) return list;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
                max = (max < curr.val) ? curr.val : max;
            }
            list.add(max);
        }
        return list;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;

    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) return root;
        TreeNode newNode = new TreeNode(val, root, null);
        if (depth == 1) return newNode;
        Queue<TreeNode> q = new LinkedList<>();
        int level = 1;

        q.add(root);
        TreeNode L = null, R = null;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                if (level == depth - 1) {
                    L = curr.left;
                    curr.left = new TreeNode(val);
                    curr.left.left = L;
                    R = curr.right;
                    curr.right = new TreeNode(val);
                    curr.right.right = R;
                }
            }
            if (depth == level) break;
            ++level;
        }
        return root;
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        q.add(root);
        list.add(0);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                int Ind = list.removeFirst();
                if (curr.left != null) {
                    q.add(curr.left);
                    list.add(Ind * 2 + 1);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                    list.add(Ind * 2 + 2);
                }
            }
            if (list.size() > 1) {
                int temp = list.getLast() - list.getFirst();
                res = Math.max(res, temp);
            }
        }
        return res;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        helper(root, new HashMap<>(), list);
        return list;
    }

    String helper(TreeNode root, HashMap<String, Integer> h, List<TreeNode> list) {
        if (root == null) return " ";
        String str = root.val + "," + helper(root.left, h, list) + "," + helper(root.right, h, list);
        h.put(str, h.getOrDefault(str, 0) + 1);
        if (h.get(str) == 2) list.add(root);
        return str;
    }

    static boolean isCousins(TreeNode root, int a, int b) {
        if (a == b) {
            return false;
        }
        return BFS(root, a, b);
    }

    static boolean BFS(TreeNode root, int a, int b) {
        Queue<TreeNode> q = new LinkedList<>();
        if (a > b) {
            int x = a;
            a = b;
            b = x;
        }
        q.add(root);
        while (!q.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0, j = 1; i < size; j++, i++) {
                TreeNode curr = q.poll();

                if (curr == null) {
                    list.add(null);
                } else {
                    if (curr.left != null) {
                        q.add(curr.left);

                    }
                    if (curr.right != null) {
                        q.add(curr.right);

                    }
                    q.add(null);
                    list.add((curr.val));
                }
            }
            System.out.println(list);
            if (list.indexOf(a) + 1 == list.indexOf(b)) {
                return false;
            }
        }
        return true;
    }

    public void printLevels() {
        int size = height(root);

        for (int i = 1; i <= size; i++) {
            System.out.println(printLevels(root, i, 0));
            System.out.println(" ");
        }


    }

    private int printLevels(TreeNode root, int level, int count) {
        if (root == null) return 0;
        if (level == 1) {
            System.out.println(root.val);
            int x = root.val;
            return 1;
        }
        count += printLevels(root.left, level - 1, count) + printLevels(root.right, level - 1, count);

        return count;
    }


    static boolean isCousinsx(TreeNode root, int a, int b) {
        if (a == b) {
            return false;
        }
        return BFSx(root, a, b);
    }

    static boolean BFSx(TreeNode root, int a, int b) {
        Queue<TreeNode> q = new LinkedList<>();
        if (a > b) {
            int x = a;
            a = b;
            b = x;
        }
        q.add(root);
        while (!q.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0, j = 1; i < size; j++, i++) {
                TreeNode curr = q.poll();

                if (curr == null) {
                    list.add(null);// 1 ,2 ,null, 3 , 4
                } else {
                    if (curr.left != null) {
                        q.add(curr.left);

                    }
                    if (curr.right != null) {
                        q.add(curr.right);
                        q.add(null);
                    }
                    list.add((Integer) curr.val);
                }
            }
            if (list.indexOf(a) + 1 == list.indexOf(b)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        BTS test = new BTS();
        System.out.println(test.isCorrect("22 -5  + 3 = 4 * 5 + 10"));
        test.isCorrect("22*12");

    }

    boolean isCorrect(String formula) {
        String[] temp = formula.split("=");
        String str1 = infixToPostfix(temp[0]);
        String str2 = infixToPostfix(temp[1]);
        if ("Invalid Expression".equals(str1) || "Invalid Expression".equals(str2)) return false;
        return evaluatePostfix(str1) == evaluatePostfix(str2);
    }

    private int Prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private String infixToPostfix(String exp) {
        exp = exp.replaceAll(" ", "");
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                while (i < exp.length() && Character.isDigit(exp.charAt(i)))
                    ans.append(exp.charAt(i++));
                ans.append(" ");
                --i;
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    ans.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {
                    ans.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            ans.append(stack.pop()).append(" ");
        }
        return ans.toString();
    }

    private int evaluatePostfix(String exp) {
        Stack<Integer> stack = new Stack<>();
        String[] arr = exp.split(" ");
        for (String s : arr) {
            if (Character.isDigit(s.charAt(0))) {
                stack.push(Integer.parseInt(s));
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(val2 + val1);
                        break;
                    case "-":
                        stack.push(val2 - val1);
                        break;
                    case "/":
                        stack.push(val2 / val1);
                        break;
                    case "*":
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();

    }
}


