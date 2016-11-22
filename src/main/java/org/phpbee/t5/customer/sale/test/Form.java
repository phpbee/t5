package org.phpbee.t5.customer.sale.test;

import org.phpbee.t5.Validator.BusinessLogicChecks;
import org.phpbee.t5.Validator.FormatChecks;
import org.phpbee.t5.Validator.TransactionExistsConstraint;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

@GroupSequence(value = {Form.class, FormatChecks.class, BusinessLogicChecks.class})
public class Form {
    @NotNull(groups = FormatChecks.class)
    @TransactionExistsConstraint(groups = BusinessLogicChecks.class)
    private String transactionId;

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


}
