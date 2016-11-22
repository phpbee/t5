package org.phpbee.t5.Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=TransactionExistsConstraintValidator.class)

public @interface TransactionExistsConstraint {
    String message() default "Transaction doesn't exists";
    Class[] groups() default {};
    Class[] payload() default {};
}
