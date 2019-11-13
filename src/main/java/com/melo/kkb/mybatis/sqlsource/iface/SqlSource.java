package com.melo.kkb.mybatis.sqlsource.iface;

import com.melo.kkb.mybatis.sqlsource.BoundSql;

public interface SqlSource {

    BoundSql getBoundSql(Object parameter);
}
