package com.melo.kkb.mybatis.sqlsource;
/**
 * 解析参数信息，主要包含参数名称和类型
 */
public class ParameterMapping {
    private String name;
    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
