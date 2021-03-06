package com.melo.kkb.mybatis;

import com.melo.kkb.mybatis.sqlSession.SqlSessionFactoryBuilder;
import com.melo.kkb.mybatis.sqlSession.iface.SqlSession;
import com.melo.kkb.mybatis.sqlSession.iface.SqlSessionFactory;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.XmlConfigurationBuilder;
import com.melo.kkb.mybatis.io.Resource;
import com.melo.kkb.mybatis.pojo.User;
import org.junit.Test;

public class JunitTest {

    @Test
    public void testConfiguration(){
        Configuration config = new XmlConfigurationBuilder().parse(Resource.getResourceAsStream("mybatisConfig.xml"));
        String sql = config.getStatement("select.selectUser").getSqlSource().getBoundSql(new User(1,"1231","123213")).getSql();
        System.out.println(sql);
    }
    @Test
    public void testExecutor(){
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(Resource.getResourceAsStream("mybatisConfig.xml"));
        SqlSession session = factory.openSession();
        User user = session.selectOne("select.selectUser",new User(18,"徐长卿","pal3"));
        //List<User> userList = session.selectList("select.selectAll");
        //System.out.println(userList);
        System.out.println(user);
    }
}
