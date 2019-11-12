package com.melo.kkb.mybatis.config;

import com.melo.kkb.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 解析全局配置文件
 */
public class XmlConfigurationBuilder {
    private Configuration configuration;

    public XmlConfigurationBuilder(){
        configuration = new Configuration();
    }

    public Configuration parse(InputStream is){
        Document document = DocumentUtils.readDocument(is);
        Element root = document.getRootElement();
        parseConfiguration(root);
        return configuration;
    }

    private void parseConfiguration(Element root) {
        Element environments = root.element("environments");
        parseEnvironment(environments);
        Element mappers = root.element("mappers");
        parseMappers(mappers);
    }

    private void parseMappers(Element mappers) {
    }

    private void parseEnvironment(Element environmentElement) {
        String defaultEnvId = environmentElement.attributeValue("default");
        if(defaultEnvId == null || defaultEnvId.equals("")){
            return;
        }
        List<Element> environments = environmentElement.elements();
        for(Element el : environments){
            if(defaultEnvId.equals(el.attributeValue("id"))){
                parseDataSource(el.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element el) {
        String type = el.attributeValue("type");
        if("DBCP".equals(type)){
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = new Properties();
            List<Element> propertiesEl = el.elements();
            for(Element prop : propertiesEl){
                properties.setProperty(prop.attributeValue("name"),prop.attributeValue("value"));
            }
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setDriverClassName(properties.getProperty("driver"));
            configuration.setDataSource(dataSource);
        }
    }
}
