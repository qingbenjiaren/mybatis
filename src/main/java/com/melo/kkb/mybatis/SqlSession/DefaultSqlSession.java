package com.melo.kkb.mybatis.SqlSession;

import com.melo.kkb.mybatis.Executor.CacheExecutor;
import com.melo.kkb.mybatis.Executor.DefaultResultHandler;
import com.melo.kkb.mybatis.Executor.SimpleExecutor;
import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.SqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.config.Configuration;

import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration,Executor executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<T> list = selectList(statementId,param);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        return selectList(statementId,param,new DefaultResultHandler());
    }


    @Override
    public <T> List<T> selectList(String statementId) {
        return selectList(statementId,null);
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param, ResultHandler handler) {
        return executor.executeQuery(configuration.getStatement(statementId),configuration,param,handler);
    }
}
