package com.zilanghuo.dbutil;


import java.io.Serializable;

/**
 * @author liubing
 * @date 2020/4/5
 */
public class ColumnDTO implements Serializable {

    private static final long serialVersionUID = -7591519593518789744L;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String ColumnType;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return ColumnType;
    }

    public void setColumnType(String columnType) {
        ColumnType = columnType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColumnDTO{");
        sb.append("columnName='").append(columnName).append('\'');
        sb.append(", ColumnType='").append(ColumnType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
