package org.phpbee.t5.TestBank;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=RequestedStatusValidator.class)

public @interface RequestedStatusConstraint {
    String message() default "Value not in list";
    Class[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
