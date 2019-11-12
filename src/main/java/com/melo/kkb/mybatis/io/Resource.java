package com.melo.kkb.mybatis.io;

import java.io.InputStream;

public class Resource {
    public static InputStream getResourceAsStream(String location){
        return Resource.class.getClassLoader().getResourceAsStream(location);
    }
}
