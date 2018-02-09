package com.xy.format.hbt212.core;

import com.xy.format.hbt212.core.feature.ParserFeature;
import com.xy.format.hbt212.core.feature.VerifyFeature;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.segment.core.feature.SegmentParserFeature;
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
public class StrictT212MapperBenchmark {

    private String h212;
    private T212Mapper mapper;

    @Setup
    public void init(){
        h212 = "##0139ST=32;CN=2011;PW=123456;MN=LD130133000015;CP=&&DataTime=20160824003817000;B01-Rtd=36.91;011-Rtd=231.0,011-Flag=N;060-Rtd=1.803,060-Flag=N&&4980\r\n";
        mapper = new T212Mapper()
                .enable(ParserFeature.HEADER_CONSTANT)
                .enable(ParserFeature.FOOTER_CONSTANT)
                .disable(SegmentParserFeature.IGNORE_INVAILD_SYMBOL)
                .disable(SegmentParserFeature.ALLOW_ISOLATED_KEY)
                .disable(SegmentParserFeature.ALLOW_KEY_NOT_CLOSED)
                .enable(VerifyFeature.DATA_LEN_RANGE)
                .enable(VerifyFeature.DATA_CRC)
                .enable(VerifyFeature.USE_VERIFICATION)
                .enable(VerifyFeature.THROW_ERROR_VERIFICATION_FAILED)
                .enable(VerifyFeature.STRICT);
    }

    @Benchmark
    public void mapMap() throws IOException, T212FormatException {
        mapper.readMap(h212);
    }

    @Benchmark
    public void mapDeepMap() throws IOException, T212FormatException {
        mapper.readDeepMap(h212);
    }

    @Benchmark
    public void mapData() throws IOException, T212FormatException {
        mapper.readData(h212);
    }

}