package com.xy.format.hbt212.base.parser;

import com.xy.format.hbt212.exception.T212FormatException;

import java.io.Closeable;
import java.io.IOException;

/**
 * T212解析器
 * Created by xiaoyao9184 on 2018/1/3.
 */
public interface Parser<Target>
        extends Closeable {

    Target parse() throws T212FormatException, IOException;
}
