package com.xy.stream.reader.core;

import org.junit.Test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/1/5.
 */
public class ReaderStreamTest {


    @Test
    public void testMatch() throws IOException {
        String data = "C=c;B=&&B1=b1;A=&&A1=a1;A2=a2;A3=a3&&&&";
        PushbackReader reader = new PushbackReader(new StringReader(data));
        CharBuffer buffer = CharBuffer.allocate(10);
        int len = ReaderStream.of(reader)
                .next()
                    .when(c -> c =='=')
                    .then()
                        .next(2)
                            .when(c -> Arrays.equals(c,new char[]{'&','&'}))
                            .then(() -> {
                                System.out.print("is '=&&'");
                            })
                            .back()
                        .back()
                    .then(() -> {
                        System.out.print("is '='");
                    })
                    .done()
                .read(buffer);

        assertEquals(len,1);
    }

}