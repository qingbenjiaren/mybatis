package com.melo.kkb.mybatis.SqlSession.iface;

import java.util.List;

public interface SqlSession {
    public <T> T selectOne(String statementId,Object param);

    public <T> List<T> selectList(String statementId, Object param);
}
