package org.phpbee.t5.customer.sale.test;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=ValueInListConstraintValidator.class)

public @interface ValueInListConstraint {
    String message() default "Value not in list";
    Class[] groups() default {};
    Class[] payload() default {};
    String[] values();
}
