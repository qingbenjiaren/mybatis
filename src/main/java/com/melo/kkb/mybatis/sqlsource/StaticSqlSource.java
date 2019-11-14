package com.melo.kkb.mybatis.sqlsource;

import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;

import java.util.ArrayList;
import java.util.List;

public class StaticSqlSource implements SqlSource {
    private String sqlText;
    private List<ParameterMapping> parameterMappings;
    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sqlText = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object parameter) {
        return new BoundSql(sqlText,parameterMappings);
    }
}
