package com.melo.kkb.mybatis.Executor.iface;

import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.List;

public interface Executor {

    <T> List<T> executeQuery(MapperStatement mapperStatement, Configuration configuration, Object parameter);
}
