package com.melo.kkb.mybatis;

import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.XmlConfigurationBuilder;
import com.melo.kkb.mybatis.io.Resource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JunitTest {

    @Test
    public void testConfiguration() throws SQLException {
        Configuration config = new XmlConfigurationBuilder().parse(Resource.getResourceAsStream("mybatisConfig.xml"));
        DataSource dataSource = config.getDataSource();
        Connection con = dataSource.getConnection();
        ResultSet rs = con.prepareStatement("select * from t_users order by website").executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
        //String sql = config.getStatement("selectselectUser").getSqlSource().getBoundSql(null).getSql();
        //System.out.println(sql);

    }
}
