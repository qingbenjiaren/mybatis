package com.melo.kkb.mybatis.executor.iface;

import java.sql.PreparedStatement;

/**
 * 处理参数
 */
public interface ParameterHandler {

    void setParameter(PreparedStatement ps);
}
