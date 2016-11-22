package org.phpbee.t5.customer.sale.test;

import org.phpbee.t5.Validator.ValueInListConstraintException;
import org.phpbee.t5.Validator.ValueInListConstraintValidatorLists;

/**
 * Created by alexk on 11/22/16.
 */
public class FormLists implements ValueInListConstraintValidatorLists {
    public String[] get(String name) throws ValueInListConstraintException {
        switch(name) {
            case "RequestedStatuses":
                return new String[] {"Approved", "Declined"};
        }
        throw new ValueInListConstraintException("Requested list not found");
    }
}
