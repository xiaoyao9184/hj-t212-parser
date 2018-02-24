# HJ/T212 Parser

[![Build Status](https://travis-ci.org/xiaoyao9184/hj-t212-parser.svg?branch=master)](https://travis-ci.org/xiaoyao9184/hj-t212-parser)

环保部212传输协议


# 标准

[污染源在线自动监控（监测）系统数据传输标准（HJ/T 212-2005）](http://kjs.mep.gov.cn/hjbhbz/bzwb/other/xxbz/200602/t20060201_73038.shtml)
[污染物在线监控（监测）系统数据传输标准(HJ 212-2017代替HJ/T 212-2005)](http://kjs.mep.gov.cn/hjbhbz/bzwb/other/qt/201706/t20170608_415697.shtml)


# Segment语法

```
<object> ::= <entry> [;...n]
<entry> ::= key = <value> | key - sub_key = value [,...n]
<value> ::= value | && <object> &&
```

- key 是字符串
- value 是字符串