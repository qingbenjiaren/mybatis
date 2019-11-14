package com.melo.kkb.mybatis.sqlnode;

import com.melo.kkb.mybatis.sqlnode.iface.SqlNode;
import com.melo.kkb.mybatis.utils.GenericTokenParser;
import com.melo.kkb.mybatis.utils.OgnlUtils;
import com.melo.kkb.mybatis.utils.SimpleTypeRegistry;
import com.melo.kkb.mybatis.utils.TokenHandler;

/**
 * 处理带有参数的sql
 */
public class TextSqlNode implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText){
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        //先处理${}，将处理之后的SQL语句，追加到context中
        GenericTokenParser tokenParser = new GenericTokenParser("${", "}", new BindingTokenParser(context));
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }

    private static class BindingTokenParser implements TokenHandler{
        private DynamicContext context;
        public BindingTokenParser(DynamicContext context){
            this.context = context;
        }
        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBindings().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }

            // 使用Ognl api去获取相应的值
            Object value = OgnlUtils.getValue(expression, paramObject);
            return value == null ? "" : String.valueOf(value);
        }
    }
}
