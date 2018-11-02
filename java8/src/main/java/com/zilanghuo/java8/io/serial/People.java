package com.zilanghuo.java8.io.serial;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laiwufa
 * @date 2018/11/2
 * use:
 */
@Data
public class People implements Serializable {

    private static final long serialVersionUID = 6335791900062656452L;

    // 该字段不参加序列化
    private transient String name;

    private Integer age;

    @Override
    public String toString() {
        return "name:" + this.name + ",age:" + this.age;
    }
}
