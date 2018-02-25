package com.xy.format.hbt212.core.ser;

import com.xy.format.segment.core.SegmentGenerator;
import com.xy.format.segment.core.ser.SegmentSerializer;
import com.xy.format.segment.exception.SegmentFormatException;

import java.io.IOException;
import java.util.Map;

/**
 * CPData Map 系列化
 * 其中可能包含PartEntity
 * Created by xiaoyao9184 on 2018/2/25.
 */
public class T212CpMapPathValueSegmentSerializer
        implements SegmentSerializer<Object> {

    public T212CpMapPathValueSegmentSerializer() {
    }

    @Override
    public void serialize(SegmentGenerator generator, Object data) throws IOException, SegmentFormatException {
        if(data instanceof String){
            //NOT CP map but also support
            String value = (String) data;
            generator.writeValue(value);
        }else if(data instanceof Map){
            //maybe T212Map
            Map<String,Object> map = (Map) data;
            SegmentGenerator g = generator.writeObjectStart();

            for (Map.Entry<String, Object> kv : map.entrySet()) {
                if(kv.getValue() instanceof String){
                    g.writeKey(kv.getKey());
                    g.writeValue((String) kv.getValue());
                }else if(kv.getValue() instanceof Map){
                    //is a part entity
                    Map<String,Object> partValue = (Map<String, Object>) kv.getValue();
                    g.writeKey(kv.getKey());
                    for (Map.Entry<String, Object> pkv : partValue.entrySet()) {
                        g.writePathKey(pkv.getKey());
                        g.writeValue(pkv.getValue().toString());
                    }
                }
            }

            generator.writeObjectEnd();
        }else{
            throw new SegmentFormatException("Not support this type serialize:" + data.getClass().getName());
        }
    }

}
