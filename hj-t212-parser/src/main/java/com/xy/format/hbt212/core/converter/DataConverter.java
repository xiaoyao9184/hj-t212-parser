package com.xy.format.hbt212.core.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.base.Converter;
import com.xy.format.hbt212.model.*;
import com.xy.format.hbt212.model.verify.DataElement;
import com.xy.format.hbt212.model.verify.T212Map;
import com.xy.format.segment.base.cfger.Configurator;
import com.xy.format.segment.base.cfger.Configured;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 转换器
 * 将T212Map转为
 * @see Data
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class DataConverter
        implements Converter<T212Map<String,Object>,Data>,
        Configured<DataConverter> {

    private static String SPLIT = "-";
    private static String SB = "SB";
    private static String REGEX_DEVICE = "SB.*-RS$|SB.*-RT$";
    private static String REGEX_LIVE_SIDE = ".*-Info$|.*-SN$";
    private static String REGEX_POLLUTION = ".*-.*";

    private ObjectMapper objectMapper;

    private Predicate<String> predicateDevice;
    private Predicate<String> predicateLiveSide;
    private Predicate<String> predicatePollution;


    public DataConverter(){
        predicateDevice = Pattern.compile(REGEX_DEVICE).asPredicate();
        predicateLiveSide = Pattern.compile(REGEX_LIVE_SIDE).asPredicate();
        predicatePollution = Pattern.compile(REGEX_POLLUTION).asPredicate();
    }

    /**
     * 根据Key中的分隔符分组
     * @param map Map
     * @param split 分隔符
     * @return 分组Map
     */
    private Map<String,Map<String,String>> groupBySplitKey(Map<String,String> map, String split){
        Map<String,Map<String,String>> result = map.entrySet().stream()
                .collect(Collectors.groupingBy(
                        t -> t.getKey().split(split)[0],
                        Collectors.toMap(
                                kv -> kv.getKey().split(split)[1],
                                Map.Entry::getValue
                        )
                ));
        return result;
    }

    /**
     * 转换 现场端
     * @param map
     * @return
     */
    private Map<String,LiveSide> convertLiveSide(Map<String,String> map){
        return groupBySplitKey(map,SPLIT)
                .entrySet()
                .stream()
                .map(kv -> {
                    LiveSide liveSide = objectMapper.convertValue(kv.getValue(),LiveSide.class);
                    return new AbstractMap.SimpleEntry<>(kv.getKey(),liveSide);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * 转换 设备
     * @param map
     * @return
     */
    private Map<String,Device> convertDevice(Map<String,String> map){
        return groupBySplitKey(map,SPLIT)
                .entrySet()
                .stream()
                .map(kv -> {
                    Device device = objectMapper.convertValue(kv.getValue(),Device.class);
                    return new AbstractMap.SimpleEntry<>(kv.getKey().replace(SB,""),device);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * 转换 污染因子
     * @param map
     * @return
     */
    private Map<String,Pollution> convertPollution(Map<String,String> map){
        return groupBySplitKey(map,SPLIT)
                .entrySet()
                .stream()
                .map(kv -> {
                    Pollution pollution = objectMapper.convertValue(kv.getValue(),Pollution.class);
                    return new AbstractMap.SimpleEntry<>(kv.getKey(),pollution);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }


    /**
     * 过滤Map中的Entry
     * @param map
     * @param predicate 过滤逻辑
     * @return
     */
    private Map<String,String> filter(Map<String,String> map, Predicate<String> predicate){
        return map.entrySet()
                .stream()
                .filter(kv -> predicate.test(kv.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * 转换 数据区
     * @param map
     * @return
     */
    private Map<String,Object> convertDataLevel(Map<String,String> map){
        Map<String,Object> cp = new HashMap<>();
        //v2017 entry
        Map<String,String> d = filter(map,predicateDevice);
        if(!d.isEmpty()){
            Map<String,Device> deviceMap = convertDevice(d);
            cp.put(CpData.DEVICE,deviceMap);
            d.keySet().forEach(map::remove);
        }

        Map<String,String> ls = filter(map,predicateLiveSide);
        if(!ls.isEmpty()){
            Map<String,LiveSide> liveSideMap = convertLiveSide(ls);
            cp.put(CpData.LIVESIDE,liveSideMap);
            ls.keySet().forEach(map::remove);
        }

        //v2005 entry
        String flag = map.get(DataElement.Flag.name());
        if(flag != null){
            List<DataFlag> dataFlags = convertDataFlag(flag);
            map.remove(DataElement.Flag.name());
            cp.put(Data.FLAG,dataFlags);
        }

        Map<String,String> p = filter(map,predicatePollution);
        if(!p.isEmpty()){
            Map<String,Pollution> pollutionMap = convertPollution(p);
            cp.put(CpData.POLLUTION,pollutionMap);
            p.keySet().forEach(map::remove);
        }

        //common entry must keep
        cp.putAll(map);
        return cp;
    }

    /**
     * 转换 数据段标记
     * @param flag
     * @return
     */
    private List<DataFlag> convertDataFlag(String flag){
        if(flag != null){
            int i = Integer.parseInt(flag);

            return Stream.of(DataFlag.values())
                    .filter(sf -> sf.isMarked(i))
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 转换 数据段
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    private Data convertDataLevel(T212Map<String,Object> map){
        String flag = (String) map.get(DataElement.Flag.name());
        if(flag != null){
            List<DataFlag> flagList = convertDataFlag(flag);
            //replace
            map.put(Data.FLAG,flagList);
        }

        Map<String,String> cp = (Map<String, String>) map.get(DataElement.CP.name());
        if(cp != null){
            Map<String,Object> cpMap = convertDataLevel(cp);
            //replace
            map.put(Data.CP,cpMap);
        }

        return objectMapper
                .convertValue(map,Data.class);
    }

    @Override
    public Data convert(T212Map<String,Object> map) {
        return convertDataLevel(map);
    }

    @Override
    public void configured(Configurator<DataConverter> by) {
        by.config(this);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
