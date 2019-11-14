package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
public abstract class BaseExecutor implements Executor {
    private Map<String,List<Object>> firstLevelCache = new HashMap<>();

    @Override
    public <T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter) {
        String sql = mapperStatement.getSqlSource().getBoundSql(parameter).getSql();
        List<Object> result = firstLevelCache.get(sql);
        if(result != null){
            return (List<T>) result;
        }
        result = queryFromDatabase(mapperStatement,configuration,parameter);
        firstLevelCache.put(sql,result);
        return (List<T>) result;
    }

    abstract List<Object> queryFromDatabase(MapperStatement mapperStatement, Configuration configuration, Object parameter);
}
