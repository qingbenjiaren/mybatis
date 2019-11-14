package com.melo.kkb.mybatis.Executor.iface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultHandler {

    <T>void parseResult(ResultSet rs, List<T> resultList, Class<?> clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;

}
