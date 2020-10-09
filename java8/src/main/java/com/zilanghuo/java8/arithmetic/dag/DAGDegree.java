package com.zilanghuo.java8.arithmetic.dag;


import java.util.*;

/**
 * Created by laiwufa on 2020-09-14
 */
public class DAGDegree {

    public static void main(String[] args) {

        List<Integer> vexList = new ArrayList<>();
        vexList.add(1);
        vexList.add(2);
        vexList.add(3);
        vexList.add(4);
        vexList.add(5);
        Map<Integer,Integer> degreeMap = new HashMap<>();
        List<Edge> edgeList = new ArrayList<>();
        init(vexList,edgeList,degreeMap);
        Queue<Integer> indegree = new LinkedList<>();
        degreeMap.entrySet().stream().forEach(integerIntegerEntry -> {
            if (integerIntegerEntry.getValue() == 0){
                indegree.add(integerIntegerEntry.getKey());
            }
        });

        while (!indegree.isEmpty()){
            Integer poll = indegree.poll();
            degreeMap.remove(poll);
            System.out.println(poll+"--");
            // 获取到poll 为入度的数据
            List<Edge> pollFrom = new ArrayList<>();
            edgeList.stream().forEach(edge -> {
                if (edge.getFromId().equals(poll)){
                    pollFrom.add(edge);
                }
            });
            // 判断入度
            pollFrom.stream().forEach(edge -> {
                if ((degreeMap.get(edge.getToId()) - 1) == 0){
                    indegree.add(edge.getToId());
                }else {
                    degreeMap.put(edge.getToId(),degreeMap.get(edge.getToId()) - 1);
                }
            });
        }
    }

    private static void init(List<Integer> vexList, List<Edge> edgeList, Map<Integer, Integer> degreeMap) {
        vexList.add(1);
        vexList.add(2);
        vexList.add(3);
        vexList.add(4);
        vexList.add(5);
        vexList.add(6);
        // edge
        Edge oneToTwo = new Edge(1,2);
        Edge oneToThree = new Edge(1,3);
        Edge twoToFour = new Edge(2,4);
        Edge threeToFive = new Edge(3,5);
        Edge threeToFour = new Edge(3,4);
        Edge fiveToFour = new Edge(5,4);
        Edge fiveToSix = new Edge(5,6);
        Edge forToSix = new Edge(4,6);
        edgeList.add(oneToTwo);
        edgeList.add(oneToThree);
        edgeList.add(twoToFour);
        edgeList.add(threeToFive);
        edgeList.add(threeToFour);
        edgeList.add(fiveToFour);
        edgeList.add(fiveToSix);
        edgeList.add(forToSix);

        // 入度
        degreeMap.put(1,0);
        degreeMap.put(2,1);
        degreeMap.put(3,1);
        degreeMap.put(5,1);
        degreeMap.put(4,3);
        degreeMap.put(6,2);

    }

    static class Edge{
        private Integer fromId;
        private Integer toId;

        public Edge(Integer fromId,Integer toId){
            this.fromId = fromId;
            this.toId = toId;
        }

        public Integer getFromId() {
            return fromId;
        }

        public void setFromId(Integer fromId) {
            this.fromId = fromId;
        }

        public Integer getToId() {
            return toId;
        }

        public void setToId(Integer toId) {
            this.toId = toId;
        }
    }
}
