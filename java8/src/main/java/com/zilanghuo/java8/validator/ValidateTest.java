package com.zilanghuo.java8.validator;

import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Iterator;
import java.util.Set;

/**
 * @author laiwufa
 * @date 2019/4/28 0028 下午 2:58
 */
@Data
public class ValidateTest {

    @NotNull(message = "reason信息不可以为空")
    @Pattern(regexp = "[1-7]{1}", message = "reason的类型值为1-7中的一个类型")
    private String reason;//订单取消原因

    public void validateParams() {
        //调用JSR303验证工具，校验参数
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ValidateTest>> violations = validator.validate(this);
        Iterator<ConstraintViolation<ValidateTest>> iter = violations.iterator();
        if (iter.hasNext()) {
            String errMessage = iter.next().getMessage();
            throw new RuntimeException(errMessage);
        }
    }

    public static void main(String[] args) {
        ValidateTest validateTest = new ValidateTest();
        validateTest.setReason("ere");
        validateTest.validateParams();
    }

}
