package com.melo.kkb.mybatis.Executor;

import com.melo.kkb.mybatis.Executor.BaseExecutor;
import com.melo.kkb.mybatis.config.Configuration;
import com.melo.kkb.mybatis.config.MapperStatement;

import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    @Override
    List<Object> queryFromDatabase(MapperStatement mapperStatement, Configuration configuration, Object parameter) {
        //获取连接


        //设置参数

        //查询数据

        //封装结果
        return null;
    }
}
