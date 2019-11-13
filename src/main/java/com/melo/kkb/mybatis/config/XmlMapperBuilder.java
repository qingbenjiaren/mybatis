package com.melo.kkb.mybatis.config;

import com.melo.kkb.mybatis.utils.DocumentUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
@SuppressWarnings("unchecked")
class XmlMapperBuilder {
    private Configuration configuration;
    XmlMapperBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    void parse(InputStream is){
        Document doc = DocumentUtils.readDocument(is);
        Element rootElement = doc.getRootElement();
        parseMapper(rootElement);
    }
    private void parseMapper(Element rootElement){
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.elements("select");
        XmlStatementBuilder xmlStatementBuilder = new XmlStatementBuilder(configuration);
        for(Element selectElement : selectElements){
            xmlStatementBuilder.parse(namespace,selectElement);
        }
    }
}
