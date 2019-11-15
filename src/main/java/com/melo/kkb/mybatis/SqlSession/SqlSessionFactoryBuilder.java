package com.melo.kkb.mybatis.SqlSession;

import com.melo.kkb.mybatis.SqlSession.iface.SqlSessionFactory;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.XmlConfigurationBuilder;

import java.io.InputStream;
import java.io.Reader;
/**
 * 使用构建者模式去创建SqlSessionFactory
 *
 */
public class SqlSessionFactoryBuilder {
    private Configuration configuration;
    public SqlSessionFactory build(InputStream is){
        XmlConfigurationBuilder configBuilder = new XmlConfigurationBuilder();
        // 执行解析流程
        configuration = configBuilder.parse(is);
        return build();
    }
    public SqlSessionFactory build(Reader reader){
        return null;
    }
    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
