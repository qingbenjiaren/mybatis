package com.melo.kkb.mybatis.sqlnode.iface;

import com.melo.kkb.mybatis.sqlnode.DynamicContext;

public interface SqlNode {

    void apply(DynamicContext content);
}
