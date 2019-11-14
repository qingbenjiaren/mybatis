package com.melo.kkb.mybatis;

import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.XmlConfigurationBuilder;
import com.melo.kkb.mybatis.io.Resource;
import com.melo.kkb.mybatis.pojo.User;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JunitTest {

    @Test
    public void testConfiguration(){
        Configuration config = new XmlConfigurationBuilder().parse(Resource.getResourceAsStream("mybatisConfig.xml"));
        String sql = config.getStatement("select.selectUser").getSqlSource().getBoundSql(new User("123","1231","123213")).getSql();
        System.out.println(sql);
    }
    public void testExcutor(){

    }
}
