package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
public abstract class BaseExecutor implements Executor {
    private Map<String,List<Object>> firstLevelCache = new HashMap<>();

    @Override
    public<T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter,ResultHandler handler) {
        String sql = mapperStatement.getSqlSource().getBoundSql(parameter).getSql();
        //TODO，这里key值不能唯一标识一次查询，需要联合sql+param+分页信息等其他生成唯一key值
        List<T> result = (List<T>) firstLevelCache.get(sql);
        if(result != null){
            return  result;
        }
        //查询数据
        result = queryFromDatabase(mapperStatement,configuration,parameter,handler);
        firstLevelCache.put(sql, (List<Object>) result);
        return  result;
    }

    abstract<T> List<T> queryFromDatabase(MapperStatement mapperStatement, Configuration configuration, Object parameter, ResultHandler resultHandler);
}
