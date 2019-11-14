package com.melo.kkb.mybatis.config;

import com.melo.kkb.mybatis.nodehandle.NodeHandler;
import com.melo.kkb.mybatis.sqlnode.IfSqlNode;
import com.melo.kkb.mybatis.sqlnode.MixedSqlNode;
import com.melo.kkb.mybatis.sqlnode.StaticTextSqlNode;
import com.melo.kkb.mybatis.sqlnode.TextSqlNode;
import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.sqlsource.DynamicSqlSource;
import com.melo.kkb.mybatis.sqlsource.RawSqlSource;
import com.melo.kkb.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlStatementBuilder {
    private Configuration configuration;
    private Map<String,NodeHandler> nodeHandlerMap = new HashMap<>();

    boolean isDynamic = false;
    XmlStatementBuilder(Configuration configuration){
        this.configuration = configuration;
        initNodeHandler();
    }
    private void initNodeHandler(){
        nodeHandlerMap.put("if",new IfNodeHandler());
    }

    void parse(String namespace, Element selectElement){
        String resultType = selectElement.attributeValue("resultType");
        String parameterType = selectElement.attributeValue("parameterType");
        String statementId = namespace +"."+ selectElement.attributeValue("id");
        String statementType = selectElement.attributeValue("statementType");
        configuration.addStatement(statementId,new MapperStatement
                .Builder(statementId,createSqlSource(selectElement))
                .parameterClass(conver2Class(parameterType))
                .resultClass(conver2Class(resultType))
                .statementType(statementType)
                .build());
    }

    private SqlSource createSqlSource(Element selectElement){
        MixedSqlNode rootSqlNode = dynamicParseSql(selectElement);
        if(isDynamic){
            return new DynamicSqlSource(rootSqlNode);
        }else{
            return new RawSqlSource(rootSqlNode);
        }
    }

    private MixedSqlNode dynamicParseSql(Element selectElement) {
        List<SqlNode> contents = new ArrayList<>();
        int nodeCount = selectElement.nodeCount();
        for(int i = 0; i < nodeCount; i++){
            Node node = selectElement.node(i);
            if(node instanceof Text){
                String sqlText = node.getText();
                SqlNode sqlNode;
                if(sqlText.contains("${")){
                    sqlNode = new TextSqlNode(sqlText);
                    isDynamic = true;
                }else{
                    sqlNode = new StaticTextSqlNode(sqlText);
                }
                contents.add(sqlNode);
            }else if(node instanceof Element){
                String nodeName = node.getName();
                NodeHandler handler = nodeHandlerMap.get(nodeName);
                handler.handleNode((Element)node,contents);
                isDynamic = true;
            }
        }
        return new MixedSqlNode(contents);
    }


    private Class<?> conver2Class(String classStr){
        try {
            return Class.forName(classStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    class IfNodeHandler implements NodeHandler{
        @Override
        public void handleNode(Element node2Handle, List<SqlNode> contents) {
            MixedSqlNode rootSqlNode = dynamicParseSql(node2Handle);
            String test = node2Handle.attributeValue("test");
            contents.add(new IfSqlNode(test,rootSqlNode));

        }
    }

}
