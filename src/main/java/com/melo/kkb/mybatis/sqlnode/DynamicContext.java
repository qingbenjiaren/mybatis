package com.melo.kkb.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

public class DynamicContext {
    private StringBuffer sb = new StringBuffer();
    private Map<String,Object> bindings = new HashMap<>();

    public DynamicContext(Object parameter){
        bindings.put("_parameter",parameter);
    }

    public void appendSql(String sql){
        sb.append(sql);
        sb.append(" ");
    }
    public String getSql(){
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void setBindings(Map<String, Object> bindings) {
        this.bindings = bindings;
    }
}
