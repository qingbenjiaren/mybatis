package com.melo.kkb.mybatis.sqlnode.iface;

import com.melo.kkb.mybatis.sqlnode.DynamicContent;

public interface SqlNode {

    void apply(DynamicContent content);
}
