package com.xy.format.hbt212;

import javax.activation.UnsupportedDataTypeException;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum H212Translator {
    I;

    public String translation(Class<? extends Enum> ec,String code) throws UnsupportedDataTypeException, ClassNotFoundException {
        //noinspection ResultOfMethodCallIgnored
        Stream.of(ec.getInterfaces())
                .filter(c -> c.equals(CodeMean.class))
                .findAny()
                .orElseThrow(UnsupportedDataTypeException::new);

        return Stream.of(ec.getEnumConstants())
                .filter(e -> e instanceof CodeMean)
                .map(e -> (CodeMean)e)
                .filter(cm -> cm.code().equals(code))
                .findAny()
                .orElseThrow(ClassNotFoundException::new)
                .mean();
    }

}
