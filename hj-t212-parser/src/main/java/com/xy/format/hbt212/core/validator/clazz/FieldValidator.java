package com.xy.format.hbt212.core.validator.clazz;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public abstract class FieldValidator<A extends Annotation,V,AF extends Annotation,FV>
        implements ConstraintValidator<A, V> {

    private String field;
    private AF af;
    private ConstraintValidator<AF,FV> constraintValidator;

    public FieldValidator(ConstraintValidator<AF,FV> constraintValidator){
        this.constraintValidator = constraintValidator;
    }

    @Override
    public void initialize(A a) {
        this.field = getField(a);
        this.af = getAnnotation(a);
        constraintValidator.initialize(af);
    }

    @Override
    public boolean isValid(V value, ConstraintValidatorContext constraintValidatorContext) {
        FV fv = getFieldValue(value,field);
        boolean result = constraintValidator.isValid(fv,constraintValidatorContext);
        if(!result){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(getFieldMessage(af))
                    .addPropertyNode(field).addConstraintViolation();
        }
        return result;
    }

    public abstract String getField(A a);
    public abstract AF getAnnotation(A a);
    public abstract FV getFieldValue(V value,String field);
    public abstract String getFieldMessage(AF value);
}
