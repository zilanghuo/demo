package com.zilanghuo.java8.io.json;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 10:24
 */
@Data
public class DataResult implements Serializable {

    private static final long serialVersionUID = -3273974193806726050L;

    private TotalResult data;

}
