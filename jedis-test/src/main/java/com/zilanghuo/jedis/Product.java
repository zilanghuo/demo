package com.zilanghuo.jedis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author laiwufa
 * @date 2019/5/16 0016 下午 4:49
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1191098225066412531L;
    private int id;

    private String name;

    private String descript;

    private Integer amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
