package com.zilanghuo.java8.optional;

import java.util.Optional;

/**
 * @author laiwufa
 * @date 2019/6/23 15:58
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }
}
