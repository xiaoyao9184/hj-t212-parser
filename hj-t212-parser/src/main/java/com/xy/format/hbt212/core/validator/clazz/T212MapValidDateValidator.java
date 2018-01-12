package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.ValidDate;
import com.xy.format.hbt212.core.validator.field.ValidDateValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapValidDateValidator
        extends FieldValidator<FieldValidDate,T212Map,ValidDate,String>
        implements ConstraintValidator<FieldValidDate,T212Map> {

    public T212MapValidDateValidator() {
        super(new ValidDateValidator());
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
