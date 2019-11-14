package com.melo.kkb.mybatis.sqlsource;

import com.melo.kkb.mybatis.sqlnode.DynamicContext;
import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;

public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;
    public RawSqlSource(SqlNode sqlNode){
        // TODO执行阶段再去做该部分
        DynamicContext context = new DynamicContext(null);
        sqlNode.apply(context);
        sqlSource = new SqlSourceParser().parse(context.getSql());
    }

    @Override
    public BoundSql getBoundSql(Object parameter) {
        return sqlSource.getBoundSql(parameter);
    }
}
