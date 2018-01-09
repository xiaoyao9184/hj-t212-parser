package com.xy.stream.reader.core;

import com.xy.stream.reader.base.MapEntryStepGenerator;
import com.xy.stream.reader.base.lambda.RunnableWithThrowable;
import com.xy.stream.reader.base.lambda.SupplierWithThrowable;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.xy.stream.reader.core.ReaderMatch.alwaysReturnPresent;

public class SingleCharMatch<ParentStream extends ReaderStream>
        implements ReaderMatch<SingleCharMatch<ParentStream>,ParentStream,Character> {

    private ParentStream parentStream;
    private PushbackReader reader;
    private Map<Predicate<Character>, SupplierWithThrowable<Optional<Object>,IOException>> map;
    private MapEntryStepGenerator<Predicate<Character>,SupplierWithThrowable<Optional<Object>,IOException>> generator;

    public SingleCharMatch(ParentStream parentStream) {
        this.parentStream = parentStream;
        this.reader = parentStream.reader();
        this.map = new LinkedHashMap<>();
        MapEntryStepGenerator.Builder<Predicate<Character>,SupplierWithThrowable<Optional<Object>,IOException>> builder = MapEntryStepGenerator.builder();
        this.generator = builder.consumer((k,v) -> map.put(k,v))
                .keyDefault(() -> character -> false)
                .keyMergeOperator(Predicate::or)
                .valueMergeOperator((thisRunnable,runnable) -> () -> {
                    Optional<Object> o = thisRunnable.get();
                    if(!o.isPresent()){
                        o = runnable.get();
                    }
                    return o;
                })
                .create();
    }

    /**
     * 当符合条件时
     * @param predicate 条件
     * @return THIS
     */
    public SingleCharMatch<ParentStream> when(Predicate<Character> predicate) {
        generator.putKey(predicate);
        return this;
    }

    /**
     * 当字符一致时
     * @param character 字符
     * @return THIS
     */
    public SingleCharMatch<ParentStream> when(int character) {
        generator.putKey((c) -> c == character);
        return this;
    }

    /**
     * 当字符一致时
     * @param character 字符
     * @return THIS
     */
    public SingleCharMatch<ParentStream> when(Character character) {
        generator.putKey((c) -> c == character);
        return this;
    }

    public SingleCharMatch<ParentStream> then(Supplier<IOException> exceptionSupplier) {
        generator.putValue(() -> {
            throw exceptionSupplier.get();
        });
        return this;
    }

    public SingleCharMatch<ParentStream> then(SupplierWithThrowable<Optional<Object>,IOException> runnable) {
        generator.putValue(runnable);
        return this;
    }

    public SingleCharMatch<ParentStream> then(RunnableWithThrowable<IOException> runnable) {
        generator.putValue(alwaysReturnPresent(runnable));
        return this;
    }

    public SingleCharMatch<ParentStream> skip() {
        generator.putValue(alwaysReturnPresent());
        return this;
    }

    public ReaderStream<SingleCharMatch<ParentStream>> then() {
        ReaderStream<SingleCharMatch<ParentStream>> reader = new ReaderStream<>(
                this.reader,
                this.parentStream.bufSize() -1,
                this);
        then(reader::match);
        return reader;
    }

    @Override
    public ParentStream done() {
        generator.generate();
        return parentStream;
    }

    @Override
    public Optional<Character> match() throws IOException {
        int i = reader.read();
        Character character = (char) i;

        Optional<SupplierWithThrowable<Optional<Object>,IOException>> r = map.entrySet().stream()
                .filter(kv -> kv.getKey().test(character))
                .map(Map.Entry::getValue)
                .findAny();

        if(r.isPresent() &&
                r.get().get().isPresent()){
            //必须运行成功才不会回滚
            return Optional.of(character);
        }
//        if(r.isPresent()){
//            r.get().get();
//            //TODO 必须运行成功才不会回滚
//            return Optional.of(character);
//        }
        reader.unread(i);
        return Optional.empty();
    }

    @Override
    public String toString() {
        return parentStream.toString() +
                "/" + this.getClass().getSimpleName() + "(" + map.size() + ")";
    }
}
