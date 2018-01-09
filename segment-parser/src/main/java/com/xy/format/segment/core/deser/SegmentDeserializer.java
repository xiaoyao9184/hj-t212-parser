package com.xy.format.segment.core.deser;

import com.xy.format.segment.core.SegmentParser;
import com.xy.format.segment.exception.FormatException;

import java.io.IOException;

/**
 * Created by xiaoyao9184 on 2018/1/4.
 */
public interface SegmentDeserializer<Target> {

    Target deserialize(SegmentParser parser) throws IOException, FormatException;
}
