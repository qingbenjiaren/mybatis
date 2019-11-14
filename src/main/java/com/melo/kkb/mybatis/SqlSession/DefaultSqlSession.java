package com.melo.kkb.mybatis.SqlSession;

import com.melo.kkb.mybatis.Executor.iface.Executor;
import com.melo.kkb.mybatis.SqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.config.Configuration;

import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
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
        return null;
    }
}
