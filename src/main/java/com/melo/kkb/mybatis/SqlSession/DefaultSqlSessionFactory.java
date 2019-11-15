package com.melo.kkb.mybatis.SqlSession;

import com.melo.kkb.mybatis.Executor.CacheExecutor;
import com.melo.kkb.mybatis.Executor.SimpleExecutor;
import com.melo.kkb.mybatis.SqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.SqlSession.iface.SqlSessionFactory;
import com.melo.kkb.mybatis.config.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;
    private CacheExecutor executor;
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
        executor = new CacheExecutor();
    }
    @Override
    public SqlSession openSession() {
        //委托执行器
        executor.delegate(new SimpleExecutor());
        return new DefaultSqlSession(configuration,executor);
    }
}
