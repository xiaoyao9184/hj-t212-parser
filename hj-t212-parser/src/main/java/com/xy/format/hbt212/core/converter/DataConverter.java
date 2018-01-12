package com.xy.format.hbt212.core.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy.format.hbt212.base.Converter;
import com.xy.format.hbt212.model.*;
import com.xy.format.hbt212.model.mixin.DataMixin;
import com.xy.format.hbt212.model.verify.DataElement;
import com.xy.format.hbt212.model.verify.T212Map;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * 转换器
 * 将T212Map转为
 * @see Data
 * Created by xiaoyao9184 on 2017/12/15.
 */
public class DataConverter
        implements Converter<T212Map<String,Object>,Data> {

    private static String REGEX_DEVICE = "SB.*-RS$|SB.*-RT$";
    private static String REGEX_LIVE_SIDE = ".*-Info$|.*-SN$";
    private static String REGEX_POLLUTION = ".*-.*";

    private ObjectMapper objectMapper = new ObjectMapper();

    private Predicate<String> predicateDevice;
    private Predicate<String> predicateLiveSide;
    private Predicate<String> predicatePollution;


    public DataConverter(){
        predicateDevice = Pattern.compile(REGEX_DEVICE).asPredicate();
        predicateLiveSide = Pattern.compile(REGEX_LIVE_SIDE).asPredicate();
        predicatePollution = Pattern.compile(REGEX_POLLUTION).asPredicate();

        objectMapper
                .configure(FAIL_ON_UNKNOWN_PROPERTIES,false)
                .addMixIn(Data.class, DataMixin.class);
    }


    /**
     * 转换 现场端
     * @param map
     * @return
     */
    private Map<String,LiveSide> convertLiveSide(Map<String,String> map){
        Map<String,Map<String,String>> result = new HashMap<>();
        map.forEach((key, value) -> {
            String[] ks = key.split("-");
            Map<String, String> f;
            if (result.containsKey(ks[0])) {
                f = result.get(ks[0]);
            } else {
                f = new HashMap<>();
                result.put(ks[0], f);
            }
            f.put(ks[1], value);
        });
        return result.entrySet()
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
        Map<String,Map<String,String>> result = new HashMap<>();
        map.forEach((key, value) -> {
            String[] ks = key.replace("SB","").split("-");
            Map<String, String> f;
            if (result.containsKey(ks[0])) {
                f = result.get(ks[0]);
            } else {
                f = new HashMap<>();
                result.put(ks[0], f);
            }
            f.put(ks[1], value);
        });
        return result.entrySet()
                .stream()
                .map(kv -> {
                    Device device = objectMapper.convertValue(kv.getValue(),Device.class);
                    return new AbstractMap.SimpleEntry<>(kv.getKey(),device);
                })
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    /**
     * 转换 污染因子
     * @param map
     * @return
     */
    private Map<String,Pollution> convertPollution(Map<String,String> map){
        Map<String,Map<String,String>> result = new HashMap<>();
        map.forEach((key, value) -> {
            String[] ks = key.split("-");
            Map<String, String> f;
            if (result.containsKey(ks[0])) {
                f = result.get(ks[0]);
            } else {
                f = new HashMap<>();
                result.put(ks[0], f);
            }
            f.put(ks[1], value);
        });
        return result.entrySet()
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
    private Map<String,String> filter(Map<String,String> map, Predicate predicate){
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
    private CpData convertDataLevel(Map<String,String> map){
        //v2017
        Map<String,String> d = filter(map,predicateDevice);
        Map<String,Device> deviceMap = convertDevice(d);
        d.keySet().stream().peek(map::remove);

        Map<String,String> ls = filter(map,predicateLiveSide);
        Map<String,LiveSide> liveSideMap = convertLiveSide(ls);
        ls.keySet().stream().peek(map::remove);

        //v2005
        String flag = (String) map.get(DataElement.Flag.name());
        List<DataFlag> dataFlags = convertDataFlag(flag);

        Map<String,String> p = filter(map,predicatePollution);
        Map<String,Pollution> pollutionMap = convertPollution(p);

        CpData cpData = objectMapper.convertValue(map,CpData.class);
        cpData.setDataFlag(dataFlags);
        cpData.setDevice(deviceMap);
        cpData.setLiveSide(liveSideMap);
        cpData.setPollution(pollutionMap);

        return cpData;
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
    private Data convertDataLevel(T212Map<String,Object> map){
        Data data = objectMapper
                .convertValue(map,Data.class);

        String flag = (String) map.get(DataElement.Flag.name());
        data.setDataFlag(convertDataFlag(flag));

        Map<String,String> cp = (Map<String, String>) map.get(DataElement.CP.name());
        data.setCp(convertDataLevel(cp));

        return data;
    }

    @Override
    public Data convert(T212Map<String,Object> map) {
        return convertDataLevel(map);
    }
}
