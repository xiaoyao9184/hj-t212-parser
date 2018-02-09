package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.T212FormatException;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaoyao9184 on 2018/1/12.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class DefaultT212MapperBenchmark {

    private String h212;
    private T212Mapper mapper;

    @Setup
    public void init(){
//        LoggerContext loggerContext= (LoggerContext) LoggerFactory.getILoggerFactory();
//        //设置全局日志级别
//        ch.qos.logback.classic.Logger logger=loggerContext.getLogger("root");
//        logger.setLevel(Level.ERROR);
        h212 = "##0136ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();
    }

    @Benchmark
    public void readMap() throws IOException, T212FormatException {
        mapper.readMap(h212);
    }

    @Benchmark
    public void readDeepMap() throws IOException, T212FormatException {
        mapper.readDeepMap(h212);
    }

    @Benchmark
    public void readData() throws IOException, T212FormatException {
        mapper.readData(h212);
    }

}