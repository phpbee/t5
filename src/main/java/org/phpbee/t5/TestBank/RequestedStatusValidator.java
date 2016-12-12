package org.phpbee.t5.TestBank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by alexk on 11/22/16.
 */
public class RequestedStatusValidator implements ConstraintValidator<RequestedStatusConstraint, String> {
//
//    RequestedStatusConstraint constraintAnnotation;
//
//    @Override
//    public void initialize(RequestedStatusConstraint constraintAnnotation) {
//        this.constraintAnnotation = constraintAnnotation;
//    }


    @Override
    public void initialize(RequestedStatusConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        return RequestedStatus.values().contains(id);
    }

}