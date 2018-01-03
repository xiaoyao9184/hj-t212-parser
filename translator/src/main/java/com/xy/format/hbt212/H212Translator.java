package com.xy.format.hbt212;

import javax.activation.UnsupportedDataTypeException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/12/30.
 */
public enum H212Translator {
    I;

    private static Logger logger = Logger.getLogger(H212Translator.class.getName());

    private Map<Enum,Pattern> compilePattern = Collections.synchronizedMap(new LinkedHashMap<>());

    private Map<Class<? extends Enum>,Object> expandCode = Collections.synchronizedMap(new LinkedHashMap<>());
//    Map<String,CodeMean>
    /**
     * 翻译
     * @param codeType
     * @param code
     * @return
     * @throws UnsupportedDataTypeException
     * @throws ClassNotFoundException
     */
    public String translation(Class<? extends Enum> codeType, String code) throws UnsupportedDataTypeException, ClassNotFoundException {
        if(!expandCode.containsKey(codeType)){
            register(codeType);
        }
        Object codeMeanMap = expandCode.get(codeType);

        if(codeMeanMap instanceof Map){
            Map<String,CodeMean> map = (Map) codeMeanMap;
            return Optional.ofNullable(map.get(code))
                    .orElseThrow(ClassNotFoundException::new)
                    .mean();
        }else if(codeMeanMap instanceof List){
            Set<CodeMatch> set = (Set<CodeMatch>) codeMeanMap;
            return set.stream()
                    .filter(cm -> cm.match(code))
                    .findAny()
                    .orElseThrow(ClassNotFoundException::new)
                    .mean();
        }
        return null;
//
//
//        //noinspection ResultOfMethodCallIgnored
//        Stream.of(ec.getInterfaces())
//                .filter(i -> i.equals(CodeMean.class))
//                .findAny()
//                .orElseThrow(UnsupportedDataTypeException::new);
//
//
//        Optional selfCompiled = Stream.of(ec.getInterfaces())
//                .filter(i -> i.equals(CodeMatch.class))
//                .findAny();
//        if(selfCompiled.isPresent()){
//            return Stream.of(ec.getEnumConstants())
//                    .map(cm -> (CodeMean)cm)
//                    .filter(cm -> ((CodeMatch)cm).match(code))
//                    .findAny()
//                    .orElseThrow(ClassNotFoundException::new)
//                    .mean();
//        }
//
//        Optional needCompile = Stream.of(ec.getInterfaces())
//                .filter(i -> i.equals(CodePattern.class))
//                .findAny();
//        if(needCompile.isPresent()){
//            return Stream.of(ec.getEnumConstants())
//                    .map(this::temp)
//                    .peek(temp -> {
//                        if(compilePattern.containsKey(temp.e)){
//                            temp.pattern = compilePattern.get(temp.e);
//                        }else{
//                            CodePattern cp = (CodePattern) temp.e;
//                            Pattern p = Pattern.compile(cp.pattern());
//                            compilePattern.put(temp.e,p);
//                            temp.pattern = p;
//                        }
//                    })
//                    .filter(temp -> temp.pattern.asPredicate().test(code))
//                    .findAny()
//                    .orElseThrow(ClassNotFoundException::new)
//                    .mean();
//        }
//
//        return Stream.of(ec.getEnumConstants())
//                .map(e -> (CodeMean)e)
//                .filter(cm -> cm.code().equals(code))
//                .findAny()
//                .orElseThrow(ClassNotFoundException::new)
//                .mean();

    }

    /**
     * 注册
     * @param codeType 类型
     * @return 是否成功
     * @throws UnsupportedDataTypeException
     */
    public boolean register(Class<? extends Enum> codeType) throws UnsupportedDataTypeException {
        if(expandCode.containsKey(codeType)){
            throw new UnsupportedOperationException("Repeat registration");
        }

        Optional selfCompiled = Stream.of(codeType.getInterfaces())
                .filter(i -> i.equals(CodeMatch.class))
                .findAny();
        if(selfCompiled.isPresent()){
//            Map<String,CodeMean> map =
            Set<CodeMatch> map = Stream.of(codeType.getEnumConstants())
                    .map(cm -> (CodeMatch)cm)
                    .collect(Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> {
                        int i = o1.order() - o2.order();
                        if (i == 0) {
                            i++;
                        }
                        return i;
                    })));
//                    .collect(Collectors.toMap(CodeMean::code, Function.identity(),
//                            (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); },
//                            () -> {
//                                return new HashMap<>();
//                            }));
            expandCode.put(codeType,map);
            return true;
        }

        Optional needCompile = Stream.of(codeType.getInterfaces())
                .filter(i -> i.equals(CodePattern.class))
                .findAny();
        if(needCompile.isPresent()){
//            Map<String,CodeMean> map = Stream.of(codeType.getEnumConstants())
            Set<CodePattern> map = Stream.of(codeType.getEnumConstants())
                    .map(cm -> (CodePattern)cm)
                    .map(CodePatternMatchAdapter::adaptation)
                    .collect(Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> {
                        int i = o1.order() - o2.order();
                        if (i == 0) {
                            i++;
                        }
                        return i;
                    })));
