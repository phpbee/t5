package org.phpbee.t5.Validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=ValueInListConstraintValidator.class)

public @interface ValueInListConstraint {
    String message() default "Value not in list";
    Class[] groups() default {};
    Class[] payload() default {};
    String name();
}
