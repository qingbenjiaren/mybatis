package com.melo.kkb.mybatis.executor.iface;

import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.List;

public interface Executor {

    /**
     *
     * @param mapperStatement 获取SQL语句和入参出参类型信息
     * @param configuration	获取数据源连接处信息
     * @param parameter	获取入参类型
     * @return
     */
    <T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter,ResultHandler handler);
}
