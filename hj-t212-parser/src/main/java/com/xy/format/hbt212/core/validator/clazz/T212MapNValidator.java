package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.N;
import com.xy.format.hbt212.core.validator.field.NValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapNValidator
        extends FieldValidator<FieldN,T212Map,N,String>
        implements ConstraintValidator<FieldN,T212Map> {

    public T212MapNValidator() {
        super(new NValidator());
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
