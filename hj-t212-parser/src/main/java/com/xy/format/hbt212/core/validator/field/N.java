package com.xy.format.hbt212.core.validator.field;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 数值字符串
 * Created by xiaoyao9184 on 2018/1/10.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(N.List.class)
@Constraint(validatedBy = NValidator.class)
public @interface N {

    /**
     * @return 整数最大位数
     */
    int integer();

    /**
     * @return 小数最大位数
     */
    int fraction() default 0;

    /**
     * @return 最小值
     */
    double min() default -1;

    /**
     * @return 最大值
     */
    double max() default -1;

    String message() default "invalid N type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean optional() default true;


    /**
     * Defines several {@link N} annotations on the same element.
     *
     * @see N
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        N[] value();
    }
}
