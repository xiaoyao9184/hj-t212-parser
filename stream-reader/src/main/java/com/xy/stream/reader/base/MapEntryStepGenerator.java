package com.xy.stream.reader.base;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class MapEntryStepGenerator<K,V> {

    private BiConsumer<K,V> consumer;
    private BinaryOperator<K> keyMergeOperator;
    private BinaryOperator<V> valueMergeOperator;
    private Token token;
    private K k;
    private V v;
    private Supplier<K> kSupplier;
    private Supplier<V> vSupplier;


    private MapEntryStepGenerator(BiConsumer<K,V> consumer, BinaryOperator<K> keyMergeOperator, BinaryOperator<V> valueMergeOperator){
        this.consumer = consumer;
        this.keyMergeOperator = keyMergeOperator;
        this.valueMergeOperator = valueMergeOperator;
        this.token = Token.NONE;
    }

    public void putKey(K k){
        switch (token){
            case KEY:
                this.k = keyMergeOperator.apply(this.k,k);
                break;
            case VALUE:
                generate();
            case NONE:
                this.k = k;
                this.token = Token.KEY;
                break;
        }
    }

    public void putValue(V v){
        switch (token){
            case VALUE:
                this.v = valueMergeOperator.apply(this.v,v);
                break;
            case NONE:
            case KEY:
                this.v = v;
                this.token = Token.VALUE;
                break;
        }
    }

    public void generate(){
        this.consumer.accept(this.k,this.v);
        this.k = kSupplier == null ? null : kSupplier.get();
        this.v = vSupplier == null ? null : vSupplier.get();
    }


    enum Token {
        NONE,
        KEY,
        VALUE
    }



    public static <K,V> MapEntryStepGenerator.Builder<K,V> builder(){
        return new Builder<K,V>();
    }

    public static class Builder<K,V> {
        MapEntryStepGenerator<K,V> result = new MapEntryStepGenerator<>(null,null,null);

        public Builder<K,V> consumer(BiConsumer<K,V> consumer){
            result.consumer = consumer;
            return this;
        }

        public Builder<K,V> keyMergeOperator(BinaryOperator<K> keyMergeOperator){
            result.keyMergeOperator = keyMergeOperator;
            return this;

        }

        public Builder<K,V> valueMergeOperator(BinaryOperator<V> valueMergeOperator){
            result.valueMergeOperator = valueMergeOperator;
            return this;
        }

        public Builder<K,V> keyDefault(Supplier<K> keySupplier){
            result.kSupplier = keySupplier;
            return this;
        }

        public Builder<K,V> valueDefault(Supplier<V> valueSupplier){
            result.vSupplier = valueSupplier;
            return this;
        }


        public MapEntryStepGenerator<K,V> create(){
            if(result.consumer == null){
                throw new IllegalArgumentException("consumer");
            }

            if(result.keyMergeOperator == null){
                throw new IllegalArgumentException("keyMergeOperator");
            }

            if(result.valueMergeOperator == null){
                throw new IllegalArgumentException("valueMergeOperator");
            }

            return result;
        }
    }
}
