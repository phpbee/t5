package org.phpbee.t5.Validator;

import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by alexk on 11/22/16.
 */
public class TransactionExistsConstraintValidator implements ConstraintValidator<TransactionExistsConstraint, String> {


    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void initialize(TransactionExistsConstraint constraintAnnotation) {

    }


    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        return transactionRepository.exists(id);
    }


}