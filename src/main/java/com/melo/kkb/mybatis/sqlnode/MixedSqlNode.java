package com.melo.kkb.mybatis.sqlnode;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;

import java.util.ArrayList;
import java.util.List;

public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodeList = new ArrayList<>();
    public MixedSqlNode(List<SqlNode> sqlNodeList){
        this.sqlNodeList = sqlNodeList;
    }
    @Override
    public void apply(DynamicContext context) {
        for(SqlNode sqlNode : sqlNodeList){
            sqlNode.apply(context);
        }
    }
}
