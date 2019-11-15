package com.melo.kkb.mybatis.sqlSession.iface;

import com.melo.kkb.mybatis.executor.iface.ResultHandler;

import java.util.List;

public interface SqlSession {
    <T> T selectOne(String statementId,Object param);

    <T> T selectOne(String statementId,Object param, ResultHandler handler);

    <T> List<T> selectList(String statementId, Object param);

    <T> List<T> selectList(String statementId);

    <T> List<T> selectList(String statementId, Object param, ResultHandler handler);
}
