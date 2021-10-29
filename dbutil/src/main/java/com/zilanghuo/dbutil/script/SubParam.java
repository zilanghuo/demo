package com.zilanghuo.dbutil.script;

/**
 * Created by laiwufa on 2021-10-29
 */
public class SubParam {

    private String dateFormat;

    private String dateFunction;

    private String interval;

    private String name;

    private String type;

    private String value;

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFunction() {
        return dateFunction;
    }

    public void setDateFunction(String dateFunction) {
        this.dateFunction = dateFunction;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SubParam(String dateFormat, String dateFunction, String interval, String name, String type, String value) {
        this.dateFormat = dateFormat;
        this.dateFunction = dateFunction;
        this.interval = interval;
        this.name = name;
        this.type = type;
        this.value = value;
    }
}
