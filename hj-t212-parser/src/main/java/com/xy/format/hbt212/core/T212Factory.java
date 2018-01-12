package com.xy.format.hbt212.core;

import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.deser.T212Deserializer;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * T212工厂
 * Created by xiaoyao9184 on 2018/1/10.
 */
public enum T212Factory {

    I;

    private T212Configurator configurator = new T212Configurator();
    final protected HashMap<Type, T212Deserializer<Object>> _rootDeserializers = new HashMap<>();

    /**
     * 创建解析器
     * @param is 字节流
     * @return 解析器
     */
    public T212Parser parser(InputStream is) {
        InputStreamReader dis = new InputStreamReader(is);
        T212Parser t212Parser = new T212Parser(dis);
        return t212Parser;
    }

    /**
     * 创建解析器
     * @param bytes 字节数组
     * @return 解析器
     */
    public T212Parser parser(byte[] bytes) {
        return parser(new ByteArrayInputStream(bytes));
    }

    /**
     * 创建解析器
     * @param reader 字符流
     * @return 解析器
     */
    public T212Parser parser(Reader reader) {
        T212Parser t212Parser = new T212Parser(reader);
        return t212Parser;
    }

    /**
     * 创建解析器
     * @param chars 字符数组
     * @return 解析器
     */
    public T212Parser parser(char[] chars) {
        return parser(new CharArrayReader(chars));
    }

    /**
     * 创建解析器
     * @param msg 字符串
     * @return 解析器
     */
    public T212Parser parser(String msg) {
        return parser(new StringReader(msg));
    }

    /**
     * 获取类型反序列化器
     * @param tClass 类型类
     * @param <T> 类型
     * @return 反序列化器
     */
    public <T> T212Deserializer<T> deserializerFor(Class<T> tClass){
        T212Deserializer<T> deserializer = (T212Deserializer<T>) _rootDeserializers.get(tClass);
        if(deserializer instanceof Configured){
            Configured configured = (Configured) deserializer;
            configured.configured(configurator);
        }
        return deserializer;
    }


    /**
     * 注册类型反序列化器
     * @param deserializerClass 反序列化器
     */
    public void deserializerRegister(Class<? extends T212Deserializer> deserializerClass) throws InstantiationException, IllegalAccessException {
        Type type = Stream.of(deserializerClass.getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType)t)
                .filter(pt -> pt.getRawType().equals(T212Deserializer.class))
                .map(pt -> pt.getActualTypeArguments()[0])
                .findFirst()
                .orElse(java.lang.Object.class);

        deserializerRegister(type,deserializerClass);
    }

    /**
     * 注册类型反序列化器
     * @param type 类型
     * @param deserializerClass 反序列化器
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void deserializerRegister(Type type, Class<? extends T212Deserializer> deserializerClass) throws IllegalAccessException, InstantiationException {
        _rootDeserializers.put(type,deserializerClass.newInstance());
    }

}
