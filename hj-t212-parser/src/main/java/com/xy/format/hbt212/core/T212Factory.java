package com.xy.format.hbt212.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.core.cfger.T212Configurator;
import com.xy.format.hbt212.core.converter.DataConverter;
import com.xy.format.hbt212.core.deser.T212Deserializer;
import com.xy.format.hbt212.core.ser.T212Serializer;
import com.xy.format.segment.base.cfger.Configured;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * T212工厂
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212Factory {

    private T212Configurator configurator = new T212Configurator();
    final protected HashMap<Type, T212Deserializer<Object>> _rootDeserializers = new HashMap<>();
    final protected HashMap<Type, T212Serializer<Object>> _rootSerializers = new HashMap<>();



    public T212Factory(){

    }

    public T212Factory(T212Configurator configurator){
        this.configurator = configurator;
    }

    public T212Factory copy() {
        T212Factory factory = new T212Factory();
        factory.setConfigurator(this.configurator);
        factory._rootDeserializers.putAll(this._rootDeserializers);
        factory._rootSerializers.putAll(this._rootSerializers);
        return factory;
    }



    public T212Configurator getConfigurator() {
        return configurator;
    }

    public void setConfigurator(T212Configurator configurator) {
        this.configurator = configurator;
    }

    /**
     * 创建解析器
     * @param is 字节流
     * @return 解析器
     */
    public T212Parser parser(InputStream is) {
        InputStreamReader dis = new InputStreamReader(is);
        T212Parser parser = new T212Parser(dis);
        parser.configured(configurator);
        return parser;
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
        T212Parser parser = new T212Parser(reader);
        parser.configured(configurator);
        return parser;
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

    public <T> T212Deserializer<T> deserializerFor(Type type, Class<T> tClass) {
        T212Deserializer<T> deserializer = (T212Deserializer<T>) _rootDeserializers.get(type);
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


    /**
     * 创建产生器
     * @param os 字节流
     * @return 产生器
     */
    public T212Generator generator(OutputStream os) {
        OutputStreamWriter osw = new OutputStreamWriter(os);
        T212Generator generator = new T212Generator(osw);
        generator.configured(configurator);
        return generator;
    }

    /**
     * 创建产生器
     * @param writer 字符流
     * @return 产生器
     */
    public T212Generator generator(Writer writer){
        T212Generator generator = new T212Generator(writer);
        generator.configured(configurator);
        return generator;
    }

    /**
     * 获取类型序列化器
     * @param <T> 类型
     * @return 序列化器
     */
    public <T> T212Serializer<T> serializerFor(Class<T> value){
        T212Serializer<T> serializer = (T212Serializer<T>) _rootSerializers.get(value);
        if(serializer instanceof Configured){
            Configured configured = (Configured) serializer;
            configured.configured(configurator);
        }
        return serializer;
    }

    /**
     * 注册类型序列化器
     * @param serializerClass 序列化器
     */
    public void serializerRegister(Class<? extends T212Serializer> serializerClass) throws InstantiationException, IllegalAccessException {
        Type type = Stream.of(serializerClass.getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType)t)
                .filter(pt -> pt.getRawType().equals(T212Serializer.class))
                .map(pt -> pt.getActualTypeArguments()[0])
                .findFirst()
                .orElse(java.lang.Object.class);

        serializerRegister(type,serializerClass);
    }

    /**
     * 注册类型序列化器
     * @param type 类型
     * @param serializerClass 序列化器
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void serializerRegister(Type type, Class<? extends T212Serializer> serializerClass) throws IllegalAccessException, InstantiationException {
        _rootSerializers.put(type,serializerClass.newInstance());
    }



    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    /**
     * null mean use default
     * @see T212Configurator#configure(DataConverter)
     * @return NULL
     */
    public ObjectMapper objectMapper() {
        return null;
    }

}
