package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.NValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapNValidator
        extends FieldValidator<FieldN,T212Map,N,String>
        implements ConstraintValidator<FieldN,T212Map> {

    private Predicate<String> predicate;

    public T212MapNValidator() {
        super(new NValidator());
    }

    @Override
    public void initialize(FieldN fieldN) {
        super.initialize(fieldN);
        if(fieldN.regex()){
            predicate = Pattern.compile(field).asPredicate();
        }
    }

    @Override
    public String getField(FieldN fieldN) {
        return fieldN.field();
    }

    @Override
    public N getAnnotation(FieldN fieldN) {
        return fieldN.value();
    }

    @Override
    public String getFieldValue(T212Map value, String field) {
        if(predicate != null){
            Optional<String> optional = value.keySet()
                    .stream()
                    .filter(key -> predicate.test(key.toString()))
                    .findFirst();
            if(optional.isPresent()){
                return (String) value.get(optional.get());
            }
        }
        if(!value.containsKey(field)){
            return null;
        }
        return (String) value.get(field);
    }

    @Override
    public String getFieldMessage(N value) {
        return value.message();
    }
}
