package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
public class CacheExecutor implements Executor {
    private Map<String,List<Object>> secondLevelCache = new HashMap<>();
    private Executor delegate;
    public void delegate(Executor delegate){
        this.delegate = delegate;
    }
    @Override
    public<T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter, ResultHandler handler) {
        List<T> resultList;
        String sql = mapperStatement.getSqlSource().getBoundSql(parameter).getSql();
        //先从缓存取
        //TODO，这里key值不能唯一标识一次查询，需要联合sql+param+分页信息等其他生成唯一key值
        if(secondLevelCache.get(sql) != null){
            resultList = (List<T>) secondLevelCache.get(sql);
        }else {
            resultList = delegate.executeQuery(mapperStatement, configuration, parameter,handler);
        }
        secondLevelCache.put(sql, (List<Object>) resultList);
        return resultList;
    }
}
