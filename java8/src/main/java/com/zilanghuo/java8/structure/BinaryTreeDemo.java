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
        BinaryTree tree = new BinaryTree(1, null, null);
        BinaryTreeDemo demo = new BinaryTreeDemo();
        demo.insertLeftNode(tree, 4);
        demo.insertLeftNode(tree, 2);
        BinaryTree rightNode = demo.insertRightNode(tree, 3);
        demo.insertRightNode(rightNode, 6);
        demo.insertLeftNode(rightNode, 5);
        System.out.println(JSONUtil.toJsonStr(tree));
        System.out.println("---前序---");
        demo.preOrder(tree);
        System.out.println("---中序---");
        demo.midOrder(tree);
        System.out.println("---后续---");
        demo.behindOrder(tree);
        System.out.println("---广度遍历---");
        demo.bfsOrder(tree);
        System.out.println("---深度算法---");
        System.out.println("深度：" + demo.getHeight(tree));
        System.out.println("--------------------下面是二叉搜索树-----------------------");
        BinaryTreeDemo binarySearchDemo = new BinaryTreeDemo();
        BinaryTree root = new BinaryTree(50, null, null);
        binarySearchDemo.insertNodeForSearch(root, 76);
        binarySearchDemo.insertNodeForSearch(root, 21);
        binarySearchDemo.insertNodeForSearch(root, 4);
        binarySearchDemo.insertNodeForSearch(root, 32);
        binarySearchDemo.insertNodeForSearch(root, 100);
        binarySearchDemo.insertNodeForSearch(root, 64);
        binarySearchDemo.insertNodeForSearch(root, 52);
        System.out.println("-----------前序遍历----------------");
        binarySearchDemo.preOrder(root);
        System.out.println("-----------二叉搜索树查找-----------------------");
        binarySearchDemo.searchNodeForTree(root, 64);
        System.out.println("---------------只存在左节点的情况-------------------------------");
        BinaryTree demo3 = new BinaryTree(50, null, null);
        binarySearchDemo.insertNodeForSearch(demo3, 45);
        binarySearchDemo.insertNodeForSearch(demo3, 40);
        binarySearchDemo.insertNodeForSearch(demo3, 35);
        binarySearchDemo.insertNodeForSearch(demo3, 30);
        binarySearchDemo.insertNodeForSearch(demo3, 25);
        binarySearchDemo.insertNodeForSearch(demo3, 20);
        binarySearchDemo.insertNodeForSearch(demo3, 15);
        binarySearchDemo.searchNodeForTree(demo3, 15);
    }

    /**
     * 插入left节点
     *
     * @param binaryTree
     * @param value
     */
    public BinaryTree insertLeftNode(BinaryTree binaryTree, Integer value) {
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
    public BinaryTree insertRightNode(BinaryTree binaryTree, Integer value) {
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

    /**
     * 二叉搜索树的插入
     *
     * @param binaryTree
     * @param value
     */
    public void insertNodeForSearch(BinaryTree binaryTree, Integer value) {
        //判断节点大小，如果大于当前节点，插入右节点，如果小于，插入左节点
        if (binaryTree.getData() > value) {
            //插入左节点
            if (binaryTree.getLeft() == null) {
                BinaryTree newNode = new BinaryTree(value, null, null);
                binaryTree.setLeft(newNode);
                return;
            }
            // 重复1判断
            insertNodeForSearch(binaryTree.getLeft(), value);
        } else {
            if (binaryTree.getRight() == null) {
                BinaryTree newNode = new BinaryTree(value, null, null);
                binaryTree.setRight(newNode);
                return;
            }
            // 重复1步骤
            insertNodeForSearch(binaryTree.getRight(), value);
        }
    }

    /**
     * 二叉搜索树查找
     *
     * @param binaryTree
     * @param value
     * @return
     */
    public Boolean searchNodeForTree(BinaryTree binaryTree, Integer value) {
        if (binaryTree == null) {
            System.out.println("未找到该元素：" + value);
            return false;
        }
        System.out.println("寻找中");
        if (binaryTree.getData() == value) {
            System.out.println("该元素存在:" + value);
            return true;
        } else if (binaryTree.getData() > value) {
            searchNodeForTree(binaryTree.getLeft(), value);
        } else {
            searchNodeForTree(binaryTree.getRight(), value);
        }
        return false;
    }

    /**
     * 二叉搜索树删除情况
     * 1、删除叶子节点：寻找到要删除的节点，判断左右节点，将父节点的左右设置为空即可
     * 2、删除只有一个叶子节点的值：判断该删除的节点为父节点的左右节点，将父节点的左右节点只想被删节点的左右节点即可
     * 3、删除的节点拥有左右节点：涉及到左旋和右旋
     *
     * @param binaryTree
     * @param value
     * @return
     */
    public void deleteNoForSearch(BinaryTree binaryTree, Integer value, BinaryTree parentNode) {
        // 判断相等
        if (binaryTree.getData() == value) {
            // 判断是否存在左右节点
            // 无左右节点,不考虑只有一个节点的情况
            if (binaryTree.getRight() == null && binaryTree.getLeft() == null) {
                BinaryTree leftNode = parentNode.getLeft();
                if (leftNode != null && leftNode.getData() == value) {
                    parentNode.setLeft(null);
                } else {
                    parentNode.setRight(null);
                }
            }else if (binaryTree.getRight() != null){ // 存在右节点

            }


        }


    }


}

class BinaryTree {

    private Integer data;

    private BinaryTree left;

    private BinaryTree right;


    public BinaryTree(Integer data, BinaryTree left, BinaryTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTree() {
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
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