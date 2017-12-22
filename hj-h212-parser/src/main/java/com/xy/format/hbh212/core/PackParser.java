package com.xy.format.hbh212.core;

import com.xy.format.hbh212.model.Pack;
import com.xy.format.hbh212.model.schema.PackSchema;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class PackParser {

    public Pack parse(InputStream is) throws Exception {
        InputStreamReader dis = new InputStreamReader(is);
        Pack pack = new Pack();

        int count;

        char[] header = new char[2];
        dis.read(header);
        Verify.verifyChar(header,pack.getHeader(), PackSchema.HEADER);

        char[] len = new char[4];
        count = dis.read(len);
        Verify.verifyLen(count,len.length, PackSchema.SEGMENT_LEN);
        pack.setLength(len);

        int segmentLen = Integer.parseInt(new String(len));
        Verify.verifyRange(segmentLen,0,1024, PackSchema.SEGMENT_LEN);

        char[] segment = new char[segmentLen];
        count = dis.read(segment);
        Verify.verifyLen(count,segmentLen, PackSchema.SEGMENT);
        pack.setSegment(segment);

        char[] crc = new char[4];
        count = dis.read(crc);
        Verify.verifyLen(count,crc.length, PackSchema.CRC);
        //TODO crc
        pack.setCrc(crc);

        char[] footer = new char[2];
        dis.read(footer);
        Verify.verifyChar(footer,pack.getFooter(), PackSchema.FOOTER);

        return pack;
    }

}
