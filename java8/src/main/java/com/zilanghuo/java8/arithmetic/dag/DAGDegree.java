package com.zilanghuo.java8.arithmetic.dag;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    }

    private static void init(List<Integer> vexList, List<Edge> edgeList, Map<Integer, Integer> degreeMap) {
        vexList.add(1);
        vexList.add(2);
        vexList.add(3);
        vexList.add(4);
        vexList.add(5);
        // edge
        Edge oneToTwo = new Edge(1,2);
        Edge oneToThree = new Edge(1,3);
        Edge twoToFour = new Edge(2,4);
        Edge threeToFive = new Edge(3,5);
        Edge threeToFour = new Edge(3,4);
        Edge fiveToFour = new Edge(5,4);
        edgeList.add(oneToTwo);
        edgeList.add(oneToThree);
        edgeList.add(twoToFour);
        edgeList.add(threeToFive);
        edgeList.add(threeToFour);
        edgeList.add(fiveToFour);
        // 入度
        degreeMap.put(1,0);
        degreeMap.put(2,1);
        degreeMap.put(3,1);
        degreeMap.put(5,1);
        degreeMap.put(4,3);

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
