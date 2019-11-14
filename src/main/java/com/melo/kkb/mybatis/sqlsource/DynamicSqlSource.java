package com.melo.kkb.mybatis.sqlsource;

import com.melo.kkb.mybatis.sqlnode.DynamicContext;
import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;


public class DynamicSqlSource implements SqlSource {
    private SqlNode sqlNode;
    public DynamicSqlSource(SqlNode sqlNode){
        this.sqlNode = sqlNode;

    }
    @Override
    public BoundSql getBoundSql(Object parameter) {
        DynamicContext context = new DynamicContext(parameter);
        // 将SqlNode处理成一条SQL语句
        sqlNode.apply(context);
        //此时sql中不包含${},只包含#{}
        String sql = context.getSql();
        // 通过SqlSourceParser去解析SqlSource中的#{}
        SqlSource sqlSource = new SqlSourceParser().parse(sql);
        return sqlSource.getBoundSql(parameter);
    }
}
