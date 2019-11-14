package com.melo.kkb.mybatis.Executor.iface;

import java.sql.PreparedStatement;
import java.sql.Statement;

public interface ParameterHandler {

    void setParameter(PreparedStatement ps);
}
