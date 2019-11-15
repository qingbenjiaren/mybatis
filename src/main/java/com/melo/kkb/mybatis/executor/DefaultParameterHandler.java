package com.melo.kkb.mybatis.executor;


import com.melo.kkb.mybatis.executor.iface.ParameterHandler;
import com.melo.kkb.mybatis.config.MapperStatement;
import com.melo.kkb.mybatis.sqlsource.BoundSql;
import com.melo.kkb.mybatis.sqlsource.ParameterMapping;
import com.melo.kkb.mybatis.utils.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
public class DefaultParameterHandler implements ParameterHandler {
    private MapperStatement mapperStatement;
    private Object param;
    private BoundSql boundSql;

    public DefaultParameterHandler(MapperStatement mapperStatement,Object param){
        this.mapperStatement = mapperStatement;
        this.param = param;
        this.boundSql = mapperStatement.getSqlSource().getBoundSql(param);
    }

    @Override
    public void setParameter(PreparedStatement ps) {
        Class<?> clazz = mapperStatement.getParameterClass();
        try {
            if (SimpleTypeRegistry.isSimpleType(clazz)) {
                ps.setObject(1, param);
            } else if (clazz == Map.class) {
                List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
                Map<String, Object> paramMap = (HashMap<String, Object>) param;
                for (int i = 0; i < parameterMappingList.size(); i++) {
                    ps.setObject(i+1, paramMap.get(parameterMappingList.get(i).getName()));
                }
            } else {
                List<ParameterMapping> getParameterMappingList = boundSql.getParameterMappingList();

                for (int i = 0; i < getParameterMappingList.size(); i++) {
                    // 获取#{}中的属性名称
                    ParameterMapping parameterMapping = getParameterMappingList.get(i);
                    String name = parameterMapping.getName();
                    // 根据属性名称，获取入参对象中对应的属性的值
                    // 要求#{}中的属性名称和入参对象中的属性名称一致
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    Object value = field.get(param);
                    ps.setObject(i+1, value);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
