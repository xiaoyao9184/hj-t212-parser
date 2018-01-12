package com.xy.format.hbt212.base.parser;

import java.io.IOException;
import java.io.Reader;

/**
 * 字符流解析器
 * Created by xiaoyao9184 on 2018/1/3.
 */
public abstract class ReaderParser<Target>
        implements Parser<Target> {

    protected Reader reader;

    public ReaderParser(Reader reader){
        this.reader = reader;
    }


    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
