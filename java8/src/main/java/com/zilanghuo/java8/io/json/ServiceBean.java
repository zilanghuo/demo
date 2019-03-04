package com.zilanghuo.java8.io.json;

import lombok.Data;

import java.io.Serializable;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 10:38
 */
@Data
public class ServiceBean implements Serializable {

    private static final long serialVersionUID = -4355458906767416434L;

    private String key;

    private String label;

    private String applicationId;

    private String applicationName;
}