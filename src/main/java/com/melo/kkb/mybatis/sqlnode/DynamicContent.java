package com.melo.kkb.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

public class DynamicContent {
    private StringBuffer sb = new StringBuffer();
    private Map<String,Object> parameterMap = new HashMap<>();

    public DynamicContent(Object parameter){
        parameterMap.put("_parameter",parameter);
    }

    public void appendSql(String sql){
        sb.append(sql);
        sb.append(" ");
    }
    public String getSql(){
        return sb.toString();
    }

}
