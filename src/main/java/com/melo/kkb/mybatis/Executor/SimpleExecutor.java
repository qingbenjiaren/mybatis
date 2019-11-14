package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.BaseExecutor;
import com.melo.kkb.mybatis.Executor.iface.ParameterHandler;
import com.melo.kkb.mybatis.Executor.iface.StatementHandler;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;
import com.melo.kkb.mybatis.sqlsource.BoundSql;

import java.sql.Statement;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {


    @Override
    List<Object> queryFromDatabase(MapperStatement mapperStatement, Configuration configuration, Object parameter) {
        BoundSql boundSql = mapperStatement.getSqlSource().getBoundSql(parameter);
        StatementHandler statementHandler = new DefaultStatementHandler(new DefaultParameterHandler(mapperStatement,parameter));
        return statementHandler.executeQuery(mapperStatement,configuration,new DefaultResultHandler(),boundSql);
    }
}
