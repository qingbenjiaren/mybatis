package com.melo.kkb.mybatis.SqlSession.iface;

import com.melo.kkb.mybatis.Executor.iface.ResultHandler;

import java.util.List;

public interface SqlSession {
    <T> T selectOne(String statementId,Object param);

    <T> List<T> selectList(String statementId, Object param);

    <T> List<T> selectList(String statementId);

    <T> List<T> selectList(String statementId, Object param, ResultHandler handler);
}
