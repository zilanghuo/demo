package com.zilanghuo.test.niuke;

public class Inertest {

    public static void main(String[] args) {
        EnclosingOne eo = new EnclosingOne();
        EnclosingOne.InsideOne InsideOne = eo.new InsideOne();

    }
}


class EnclosingOne{
        public class InsideOne {}

        }