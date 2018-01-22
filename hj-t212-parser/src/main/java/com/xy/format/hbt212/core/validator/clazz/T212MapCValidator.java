package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.CValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapCValidator
        extends FieldValidator<FieldC,T212Map,C,String>
        implements ConstraintValidator<FieldC,T212Map> {

    private Predicate<String> predicate;

    public T212MapCValidator() {
        super(new CValidator());
    }

    @Override
    public void initialize(FieldC fieldC) {
        super.initialize(fieldC);
        if(fieldC.regex()){
            predicate = Pattern.compile(field).asPredicate();
        }
    }

    @Override
    public String getField(FieldC fieldC) {
        return fieldC.field();
    }

    @Override
    public C getAnnotation(FieldC fieldC) {
        return fieldC.value();
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
    public String getFieldMessage(C value) {
        return value.message();
    }

}
