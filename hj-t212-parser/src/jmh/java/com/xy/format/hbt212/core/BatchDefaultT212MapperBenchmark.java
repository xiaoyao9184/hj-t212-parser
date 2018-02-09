package com.xy.format.hbt212.core;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.Level;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Data;
import org.openjdk.jmh.annotations.*;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2018/1/12.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.HOURS)
@Warmup(iterations = 5, time = 3, timeUnit = TimeUnit.HOURS)
@Measurement(iterations = 10, time = 3, timeUnit = TimeUnit.HOURS)
@Fork(5)
@State(Scope.Benchmark)
public class BatchDefaultT212MapperBenchmark {

    private T212Mapper mapper;
    private List<String> packets;

    @Setup
    public void init(){
        LoggerContext loggerContext= (LoggerContext) LoggerFactory.getILoggerFactory();
        //设置全局日志级别
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("root");
        logger.setLevel(Level.OFF);

        mapper = new T212Mapper()
                .enableDefaultParserFeatures()
                .enableDefaultVerifyFeatures();

        Reflections reflections = new Reflections("", new ResourcesScanner());
        Set<String> fileNames = reflections.getResources(Pattern.compile(".*\\.h212"));

        packets = fileNames.stream()
                .flatMap(fileName -> {
                    try {
                        URI uri = this.getClass().getClassLoader().getResource(fileName).toURI();
                        return Files.readAllLines(Paths.get(uri))
                                .stream()
                                .map(line -> line + "\r\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return Stream.empty();
                })
                .collect(Collectors.toList());

        System.out.println("Packets :" + packets.size());
    }

    @Benchmark
    public void readMap() {
        List<Exception> exceptionList = new ArrayList<>();
        List<Map> timeMap = packets.stream()
                .map(t212 -> {
                    try {
                        return mapper.readMap(t212);
                    } catch (IOException | T212FormatException e) {
                        exceptionList.add(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Benchmark
    public void readDeepMap() {
        List<Exception> exceptionList = new ArrayList<>();
        List<Map> timeMap = packets.stream()
                .map(t212 -> {
                    try {
                        return mapper.readDeepMap(t212);
                    } catch (IOException | T212FormatException e) {
                        exceptionList.add(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Benchmark
    public void readData() {
        List<Exception> exceptionList = new ArrayList<>();
        List<Data> timeMap = packets.stream()
                .map(t212 -> {
                    try {
                        return mapper.readData(t212);
                    } catch (IOException | T212FormatException e) {
                        exceptionList.add(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}