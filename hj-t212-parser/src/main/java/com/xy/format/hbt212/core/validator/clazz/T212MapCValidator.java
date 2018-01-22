package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.C;
import com.xy.format.hbt212.core.validator.field.CValidator;
import com.xy.format.hbt212.model.verify.T212Map;

import javax.validation.ConstraintValidator;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class T212MapCValidator
        extends T212MapFieldValidator<FieldC,C>
        implements ConstraintValidator<FieldC,T212Map<String,?>> {

    public T212MapCValidator() {
        super(new CValidator());
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
    public boolean isFieldRegex(FieldC fieldC) {
        return fieldC.regex();
    }

    @Override
    public String getFieldMessage(C value) {
        return value.message();
    }

}
