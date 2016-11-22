package org.phpbee.t5.customer.sale.test;

import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Created by alexk on 11/22/16.
 */
public class ValueInListConstraintValidator implements ConstraintValidator<ValueInListConstraint, String> {

    ValueInListConstraint constraintAnnotation;

    @Override
    public void initialize(ValueInListConstraint constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;

    }


    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        return Arrays.asList(constraintAnnotation.values()).contains(id);
    }

}