package com.xy.format.hbt212.core.validator.clazz;

import com.xy.format.hbt212.core.validator.field.N;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by xiaoyao9184 on 2018/1/11.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(FieldN.List.class)
@Constraint(validatedBy = T212MapNValidator.class)
public @interface FieldN {

    String field() default "";

    N value();

    String message() default "invalid N type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean optional() default false;

    boolean regex() default false;

    /**
     * Defines several {@link FieldN} annotations on the same element.
     *
     * @see FieldN
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        FieldN[] value();
    }
}
