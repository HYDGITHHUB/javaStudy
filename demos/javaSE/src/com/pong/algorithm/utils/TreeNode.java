package com.pong.algorithm.utils;

import java.util.ArrayList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode makeTree(Integer[] trees) {
        if (trees.length == 0)
            return null;
        TreeNode[] treeNodes = new TreeNode[trees.length + 1];
        for (int i = 1; i < treeNodes.length; i++) {
            if (trees[i - 1] == null) {
                treeNodes[i] = null;
            } else {
                treeNodes[i] = new TreeNode(trees[i - 1]);
            }
        }

        TreeNode treeNode = null;
        //这个只适用于完全二叉树
//        for (int i = 1; i < treeNodes.length; i++) {
//            treeNode = treeNodes[i];
//            if (treeNode == null) continue;
//            if (2 * i < treeNodes.length)
//                treeNode.left = treeNodes[2 * i];
//            if (2 * i + 1 < treeNodes.length)
//                treeNode.right = treeNodes[2 * i + 1];
//        }
        for (int i = 1, index = 2; i < treeNodes.length && index < treeNodes.length; i++) {
            treeNode = treeNodes[i];
            if (treeNode == null) continue;
            treeNode.left = treeNodes[index];
            if (index + 1 < treeNodes.length)
                treeNode.right = treeNodes[index + 1];
            index += 2;
        }
        return treeNodes[1];
    }

    //中序遍历
    public static ArrayList<Integer> middleTraverse(TreeNode treeNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (treeNode == null) {
            arrayList.add(null);
        }else if (treeNode.left == null && treeNode.right == null) {
            arrayList.add(treeNode.val);
        } else {
            arrayList.add(treeNode.val);
            arrayList.addAll(middleTraverse(treeNode.left));
            arrayList.addAll(middleTraverse(treeNode.right));
        }
        return arrayList;
    }

    //前序遍历
    public static ArrayList<Integer> beforeTraverse(TreeNode treeNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (treeNode == null) {
            arrayList.add(null);
        }else if (treeNode.left == null && treeNode.right == null) {
            arrayList.add(treeNode.val);
        } else {
            arrayList.addAll(beforeTraverse(treeNode.left));
            arrayList.add(treeNode.val);
            arrayList.addAll(beforeTraverse(treeNode.right));
        }
        return arrayList;
    }

    //后序遍历
    public static ArrayList<Integer> afterTraverse(TreeNode treeNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (treeNode == null) {
            arrayList.add(null);
        }else if (treeNode.left == null && treeNode.right == null) {
            arrayList.add(treeNode.val);
        } else {
            arrayList.addAll(afterTraverse(treeNode.right));
            arrayList.add(treeNode.val);
            arrayList.addAll(afterTraverse(treeNode.left));
        }
        return arrayList;
    }

    //层序遍历
    public static ArrayList<Integer> sequenceTraverse(TreeNode root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        arrayList.add(root.val);
        while (treeNodes.size() > 0) {
            ArrayList<TreeNode> subTreeNodes = new ArrayList<>();
            for (TreeNode value : treeNodes) {
                if (value.left != null || value.right != null) {
                    if (value.left != null) {
                        subTreeNodes.add(value.left);
                        arrayList.add(value.left.val);
                    } else {
                        arrayList.add(null);
                    }
                    if (value.right != null) {
                        subTreeNodes.add(value.right);
                        arrayList.add(value.right.val);
                    } else {
                        arrayList.add(null);
                    }
                }
            }
            treeNodes = subTreeNodes;
        }
        return arrayList;
    }
}
