package com.melo.kkb.mybatis.sqlnode;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;

/**
 * 处理带有参数的sql
 */
public class TextSqlNode implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText){
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContent content) {

    }
}
