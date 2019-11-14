package com.melo.kkb.mybatis.Executor.iface;

import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    <T> List<T> executeQuery(Statement stmt, ResultHandler resulthandler);

}
