package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.core.validator.field.ValidDateValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapValidDateValidator
        extends FieldValidator<FieldValidDate,T212Map,ValidDate,String>
        implements ConstraintValidator<FieldValidDate,T212Map> {

    private Predicate<String> predicate;

    public T212MapValidDateValidator() {
        super(new ValidDateValidator());
    }

    @Override
    public void initialize(FieldValidDate fieldValidDate) {
        super.initialize(fieldValidDate);
        if(fieldValidDate.regex()){
            predicate = Pattern.compile(field).asPredicate();
        }
    }

    @Override
    public String getField(FieldValidDate field) {
        return field.field();
    }

    @Override
    public ValidDate getAnnotation(FieldValidDate field) {
        return field.value();
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
    public String getFieldMessage(ValidDate value) {
        return value.message();
    }
}
