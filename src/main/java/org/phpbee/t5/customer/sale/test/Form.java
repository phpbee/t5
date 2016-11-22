package org.phpbee.t5.customer.sale.test;

import org.phpbee.t5.Validator.BusinessLogicChecks;
import org.phpbee.t5.Validator.FormatChecks;
import org.phpbee.t5.Validator.TransactionExistsConstraint;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@GroupSequence(value = {Form.class, FormatChecks.class, BusinessLogicChecks.class})
public class Form {
    @NotNull(groups = FormatChecks.class)
    @TransactionExistsConstraint(groups = BusinessLogicChecks.class)
    private String transactionId;

    @NotNull(groups = FormatChecks.class)
    @ValueInListConstraint(groups = BusinessLogicChecks.class, values = {"Approved", "Declined"})
    private String requestedStatus;

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestedStatus() {
        return requestedStatus;
    }

    public void setRequestedStatus(String requestedStatus) {
        this.requestedStatus = requestedStatus;
    }
}
