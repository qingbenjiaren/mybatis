package com.melo.kkb.mybatis.sqlsource;

import com.melo.kkb.mybatis.sqlnode.DynamicContent;
import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;

import java.util.ArrayList;

public class DynamicSqlSource implements SqlSource {
    private SqlNode sqlNode;
    public DynamicSqlSource(SqlNode sqlNode){
        this.sqlNode = sqlNode;

    }
    @Override
    public BoundSql getBoundSql(Object parameter) {
        DynamicContent content = new DynamicContent(null);
        sqlNode.apply(content);
        return new BoundSql(content.getSql(),new ArrayList<>());
    }
}
