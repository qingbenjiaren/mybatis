package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.iface.ResultHandler;
import com.melo.kkb.mybatis.utils.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class DefaultResultHandler implements ResultHandler {
    @Override
    public <T> void parseResult(ResultSet rs, List<T> resultList, Class<?> clazz) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        while(rs.next()){
            if(SimpleTypeRegistry.isSimpleType(clazz)){
                resultList.add((T) rs.getObject(1));
            }else{
                Object t = clazz.newInstance();
                // 每一列，对应映射对象的一个属性
                // 列的名称要和对象的属性名称一致
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, rs.getObject(i));
                }
                resultList.add((T) t);
            }
        }
    }
}
