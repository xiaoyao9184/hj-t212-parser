package com.xy.stream.reader.core;

import com.xy.stream.reader.base.MapEntryStepGenerator;
import com.xy.stream.reader.base.lambda.RunnableWithThrowable;
import com.xy.stream.reader.base.lambda.SupplierWithThrowable;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.xy.stream.reader.core.ReaderMatch.alwaysReturnPresent;

public class MultipleCharMatch<ParentStream extends ReaderStream>
        implements ReaderMatch<MultipleCharMatch<ParentStream>,ParentStream,char[]> {

    private ParentStream parentStream;
    private PushbackReader reader;
    private int count;
    private boolean alwaysRollBack = false;
    private Map<Predicate<char[]>, SupplierWithThrowable<Optional<Object>,IOException>> map;
    private MapEntryStepGenerator<Predicate<char[]>,SupplierWithThrowable<Optional<Object>,IOException>> generator;

    public MultipleCharMatch(ParentStream parentStream, int count) {
        this.parentStream = parentStream;
        this.reader = parentStream.reader();
        this.count = count;
        this.map = new LinkedHashMap<>();
        MapEntryStepGenerator.Builder<Predicate<char[]>,SupplierWithThrowable<Optional<Object>,IOException>> builder = MapEntryStepGenerator.builder();
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
    public MultipleCharMatch<ParentStream> when(Predicate<char[]> predicate) {
        generator.putKey(predicate);
        return this;
    }

    /**
     * 当字符一致时
     * @param characters 字符
     * @return THIS
     */
    public MultipleCharMatch<ParentStream> when(char... characters) {
        generator.putKey((ca) -> Arrays.equals(ca,characters));
        return this;
    }

//    public ReaderStream<MultipleCharMatch<ParentStream>> when() {
//        ReaderStream<MultipleCharMatch<ParentStream>> stream = new ReaderStream<>(
//                this.reader,
//                this.parentStream.bufSize() -1,
//                this);
//        generator.putKey((ca) -> {
//            try {
//                return stream.core().isPresent();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        return stream;
//    }

    public MultipleCharMatch<ParentStream> then(Supplier<IOException> exceptionSupplier) {
        generator.putValue(() -> {
            throw exceptionSupplier.get();
        });
        return this;
    }

    public MultipleCharMatch<ParentStream> then(SupplierWithThrowable<Optional<Object>,IOException> runnable) {
        generator.putValue(runnable);
        return this;
    }

    public MultipleCharMatch<ParentStream> then(RunnableWithThrowable<IOException> runnable) {
        generator.putValue(alwaysReturnPresent(runnable));
        return this;
    }

    public ReaderStream<MultipleCharMatch<ParentStream>> then() {
        ReaderStream<MultipleCharMatch<ParentStream>> reader = new ReaderStream<>(
                this.reader,
                this.parentStream.bufSize() -1,
                this);
        then(reader::match);
        return reader;
    }


    /**
     *
     * @return
     */
    @Override
    public ParentStream done() {
        generator.generate();
        return parentStream;
    }

    /**
     * 中断
     * @return
     */
    public ParentStream stop(){
        return done();
    }

    /**
     * 回滚
     * @return
     */
    public ParentStream back(){
        alwaysRollBack = true;
        return done();
    }

    @Override
    public Optional<char[]> match() throws IOException {
        char[] chars = new char[count];
        //noinspection ResultOfMethodCallIgnored
        reader.read(chars);

        Optional<SupplierWithThrowable<Optional<Object>,IOException>> r = map.entrySet().stream()
                .filter(kv -> kv.getKey().test(chars))
                .map(Map.Entry::getValue)
                .findAny();

        if(r.isPresent()){
            if(r.get().get().isPresent() &&
                    !alwaysRollBack){
                //必须运行成功才不会回滚
                return Optional.of(chars);
            }
        }



//        if(r.isPresent()){
//            r.get().get();
//            return Optional.of(chars);
//        }
        //回滚
        reader.unread(chars);
        return Optional.empty();
    }

    @Override
    public String toString() {
        return parentStream.toString() +
                "/" + this.getClass().getSimpleName() + "(" + map.size() + ")";
    }

}
