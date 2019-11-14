package com.melo.kkb.mybatis.SqlSession;

import com.melo.kkb.mybatis.SqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.SqlSession.iface.SqlSessionFactory;
import com.melo.kkb.mybatis.config.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
