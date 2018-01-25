package com.xy.format.hbt212.model.verify;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * T212 Map
 * 解决无法对MAP进行验证定义问题
 * Created by xiaoyao9184 on 2018/1/10.
 */
//@FieldMissing(groups = T212Map.Group.DataLevel.class)
//@ValueRange(groups = T212Map.Group.DataLevel.class)
//@FieldValidDate(field = "QN",
//        value = @ValidDate(field = "QN", format = "yyyyMMddHHmmssSSS"))
////@FieldValidDate(field = "QN", groups = ModeGroup.Strict.class,
////        value = @ValidDate(field = "QN", format = "yyyyMMddHHmmssSSS", optional = false))
//@FieldC(field = "ST",
//        value = @C(len = 2))
////@NotBlank(groups = ModeGroup.Strict.class)
//@FieldC(field = "CN",
//        value = @C(len = 4))
////@NotBlank(groups = ModeGroup.Strict.class)
//@FieldC(field = "PW",
//        value = @C(len = 6))
////@NotBlank(groups = ModeGroup.Strict.class)
//@FieldC(field = "MN", groups = VersionGroup.V2005.class,
//        value = @C(len = 14))
//@FieldC(field = "MN", groups = VersionGroup.V2017.class,
//        value = @C(len = 24))
////@NotBlank(groups = ModeGroup.Strict.class)
//@FieldC(field = "Flag",
//        value = @C(len = 1))
//@FieldN(field = "PNUM",
//        value = @N(integer = 4))
////@Min(value = 1, groups = ModeGroup.UseSubPacket.class)
//@FieldN(field = "PNO",
//        value = @N(integer = 4))
////@Min(value = 1, groups = ModeGroup.UseSubPacket.class)
//// 本意为组取交，无法实现
////@FieldC(field = "CP", groups = { VersionGroup.V2005.class, T212MapLevelGroup.DataLevel.class },
////        value = @C(len = 950))
////@FieldC(field = "CP", groups = { VersionGroup.V2017.class, T212MapLevelGroup.DataLevel.class },
////        value = @C(len = 960))
//
////CP级别的数据验证
public class T212Map<K,V>
        implements Map<K,V>, Serializable {

    private Map<K,V> m;

    public T212Map(Map<K, V> m) {
        this.m = m;
    }

    @Deprecated
    public static <K,V> T212Map<K,V> create(Map<K,V> map){
        return new T212Map<>(map);
    }

    @Deprecated
    public static <K,V> T212Map<K,V> create2005(Map<K,V> map) {
        return new T212MapV2005<>(map);
    }

    @Deprecated
    public static <K,V> T212Map<K,V> create2017(Map<K,V> map) {
        return new T212MapV2017<>(map);
    }

    public static T212DataLevelMap createDataLevel(Map<String,String> map) {
        return new T212DataLevelMap(map);
    }

    public static T212CpDataLevelMap createCpDataLevel(Map<String,Object> map) {
        return new T212CpDataLevelMap(map);
    }

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return m.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return m.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return m.get(key);
    }

    @Override
    public V put(K key, V value) {
        return m.put(key,value);
    }

    @Override
    public V remove(Object key) {
        return m.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.m.putAll(m);
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public Set<K> keySet() {
        return m.keySet();
    }

    @Override
    public Collection<V> values() {
        return m.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return m.entrySet();
    }

}
