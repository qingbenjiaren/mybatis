package com.melo.kkb.mybatis.config;

import com.melo.kkb.mybatis.io.Resource;
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
@SuppressWarnings("ALL")
public class XmlConfigurationBuilder {
    private Configuration configuration;

    public XmlConfigurationBuilder(){
        configuration = new Configuration();
    }

    /**
     * 从跟节点开始解析全局配置文件
     * @param is
     * @return
     */
    public Configuration parse(InputStream is){
        Document document = DocumentUtils.readDocument(is);
        Element root = document.getRootElement();
        parseConfiguration(root);
        return configuration;
    }

    /**
     * rootElement
     * @param root
     */
    private void parseConfiguration(Element root) {
        Element environments = root.element("environments");
        parseEnvironment(environments);
        Element mappers = root.element("mappers");
        parseMappers(mappers);
    }

    /**
     * mappers
     * @param mappers
     */
    private void parseMappers(Element mappers) {
        List<Element> mapperElements = mappers.elements("mapper");
        //创建专门来解析映射文件的解析类
        XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
        for(Element mapperElement : mapperElements){
            String mapperLocation = mapperElement.attributeValue("resource");
            xmlMapperBuilder.parse(Resource.getResourceAsStream(mapperLocation));
        }
    }

    /**
     * environment
     * @param environmentElement
     */
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

    /**
     * dbElement
     * @param el
     */
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
