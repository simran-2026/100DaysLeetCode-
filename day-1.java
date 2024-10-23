/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private List<Integer> levelSums = new ArrayList<>();
    public TreeNode replaceValueInTree(TreeNode root) {
          calculateLevelSums(root, 0); // Step 1: Calculate sums for all levels.
        root.val = 0;                // Step 2: Set the root value to 0 as per the requirement.
        replaceValues(root, 1);      // Step 3: Start replacing the values from the first level.
        return root;                 // Return the modified tree.
    }

    // DFS helper method to calculate sum of all nodes at each depth level.
    private void calculateLevelSums(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // If this is the first time we're visiting this depth level, add a new sum entry.
        if (levelSums.size() <= depth) {
            levelSums.add(0);
        }
        // Update the current level sum with the value of the current node.
        levelSums.set(depth, levelSums.get(depth) + node.val);
        // Recurse on left and right children.
        calculateLevelSums(node.left, depth + 1);
        calculateLevelSums(node.right, depth + 1);
    }

    // DFS helper method to replace node values based on the previously calculated sums.
    private void replaceValues(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // Compute the values to deduct (current node's children values if they aren't null).
        int leftValue = node.left == null ? 0 : node.left.val;
        int rightValue = node.right == null ? 0 : node.right.val;

        // If the left child exists, replace its value based on the sum at the current depth.
        if (node.left != null) {
            node.left.val = levelSums.get(depth) - leftValue - rightValue;
        }
        // If the right child exists, do the same for the right child.
        if (node.right != null) {
            node.right.val = levelSums.get(depth) - leftValue - rightValue;
        }

        // Recurse on left and right children to continue the process.
        replaceValues(node.left, depth + 1);
        replaceValues(node.right, depth + 1);
    }
}
