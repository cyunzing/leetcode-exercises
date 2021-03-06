package com.zing.leetcode.explore.learn;

import com.zing.structure.Node;
import com.zing.structure.TreeNode;

import java.util.*;

/**
 * @author Zing
 * @date 2020-05-10
 */
public class TreeSolution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        list.add(root.val);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.empty() || node != null) {
            if (node != null) {
                stack.push(node);
                list.add(node.val);
                node = node.left;
            } else {
                node = stack.pop().right;
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }

            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);
        return list;
    }

    /**
     * 前序遍历：中->左->右
     * 后序遍历：左->右->中
     * 后序遍历如何通过前序遍历求解？
     * 中->左->右的访问顺序变为中->右->左
     * 中->右->左反向观察为左->右->中
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            list.add(0, node.val);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.empty() || node != null) {
            if (node != null) {
                stack.push(node);
                list.add(0, node.val);
                node = node.right;
            } else {
                node = stack.pop().left;
            }
        }
        return list;
    }

    /**
     * Binary Tree Level Order Traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        levelOrder(root, list, 0);
        return list;
    }

    private void levelOrder(TreeNode root, List<List<Integer>> list, int level) {
        if (root != null) {
            if (list.size() == level) {
                list.add(new ArrayList<>());
            }
            list.get(level).add(root.val);
            levelOrder(root.left, list, level + 1);
            levelOrder(root.right, list, level + 1);
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(level);
        }

        return list;
    }

    /**
     * Maximum Depth of Binary Tree
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * Symmetric Tree
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        } else if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode n1 = stack.pop(), n2 = stack.pop();
            if (n1 == null && n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null || n1.val != n2.val) {
                return false;
            }
            stack.push(n1.left);
            stack.push(n2.right);
            stack.push(n1.right);
            stack.push(n2.left);
        }
        return true;
    }

    /**
     * Path Sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * Construct Binary Tree from Inorder and Postorder Traversal
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        TreeNode node = null;
        if (pe >= ps && ie >= is) {
            node = new TreeNode(postorder[pe]);

            int index = -1;
            for (int i = is; i <= ie; ++i) {
                if (inorder[i] == node.val) {
                    index = i;
                }
            }

            int leftLength = index - is;
            node.left = buildTree(inorder, is, index - 1, postorder, ps, ps + leftLength - 1);
            node.right = buildTree(inorder, index + 1, ie, postorder, ps + leftLength, pe - 1);
        }

        return node;
    }

    /**
     * Construct Binary Tree from Preorder and Inorder Traversal
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null || inorder.length != preorder.length) {
            return null;
        }
        return buildTree2(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree2(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        TreeNode node = null;
        if (pe >= ps && ie >= is) {
            node = new TreeNode(preorder[ps]);

            int index = -1;
            for (int i = is; i <= ie; ++i) {
                if (inorder[i] == node.val) {
                    index = i;
                }
            }

            int leftLength = index - is;
            node.left = buildTree2(preorder, ps + 1, ps + leftLength, inorder, is, index - 1);
            node.right = buildTree2(preorder, ps + 1 + leftLength, pe, inorder, index + 1, ie);
        }

        return node;
    }

    /**
     * Populating Next Right Pointers in Each Node
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.get(i).next = queue.get(i + 1);
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }

        Node pre = root;
        Node cur = null;
        while (pre.left != null) {
            cur = pre;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            pre = pre.left;
        }
        return root;
    }

    /**
     * Populating Next Right Pointers in Each Node II
     */
    public Node connectII(Node root) {
        if (root == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.get(i).next = queue.get(i + 1);
            }
        }
        return root;
    }

    public Node connectII2(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;
        Node pre = null;
        Node node = null;

        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (node != null) {
                        node.next = cur.left;
                    } else {
                        pre = cur.left;
                    }
                    node = cur.left;
                }
                if (cur.right != null) {
                    if (node != null) {
                        node.next = cur.right;
                    } else {
                        pre = cur.right;
                    }
                    node = cur.right;
                }
                cur = cur.next;
            }
            cur = pre;
            pre = null;
            node = null;
        }
        return root;
    }

    public Node connectII3(Node root) {
        if (root == null) {
            return null;
        }

        Node tmp = new Node(0);
        Node node = root;
        while (node != null) {
            Node cur = tmp;
            while (node != null) {
                if (node.left != null) {
                    cur.next = node.left;
                    cur = node.left;
                }
                if (node.right != null) {
                    cur.next = node.right;
                    cur = node.right;
                }
                node = node.next;
            }
            node = tmp.next;
            tmp.next = null;
        }
        return root;
    }

    /**
     * Lowest Common Ancestor of a Binary Tree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * Serialize and Deserialize Binary Tree
     */
    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return deserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("null").append(",");
            } else {
                sb.append(root.val).append(",");
                serialize(root.left, sb);
                serialize(root.right, sb);
            }
        }

        private TreeNode deserialize(Queue<String> list) {
            if (list.isEmpty()) {
                return null;
            }
            String val = list.remove();
            if ("null".equals(val)) {
                return null;
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(val));
                node.left = deserialize(list);
                node.right = deserialize(list);
                return node;
            }
        }
    }

    /**
     * Count Univalue Subtrees
     */
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1];
        isUnival(root, -1, count);
        return count[0];
    }

    private boolean isUnival(TreeNode node, int val, int[] count) {
        if (node == null) {
            return true;
        }
        if (isUnival(node.left, node.val, count) && isUnival(node.right, node.val, count)) {
            count[0]++;
            return node.val == val;
        } else {
            return false;
        }
    }

}
