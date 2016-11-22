package org.phpbee.t5.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by alexk on 11/22/16.
 */
public class ValueInListConstraintValidator implements ConstraintValidator<ValueInListConstraint, String> {

    @Autowired
    ValueInListConstraintValidatorLists valueInListConstraintValidatorLists;

    ValueInListConstraint constraintAnnotation;

    @Override
    public void initialize(ValueInListConstraint constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }


    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        try {
            return Arrays.asList(valueInListConstraintValidatorLists.get(constraintAnnotation.name())).contains(id);
        } catch (ValueInListConstraintException e) {
            e.printStackTrace();
            return false;
        }
    }

}