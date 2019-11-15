package com.melo.kkb.mybatis.sqlSession;

import com.melo.kkb.mybatis.executor.CacheExecutor;
import com.melo.kkb.mybatis.executor.SimpleExecutor;
import com.melo.kkb.mybatis.sqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.sqlSession.iface.SqlSessionFactory;
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
