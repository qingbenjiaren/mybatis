package com.melo.kkb.mybatis.sqlsource;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;

public class RawSqlSource implements SqlSource {

    public RawSqlSource(SqlNode sqlNode){
        // TODO执行阶段再去做该部分
        // TODO 其实此处就是要去解析SqlSource
    }

    @Override
    public BoundSql getBoundSql(Object parameter) {
        return null;
    }
}
