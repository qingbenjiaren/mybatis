package com.melo.kkb.mybatis.Executor.iface;

import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;
import com.melo.kkb.mybatis.sqlsource.BoundSql;

import java.sql.Statement;
import java.util.List;

/**
 * 出来statement
 */
public interface StatementHandler {

    <T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, ResultHandler resulthandler, BoundSql boundSql);

}
