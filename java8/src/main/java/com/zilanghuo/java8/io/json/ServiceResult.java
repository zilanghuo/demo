package com.zilanghuo.java8.io.json;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 10:38
 */
@Data
public class ServiceResult implements Serializable {

    private static final long serialVersionUID = -5000911391300900536L;

    private ServiceBean service;

    private Long value;
}
