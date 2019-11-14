package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.Executor.iface.StatementHandler;

import java.sql.Statement;
import java.util.List;

public class BaseStatementHandler implements StatementHandler {
    @Override
    public <T> List<T> executeQuery(Statement stmt, ResultHandler resulthandler) {
        return null;
    }
}
