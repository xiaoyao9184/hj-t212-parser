package com.xy.format.hbt212.core.validator.field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by xiaoyao9184 on 2018/1/10.
 */
public class NValidator implements ConstraintValidator<N, String> {

    private int int_len_max;
    private int fraction_len_max;
    private double min;
    private double max;
//    private String format;
    private boolean optional;

    @Override
    public void initialize(N n) {
        this.int_len_max = n.integer();
        this.fraction_len_max = n.fraction();
        this.min = n.min();
        this.max = n.max();
        this.optional = n.optional();

//        format = String.join("", Collections.nCopies(int_len_max,"#"));
//        if(fraction_len_max > 0){
//            format = format + "." + String.join("", Collections.nCopies(fraction_len_max,"#"));
//        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return optional;
        }
//        return isValidFormat(format, value);

        try{
            BigDecimal decimal = new BigDecimal(value);
            int int_len = getNumLength(decimal.longValue());
            int fraction_len = decimal.scale();
            boolean result = int_len <= int_len_max &&
                    fraction_len <= fraction_len_max;

            if(min > 0 &&
                    decimal.doubleValue() < min){
                return false;
            }
            if(max > 0 &&
                    decimal.doubleValue() > max){
                return false;
            }
            return result;
        }catch (NumberFormatException e){
            return false;
        }
    }

    @Deprecated
    public static boolean isValidFormat(String format, String value) {
        Number number = null;
        try {
            DecimalFormat sdf = new DecimalFormat(format);
            if (value != null){
                number = sdf.parse(value);
                //12345 + #### = 12345 位数不对
                if (!value.equals(sdf.format(number))) {
                    number = null;
                }
            }

        } catch (ParseException ex) {
        }
        return number != null;
    }

    private static int getNumLength(long num){
        num = num>0?num:-num;
        if (num==0) {
            return 1;
        }
        return (int) Math.log10(num)+1;
    }
}
