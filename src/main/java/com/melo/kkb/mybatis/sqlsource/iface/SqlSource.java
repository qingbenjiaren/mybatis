package com.melo.kkb.mybatis.sqlsource.iface;

import com.melo.kkb.mybatis.sqlsource.BoundSql;

public interface SqlSource {

    /**
     * 根据入参对象，获取JDBC可以执行的SQL语句
     * 执行阶段才会调用该方法
     */
    BoundSql getBoundSql(Object parameter);
}
