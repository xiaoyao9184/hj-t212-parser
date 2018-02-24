package com.xy.format.hbt212.core;

import com.xy.format.hbt212.exception.T212FormatException;
import com.xy.format.hbt212.model.verify.PacketElement;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

import static com.xy.format.hbt212.core.feature.ParserFeature.FOOTER_CONSTANT;
import static com.xy.format.hbt212.core.feature.ParserFeature.HEADER_CONSTANT;

/**
 * T212通信包解析器
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class T212Parser
        implements Configured<T212Parser>, Closeable {

    public static char[] HEADER = new char[]{ '#','#' };
    public static char[] FOOTER = new char[]{ '\r', '\n' };

    protected Reader reader;
    //
    private int parserFeature;

    //TEMP
    private int count;
//    private PacketElement token;

    public T212Parser(Reader reader){
        this.reader = reader;
    }

    /**
     * 设置解析特性
     * @param parserFeature 解析特性
     */
    public void setParserFeature(int parserFeature) {
        this.parserFeature = parserFeature;
    }


    /**
     * 读取 包头
     * @see PacketElement#HEADER
     * @return chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readHeader() throws T212FormatException, IOException {
        char[] header = new char[2];
        count = reader.read(header);
        VerifyUtil.verifyLen(count, 2, PacketElement.HEADER);
        if(HEADER_CONSTANT.enabledIn(parserFeature)){
            VerifyUtil.verifyChar(header, HEADER, PacketElement.HEADER);
        }
        return header;
    }

    /**
     * 读取 数据段长度
     * @see PacketElement#DATA_LEN
     * @return chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readDataLen() throws T212FormatException, IOException {
        char[] len = new char[4];
        count = reader.read(len);
        VerifyUtil.verifyLen(count,len.length, PacketElement.DATA_LEN);
        return len;
    }

    /**
     * 读取 4字节Integer
     * @param radix 进制
     * @return Integer
     * @throws IOException
     */
    public int readInt32(int radix) throws IOException {
        char[] intChars = new char[4];
        count = reader.read(intChars);
        if(count != 4){
            return -1;
        }
        return Integer.parseInt(new String(intChars),radix);
    }

    /**
     * 读取 数据段
     * @see PacketElement#DATA
     * @return chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readData(int segmentLen) throws T212FormatException, IOException {
        char[] segment = new char[segmentLen];
        count = reader.read(segment);
        VerifyUtil.verifyLen(count,segmentLen, PacketElement.DATA);
        return segment;
    }

    /**
     * 读取 DATA_CRC 校验
     * @see PacketElement#DATA_CRC
     * @return header chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readCrc() throws T212FormatException, IOException {
        char[] crc = new char[4];
        count = reader.read(crc);
        VerifyUtil.verifyLen(count,crc.length, PacketElement.DATA_CRC);
        return crc;
    }

    /**
     * 读取 数据段长度
     * @see PacketElement#DATA_LEN
     * @return chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readFooter() throws T212FormatException, IOException {
        char[] footer = new char[2];
        count = reader.read(footer);
        VerifyUtil.verifyLen(count, 2, PacketElement.FOOTER);
        if(FOOTER_CONSTANT.enabledIn(parserFeature)){
            VerifyUtil.verifyChar(footer, FOOTER, PacketElement.FOOTER);
        }
        return footer;
    }

    /**
     * 读取 包尾
     * @see PacketElement#FOOTER
     * @return chars
     * @throws T212FormatException
     * @throws IOException
     */
    public char[] readDataAndCrc(int dataLen) throws IOException, T212FormatException {
        reader.mark(-0);
        char[] data = new char[dataLen];
        count = reader.read(data);
        VerifyUtil.verifyLen(count,dataLen, PacketElement.DATA);

        int crc = readInt32(16);
        if(crc != -1 &&
                crc16Checkout(data,dataLen) == crc){
            return data;
        }
        reader.reset();
        return null;
    }


    @Override
    public void configured(Configurator<T212Parser> configurator) {
        configurator.config(this);
    }

    @Override
    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * CRC校验
     * @param msg 消息
     * @param length 长度
     * @return DATA_CRC 校验码
     */
    public static int crc16Checkout(char[] msg, int length) {
        int i,j,crc_reg,check;

        crc_reg = 0xFFFF;
        for(i=0; i<length; i++) {
            crc_reg = (crc_reg>>8) ^ msg[i];
            for(j=0;j<8;j++) {
                check = crc_reg & 0x0001;
                crc_reg >>= 1;
                if (check == 0x0001) {
                    crc_reg ^= 0xA001;
                }
            }
        }
        return crc_reg;
    }

}
