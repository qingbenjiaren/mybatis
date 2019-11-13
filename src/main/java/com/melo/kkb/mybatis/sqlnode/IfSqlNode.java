package com.melo.kkb.mybatis.sqlnode;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;

public class IfSqlNode implements SqlNode {
    private String test;
    private SqlNode rootSqlNode;
    public IfSqlNode(String test,SqlNode rootSqlNode){
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }
    @Override
    public void apply(DynamicContent content) {

    }
}
