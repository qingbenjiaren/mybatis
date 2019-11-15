package com.melo.kkb.mybatis.executor.iface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理查询结果集
 */
public interface ResultHandler {

    <T>void parseResult(ResultSet rs, List<T> resultList, Class<?> clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;

}
