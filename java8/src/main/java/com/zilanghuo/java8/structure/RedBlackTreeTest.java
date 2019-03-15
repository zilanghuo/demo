package com.zilanghuo.java8.structure;

/**
 * @author laiwufa
 * @date 2019/3/15 0015 下午 5:00
 */
public class RedBlackTreeTest {

    public static void main(String[] args) {

        RedBlackTree<Integer> redBlackTree = new RedBlackTree();
        RedBlackTree.RBTNode<Integer> root_80 = new RedBlackTree.RBTNode(80, RedBlackTree.BLACK, null);

        RedBlackTree.RBTNode<Integer> node_2_40 = new RedBlackTree.RBTNode(40, RedBlackTree.RED, root_80);
        RedBlackTree.RBTNode<Integer> node_2_120 = new RedBlackTree.RBTNode(120, RedBlackTree.RED, root_80);

        RedBlackTree.RBTNode<Integer> node_3_20 = new RedBlackTree.RBTNode(20, RedBlackTree.BLACK, node_2_40);
        RedBlackTree.RBTNode<Integer> node_3_60 = new RedBlackTree.RBTNode(60, RedBlackTree.BLACK, node_2_40);
        RedBlackTree.RBTNode<Integer> node_3_100 = new RedBlackTree.RBTNode(100, RedBlackTree.BLACK, node_2_120);
        RedBlackTree.RBTNode<Integer> node_3_140 = new RedBlackTree.RBTNode(140, RedBlackTree.BLACK, node_2_120);

        RedBlackTree.RBTNode<Integer> node_4_10 = new RedBlackTree.RBTNode(10, RedBlackTree.RED, node_3_20, null, null);
        RedBlackTree.RBTNode<Integer> node_4_50 = new RedBlackTree.RBTNode(50, RedBlackTree.RED, node_3_60, null, null);
        RedBlackTree.RBTNode<Integer> node_4_90 = new RedBlackTree.RBTNode(90, RedBlackTree.RED, node_3_100, null, null);
        RedBlackTree.RBTNode<Integer> node_4_130 = new RedBlackTree.RBTNode(130, RedBlackTree.RED, node_3_140, null, null);
        RedBlackTree.RBTNode<Integer> node_4_150 = new RedBlackTree.RBTNode(150, RedBlackTree.RED, node_3_140, null, null);

        redBlackTree.setMRoot(root_80);

        root_80.left = node_2_40;
        root_80.right = node_2_120;

        node_2_40.left = node_3_20;
        node_2_40.left = node_3_60;
        node_2_120.left = node_3_100;
        node_2_120.left = node_3_140;

        node_3_20.left = node_4_10;
        node_3_60.left = node_4_50;
        node_3_100.left = node_4_90;
        node_3_140.left = node_4_130;
        node_3_140.right = node_4_150;

        System.out.println(redBlackTree);


    }

}
