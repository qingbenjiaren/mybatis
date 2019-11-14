package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheExecutor implements Executor {
    private Map<String,List<Object>> secondLevelCache = new HashMap<>();
    private Executor delegate;
    public CacheExecutor(Executor delegate){
        this.delegate = delegate;
    }
    @Override
    public <T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter) {
        String sql = mapperStatement.getSqlSource().getBoundSql(parameter).getSql();
        if(secondLevelCache.get(sql) != null){
            return (List<T>) secondLevelCache.get(sql);
        }
        return delegate.executeQuery(mapperStatement, configuration, parameter);
    }
}
