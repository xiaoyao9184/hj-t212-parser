package com.xy.format.hbt212.core.ser;

import com.xy.format.hbt212.core.T212Generator;
import com.xy.format.hbt212.core.VerifyUtil;
import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.Pack;
import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;

import java.io.IOException;
import java.util.Arrays;

import static com.xy.format.hbt212.core.feature.VerifyFeature.DATA_CRC;
import static com.xy.format.hbt212.core.feature.VerifyFeature.DATA_LEN_RANGE;

/**
 * 通信包 级别 反序列化器
 * Created by xiaoyao9184 on 2018/2/24.
 */
public class PackLevelSerializer
        implements T212Serializer<Pack>, Configured<PackLevelSerializer> {

    private Configurator<T212Generator> generatorConfigurator;
    private int verifyFeature;

    @Override
    public void configured(Configurator<PackLevelSerializer> configurator){
        configurator.config(this);
    }

    @Override
    public void serialize(T212Generator generator, Pack pack) throws IOException, T212FormatException {
        generator.configured(generatorConfigurator);

        generator.writeHeader();

        if(DATA_LEN_RANGE.enabledIn(verifyFeature)){
            int segmentLen = 0;
            if(!Arrays.equals(pack.getLength(),new char[]{0,0,0,0})){
                segmentLen = Integer.parseInt(new String(pack.getLength()));
            }
            if(segmentLen == 0){
                segmentLen = pack.getSegment().length;
            }

            VerifyUtil.verifyRange(segmentLen,0,1024, PacketElement.DATA_LEN);
        }

        generator.writeDataAndLenAndCrc(pack.getSegment());

        if(DATA_CRC.enabledIn(verifyFeature)){
            if(Arrays.equals(pack.getCrc(),new char[]{0,0,0,0})){
                //ignore
            }else{
                VerifyUtil.verifyCrc(pack.getSegment(), pack.getCrc(), PacketElement.DATA_CRC);
            }
        }

        generator.writeFooter();
    }

    public void setVerifyFeature(int verifyFeature) {
        this.verifyFeature = verifyFeature;
    }

    public void setGeneratorConfigurator(Configurator<T212Generator> generatorConfigurator) {
        this.generatorConfigurator = generatorConfigurator;
    }

}
