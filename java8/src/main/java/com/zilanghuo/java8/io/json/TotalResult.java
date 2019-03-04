package com.zilanghuo.java8.io.json;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author laiwufa
 * @date 2019/3/4 0004 上午 10:39
 */
@Data
public class TotalResult implements Serializable {

    private static final long serialVersionUID = 345689637100345171L;

    private List<ServiceResult> getTopNSlowService;

}