//                    .collect(Collectors.toMap(CodeMean::code, cp -> new CodeMatch() {
//                        Pattern p = Pattern.compile(cp.pattern());
//
//                        @Override
//                        public boolean match(String code) {
//                            return p.asPredicate().test(code);
//                        }
//
//                        @Override
//                        public int order() {
//                            return -1;
//                        }
//
//                        @Override
//                        public String pattern() {
//                            return cp.pattern();
//                        }
//
//                        @Override
//                        public String code() {
//                            return cp.code();
//                        }
//
//                        @Override
//                        public String mean() {
//                            return cp.mean();
//                        }
//                    }));
            expandCode.put(codeType,map);
            return true;
        }


        //noinspection ResultOfMethodCallIgnored
        Stream.of(codeType.getInterfaces())
                .filter(i -> i.equals(CodeMean.class))
                .findAny()
                .orElseThrow(UnsupportedDataTypeException::new);
        Map<String,CodeMean> map = Stream.of(codeType.getEnumConstants())
                .map(cm -> (CodeMean)cm)
                .collect(Collectors.toMap(CodeMean::code, Function.identity()));
        expandCode.put(codeType,map);
        return true;
    }

    /**
     * 扩展
     * @param codeType 类型
     * @param code 代码
     * @param mean 含义
     * @param predicate 自定义匹配规则
     * @return 是否成功
     */
    public boolean expand(Class<? extends Enum> codeType, String code, String mean, Predicate<String> predicate) throws UnsupportedDataTypeException, ClassNotFoundException {
        if(!expandCode.containsKey(codeType)){
            register(codeType);
        }
        Object codeMeanMap = expandCode.get(codeType);
        String finalCode = code;
        if(codeMeanMap instanceof Map){
            Map<String,CodeMean> map = (Map) codeMeanMap;
            CodeMean cm = new CodeMean() {
                @Override
                public String code() {
                    return finalCode;
                }

                @Override
                public String mean() {
                    return mean;
                }
            };
            map.put(code,cm);
        }else if(codeMeanMap instanceof List){
            Set set = (Set) codeMeanMap;
            CodeMatch cm = new CodeMatch() {
                @Override
                public boolean match(String code) {
                    return predicate.test(code);
                }

                @Override
                public String pattern() {
                    return null;
                }

                @Override
                public int order() {
                    return -1;
                }

                @Override
                public String code() {
                    return finalCode;
                }

                @Override
                public String mean() {
                    return mean;
                }
            };
            set.add(cm);
        }

//
//        Map<String,CodeMean> codeMeanMap = expandCode.get(codeType);
//
//        if(predicate == null){
//            if(codeMeanMap.containsKey(code)){
//                logger.info("Replace old code");
//                codeMeanMap.remove(code);
//            }
//            String finalCode1 = code;
//            CodeMean cm = new CodeMean() {
//                @Override
//                public String code() {
//                    return finalCode1;
//                }
//
//                @Override
//                public String mean() {
//                    return mean;
//                }
//            };
//            codeMeanMap.put(code,cm);
//        }else{
//            while (codeMeanMap.containsKey(code)){
//                logger.info("Rename code key");
//                code = code + "_";
//            }
//            String finalCode = code;
//            CodeMatch cm = new CodeMatch() {
//                @Override
//                public boolean match(String code) {
//                    return predicate.test(code);
//                }
//
//                @Override
//                public int order() {
//                    return -1;
//                }
//
//                @Override
//                public String pattern() {
//                    return null;
//                }
//
//                @Override
//                public String code() {
//                    return finalCode;
//                }
//
//                @Override
//                public String mean() {
//                    return mean;
//                }
//            };
//            codeMeanMap.put(code,cm);
//        }

        return true;
    }


//    public CodeTemp temp(Enum e){
//        CodeTemp codeTemp = new CodeTemp();
//        codeTemp.e = e;
//        return codeTemp;
//    }
//
//    private class CodeTemp {
//        public Enum e;
//        public Pattern pattern;
//
//        public String mean(){
//            return ((CodeMean)e).mean();
//        }
//    }

    private static class CodePatternMatchAdapter implements CodeMatch {

        public static CodePatternMatchAdapter adaptation(CodePattern codePattern){
            CodePatternMatchAdapter adapter = new CodePatternMatchAdapter();
            adapter.codePattern = codePattern;
            adapter.pattern = Pattern.compile(codePattern.pattern());
            return adapter;
        }

        private CodePattern codePattern;
        private Pattern pattern;

        @Override
        public boolean match(String code) {
            return pattern.asPredicate().test(code);
        }

        @Override
        public String pattern() {
            return codePattern.pattern();
        }

        @Override
        public int order() {
            return -1;
        }

        @Override
        public String code() {
            return codePattern.code();
        }

        @Override
        public String mean() {
            return codePattern.mean();
        }
    }

}
