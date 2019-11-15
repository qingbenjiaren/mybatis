package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.Executor.iface.StatementHandler;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;
import com.melo.kkb.mybatis.sqlsource.BoundSql;
import java.util.List;

/**
 * 执行JDBC代码
 */
public class SimpleExecutor extends BaseExecutor {
    @Override
    <T>List<T> queryFromDatabase(MapperStatement mapperStatement, Configuration configuration, Object parameter, ResultHandler resultHandler) {
        //通过mapperStatement获取BoundSql
        BoundSql boundSql = mapperStatement.getSqlSource().getBoundSql(parameter);
        //创建statementHandler
        StatementHandler statementHandler = new DefaultStatementHandler(mapperStatement,parameter);
        return statementHandler.executeQuery(mapperStatement,configuration,resultHandler,boundSql);
    }
}
