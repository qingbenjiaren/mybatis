package com.melo.kkb.mybatis.config;

import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;

/**
 * 用来封装映射文件中的CRUD标签，比如select标签
 */
public class MapperStatement {
    private Class<?> resultClass;
    private Class<?> parameterClass;
    private String statementId;
    private String statementType;
    private SqlSource sqlSource;
    private MapperStatement(){}
    private static MapperStatement build(Builder builder){
        MapperStatement mapper = new MapperStatement();
        mapper.resultClass = builder.resultClass;
        mapper.parameterClass = builder.parameterClass;
        mapper.statementId = builder.statementId;
        mapper.statementType = builder.statementType;
        mapper.sqlSource = builder.sqlSource;
        return mapper;
    }
    public static class Builder{
        private String statementId;
        private SqlSource sqlSource;
        //有默认值
        private Class<?> resultClass = null;
        private Class<?> parameterClass = null;
        private String statementType = "prepared";

        public Builder(String statementId,SqlSource sqlSource){
            this.statementId = statementId;
            this.sqlSource = sqlSource;
        }
        public Builder resultClass(Class<?> clazz){
            this.resultClass = clazz;
            return this;
        }
        public Builder parameterClass(Class<?> clazz){
            this.parameterClass = clazz;
            return this;
        }
        public Builder statementType(String val){
            if(val != null && !"".equals(val)) {
                this.statementType = val;
            }
            return this;
        }
        public MapperStatement build(){
            return MapperStatement.build(this);
        }
    }

    public Class<?> getResultClass() {
        return resultClass;
    }

    public Class<?> getParameterClass() {
        return parameterClass;
    }

    public String getStatementId() {
        return statementId;
    }

    public String getStatementType() {
        return statementType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }
}
