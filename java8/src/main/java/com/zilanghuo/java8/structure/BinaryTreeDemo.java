package com.zilanghuo.java8.structure;

import cn.hutool.json.JSONUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author laiwufa
 * @date 2019/5/5 22:33
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("1", null, null);
        BinaryTreeDemo demo = new BinaryTreeDemo();
        demo.insertLeftNode(tree, "4");
        demo.insertLeftNode(tree, "2");
        BinaryTree rightNode = demo.insertRightNode(tree, "3");
        demo.insertRightNode(rightNode, "6");
        demo.insertLeftNode(rightNode, "5");
        System.out.println(JSONUtil.toJsonStr(tree));
        demo.preOrder(tree);
        System.out.println("------");
        demo.midOrder(tree);
        System.out.println("------");
        demo.behindOrder(tree);
        System.out.println("------");
        demo.bfsOrder(tree);
        System.out.println("------");
        System.out.println("深度：" + demo.getHeight(tree));
    }

    /**
     * 插入left节点
     *
     * @param binaryTree
     * @param value
     */
    public BinaryTree insertLeftNode(BinaryTree binaryTree, String value) {
        if (null != binaryTree) {
            if (binaryTree.getLeft() == null) {
                BinaryTree node = new BinaryTree(value, null, null);
                binaryTree.setLeft(node);
                return node;
            } else {
                BinaryTree left = binaryTree.getLeft();
                BinaryTree node = new BinaryTree(value, left, null);
                binaryTree.setLeft(node);
                return node;
            }
        }
        return new BinaryTree(value, null, null);

    }

    /**
     * 插入右节点
     *
     * @param binaryTree
     * @param value
     */
    public BinaryTree insertRightNode(BinaryTree binaryTree, String value) {
        if (null != binaryTree) {
            if (binaryTree.getRight() == null) {
                BinaryTree node = new BinaryTree(value, null, null);
                binaryTree.setRight(node);
                return node;
            } else {
                BinaryTree right = binaryTree.getRight();
                BinaryTree node = new BinaryTree(value, null, right);
                binaryTree.setRight(node);
                return node;
            }
        }
        return new BinaryTree(value, null, null);
    }

    /**
     * 前序遍历，中--左--右
     *
     * @param binaryTree
     */
    public void preOrder(BinaryTree binaryTree) {
        if (null != binaryTree) {
            System.out.println(binaryTree.getData());
            preOrder(binaryTree.getLeft());
            preOrder(binaryTree.getRight());
        }
    }

    /**
     * 中序遍历：左--中--右
     *
     * @param binaryTree
     */
    public void midOrder(BinaryTree binaryTree) {
        if (null != binaryTree) {
            midOrder(binaryTree.getLeft());
            System.out.println(binaryTree.getData());
            midOrder(binaryTree.getRight());
        }
    }

    /**
     * 中序遍历：左--中--右
     *
     * @param binaryTree
     */
    public void behindOrder(BinaryTree binaryTree) {
        if (null != binaryTree) {
            behindOrder(binaryTree.getLeft());
            behindOrder(binaryTree.getRight());
            System.out.println(binaryTree.getData());
        }
    }

    /**
     * 广度遍历，先入先出
     *
     * @param binaryTree
     */
    public void bfsOrder(BinaryTree binaryTree) {
        Queue<BinaryTree> queue = new ArrayDeque();
        queue.add(binaryTree);
        while (!queue.isEmpty()) {
            BinaryTree poll = queue.poll();
            System.out.print(poll.getData() + "-");
            if (null != poll.getLeft()) {
                queue.add(poll.getLeft());
            }
            if (null != poll.getRight()) {
                queue.add(poll.getRight());
            }
        }
    }

    /**
     * 获取树的深度
     *
     * @param binaryTree
     * @return
     */
    public int getHeight(BinaryTree binaryTree) {
        if (null == binaryTree) {
            return 0;
        }
        int leftHeight = getHeight(binaryTree.getLeft());
        int rightHeight = getHeight(binaryTree.getRight());
        int max = leftHeight;
        if (max < rightHeight) {
            max = rightHeight;
        }
        return max + 1;
    }

}

class BinaryTree {

    private String data;

    private BinaryTree left;

    private BinaryTree right;


    public BinaryTree(String data, BinaryTree left, BinaryTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

}