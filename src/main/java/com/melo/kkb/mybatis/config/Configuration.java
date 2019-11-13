package com.melo.kkb.mybatis.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装了全局配置文件和映射文件中的信息
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String,MapperStatement> statementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void addStatement(String statementId,MapperStatement statement){
        statementMap.put(statementId,statement);
    }
    public MapperStatement getStatement(String key){
        return statementMap.get(key);
    }
}
