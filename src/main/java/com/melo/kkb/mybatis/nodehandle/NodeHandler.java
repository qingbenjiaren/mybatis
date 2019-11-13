package com.melo.kkb.mybatis.nodehandle;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

public interface NodeHandler {

    void handleNode (Element node2Handle, List<SqlNode> contents);
}
