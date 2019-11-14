package com.melo.kkb.mybatis.sqlnode;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;

/**
 * 处理存文本sql
 */
public class StaticTextSqlNode implements SqlNode {
    String sqlText;
    public StaticTextSqlNode(String sqlText){
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        context.appendSql(sqlText);
    }
}
