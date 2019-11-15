package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.ParameterHandler;
import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.Executor.iface.StatementHandler;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;
import com.melo.kkb.mybatis.sqlsource.BoundSql;
import javafx.scene.Parent;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DefaultStatementHandler implements StatementHandler {
    private ParameterHandler parameterHandler;

    public DefaultStatementHandler(MapperStatement mapperStatement,Object parameter){
        this.parameterHandler = new DefaultParameterHandler(mapperStatement,parameter);
    }
    @Override
    public <T> List<T> executeQuery(MapperStatement mapperStatement,Configuration configuration,ResultHandler resulthandler,BoundSql boundSql) {
        List<T> resultList = new ArrayList<>();
        try {
            System.out.println(boundSql.getSql());
            PreparedStatement ps = parseStatement(mapperStatement, configuration,boundSql);
            //设置参数
            parameterHandler.setParameter(ps);
            //执行查询
            ps.executeQuery();
            resulthandler.parseResult(ps.executeQuery(),resultList,mapperStatement.getResultClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private PreparedStatement parseStatement(MapperStatement mapperStatement, Configuration configuration, BoundSql boundSql) throws SQLException {
        if("prepared".equals(mapperStatement.getStatementType())){
            return configuration.getDataSource().getConnection().prepareStatement(boundSql.getSql());
        }else{
            return configuration.getDataSource().getConnection().prepareCall(boundSql.getSql());
        }
    }
}
