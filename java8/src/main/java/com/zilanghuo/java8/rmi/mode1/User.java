package com.zilanghuo.java8.rmi.mode1;

import lombok.Data;
import java.io.Serializable;
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -975848310539265120L;

    private Integer age;

    private String name;
}
