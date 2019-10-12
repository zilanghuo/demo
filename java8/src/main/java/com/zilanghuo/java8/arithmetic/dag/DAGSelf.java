package com.zilanghuo.java8.arithmetic.dag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by laiwufa on 2019-10-12
 */
public class DAGSelf {


    public static List<Edge> edgeList = new ArrayList();

    public static List<Node> nodeList = new ArrayList();

    // 邻接表中表对应的链表的顶点：elementNode
    @Data
    @AllArgsConstructor
    class ENode {
        int ivex;       // 该边所指向的顶点的位置
        ENode nextEdge; // 指向下一条弧的指针
    }

    /**
     * 节点属性
     */
    @Data
    class Node {

        private String ele; //元素

        private ENode firstNode; //第一条只想的边

        public Node(String ele){
            this.ele = ele;
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Edge {
        private Node sourceNode;

        private Node targetNode;
    }

    /**
     * 初始化
     */
    public void init() {
        Node zero = new Node("0");
        Node one = new Node("1");
        Node two = new Node("2");
        Node three = new Node("3");
        Node four = new Node("4");
        Node five = new Node("5");
        Node six = new Node("6");
        Node seven = new Node("7");
        Edge e1 = new Edge(zero, three);
        Edge e2 = new Edge(zero, four);
        Edge e3 = new Edge(one, three);
        Edge e4 = new Edge(three, five);
        Edge e5 = new Edge(six, seven);
        nodeList.add(zero);
        nodeList.add(one);
        nodeList.add(two);
        nodeList.add(three);
        nodeList.add(four);
        nodeList.add(five);
        nodeList.add(six);
        nodeList.add(seven);

        edgeList.add(e1);
        edgeList.add(e2);
        edgeList.add(e3);
        edgeList.add(e4);
        edgeList.add(e5);

    }

    public  void addNext() {
        edgeList.forEach(edge -> {
            int sourceIndex = getPosition(edge.getSourceNode().getEle());
            int targetIndex = getPosition(edge.getTargetNode().getEle());
            Node source = nodeList.get(sourceIndex);
            if (source.getFirstNode() == null){
                source.setFirstNode(new ENode(targetIndex,null));
            }else{
                ENode firstNode = source.getFirstNode();
                linkLast(firstNode,targetIndex);
            }
        });
    }

    public void linkLast(ENode curr,int sourceIndex){
        ENode p = curr;
        while (p.nextEdge != null){
            p = p.nextEdge;
        }
        p.nextEdge = new ENode(sourceIndex,null);

    }

    /*
     * 返回ch位置
     */
    private int getPosition(String ch) {
        for(int i=0; i<nodeList.size(); i++) {
            if(nodeList.get(i).ele == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     */
    public void sort(){
        // 入度为0 的节点
        Queue<Integer> queue = new ArrayDeque();
        // 目标的数据节点
        List<Node> top = new ArrayList<>();

        //入度数
        int[] rudu = new int[nodeList.size()];
        //统计下入度数
        nodeList.forEach(node -> {
            ENode firstNode = node.getFirstNode();
            while (firstNode != null){
                rudu[firstNode.getIvex()]++;
                firstNode = firstNode.nextEdge;
            }
        });


        //入度为0的点
        for (int i = 0; i < rudu.length; i++) {
            if (rudu[i] == 0){
                queue.add(i);
            }

        }

        // 将对于的next点的入度减少1
        while (queue.size() > 0){
            Integer poll = queue.poll();

            Node e = nodeList.get(poll);
            top.add(e);
            //减去对应的入度
            ENode firstNode = e.getFirstNode();
            while (firstNode != null){
                rudu[firstNode.getIvex()]--;
                if (rudu[firstNode.getIvex()] == 0 ){
                    queue.add(firstNode.getIvex());
                }
                firstNode = firstNode.nextEdge;
            }

        }
        System.out.println("-----------------");
        top.forEach(node -> {
            System.out.print(node.getEle()+"-->");
        });

    }

    public void print(){
        nodeList.forEach(node -> {
            System.out.print(""+node.getEle()+"--");
            ENode firstNode = node.getFirstNode();

            while (firstNode != null){
                System.out.print(firstNode.ivex+"-->");
                firstNode = firstNode.nextEdge;
            }
            System.out.println("_");
        });
    }


    public static void main(String[] args) {
        DAGSelf self = new DAGSelf();
        self.init();
        self.addNext();
        self.print();
        self.sort();
    }
}
