package com.xy.format.segment.base.cfger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 适配多个Configurator
 *
 * 解决无法继承多次继承同一个泛型接口
 *
 * 继承本Adapter后，
 * 将符合泛型接口的方法通过方法引用聚合到
 * @see #configurators() 方法实现中，
 * 返回即可
 *
 * Created by xiaoyao9184 on 2018/1/9.
 */
public abstract class MultipleConfiguratorAdapter
        implements Configurator {

    private Map<Type,Configurator> cacheConfigurators;
    private boolean useCache;

    protected MultipleConfiguratorAdapter() {
        cache(configurators());
    }

    public void cache(Collection<Configurator> collection){
        this.useCache = !collection.isEmpty();
        this.cacheConfigurators = collection
                .stream()
                .collect(Collectors.toMap(
                        getConfiguratorTargetType(),
                        Function.identity()
                ));
    }

    private void useCache(Object target){
        //noinspection unchecked
        cacheConfigurators.get(target.getClass())
                .config(target);
    }

    private void notCache(Object target){
        //noinspection unchecked
        configurators()
                .stream()
                .filter(configurator -> checkConfiguratorTargetType(configurator,target))
                .findFirst()
                .ifPresent(configurator -> configurator.config(target));
    }

    /**
     * @see
     * @return
     */
    public abstract Collection<Configurator> configurators();

    @Override
    public void config(Object target) {
        if(useCache) {
            useCache(target);
        } else{
            notCache(target);
        }
    }

    /**
     * 静态方法 引用 方法
     * @return 被引用的方法
     */
    private static Function<Configurator,Type> getConfiguratorTargetType(){
        return (configurator) -> Stream.of(configurator.getClass().getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType)t)
                .filter(pt -> pt.getOwnerType().equals(Configurator.class))
                .map(pt -> pt.getActualTypeArguments()[0])
                .findFirst()
                .orElse(java.lang.Object.class);
    }

    private static boolean checkConfiguratorTargetType(Configurator configurator, Object target){
        return Stream.of(configurator.getClass().getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType)t)
                .filter(pt -> pt.getOwnerType().equals(Configurator.class))
                .map(pt -> pt.getActualTypeArguments()[0])
                .anyMatch(t -> t.equals(target.getClass()));
    }



}
