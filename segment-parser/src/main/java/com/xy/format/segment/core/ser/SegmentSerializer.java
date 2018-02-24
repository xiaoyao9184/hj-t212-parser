package com.xy.format.segment.core.ser;

import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.exception.SegmentFormatException;

import java.io.IOException;

/**
 * Created by xiaoyao9184 on 2018/2/24.
 */
public interface SegmentSerializer<Target> {

    void serialize(SegmentGenerator generator, Target target) throws IOException, SegmentFormatException;
}
