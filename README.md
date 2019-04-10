# HJ/T212 Parser

[![Build Status](https://travis-ci.org/xiaoyao9184/hj-t212-parser.svg?branch=master)](https://travis-ci.org/xiaoyao9184/hj-t212-parser)
[![Artifact Status](https://jitpack.io/v/xiaoyao9184/hj-t212-parser.svg)](https://jitpack.io/#xiaoyao9184/hj-t212-parser)

环保部212传输协议


# 标准

[污染源在线自动监控（监测）系统数据传输标准（HJ/T 212-2005）](http://kjs.mee.gov.cn/hjbhbz/bzwb/other/xxbz/200602/t20060201_73038.shtml)
[污染物在线监控（监测）系统数据传输标准(HJ 212-2017代替HJ/T 212-2005)](http://kjs.mee.gov.cn/hjbhbz/bzwb/other/qt/201706/t20170608_415697.shtml)

当前支持特性为：

- 2005 2017双版协议兼容
- 解析协议为java对象、Map对象
- 反向生成协议包
- 结构校验、CRC校验、字段类型校验
- 校验启停、解析容错特性开关
- 码表枚举


# 使用

通过gradle引入托管在jitpack上的jar包

```groovy
repositories {
    maven{
        url 'https://jitpack.io'
    }
}
dependencies {
    compile 'com.github.xiaoyao9184.hj-t212-parser:hj-t212-parser:master-SNAPSHOT'
    compile 'com.github.xiaoyao9184.hj-t212-parser:hj-t212-translator:master-SNAPSHOT'
}
```

通过创建`T212Mapper`对象，获得读取、写入T212信息的功能
[查看此单元代码](https://github.com/xiaoyao9184/hj-t212-parser/blob/54eccf28a0ac8a147b163270d5028e6d6fc26901/hj-t212-parser/src/test/java/com/xy/format/hbt212/core/T212ParserTest.java#L20=L39)

```java
String h212 = "##0136ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";

T212Mapper mapper = new T212Mapper()
                .enableDefaultVerifyFeatures()
                .enableDefaultParserFeatures();

//从T212字符串中读取Data对象
Data data = mapper.readData(h212);

//.... use data

//create data and set it
data.setPw("000000");
//将Data对象写入成T212字符串
String result = mapper.writeDataAsString(data);

```

更多高级方式请参阅单元测试代码


# 类似Jackson的API

如果你使用过Jackson，你会发现以下类在设计方式上非常类似

| Jackson类 | T212类 |
|:-----:|:-----:|
| ObjectMapper | T212Mapper
| JsonParser | T212Parser
| JsonGenerator | T212Generator
| Deserializers | T212Deserializer
| Serializers | T212Serializer


# Segment语法

如果你很了解T212的数据区的结构定义，将会很简单的认同以下抽象语法，
当前使用`Segment`作为这种语法的代称。

```
<object> ::= <entry> [;...n]
<entry> ::= key = <value> | key - sub_key = value [,...n]
<value> ::= value | && <object> &&
```

- key 是字符串
- value 是字符串


# 协议错误修正

| 版本 | 位置 | 错误类型 | 错误描述 | 解决办法 |
|:-----:|:-----:|:-----:|:-----:|:-----:|
| 2017 | 28、29页 | 污染物编码冲突 | e701xx `污水提升泵` 与 `加药量` | `加药量` 使用 e711xx |
| 2017 | 30页 | 污染物编码冲突 | g119xx `脱硫率` 与 `入口二氧化硫 SO2` | `脱硫率` 使用 g200xx |
