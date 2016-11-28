package org.phpbee.t5.customer.sale.test;

import org.hibernate.validator.constraints.URL;
import org.phpbee.t5.Validator.BusinessLogicChecks;
import org.phpbee.t5.Validator.FormatChecks;
import org.phpbee.t5.Validator.TransactionExistsConstraint;
import org.phpbee.t5.Validator.ValueInListConstraint;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

@GroupSequence(value = {Form.class, FormatChecks.class, BusinessLogicChecks.class})
public class Form {
    @NotNull(groups = FormatChecks.class)
    @TransactionExistsConstraint(groups = BusinessLogicChecks.class)
    private String transactionId;

    @NotNull(groups = FormatChecks.class)
    @URL(groups = FormatChecks.class)
    private String returnURL;

    @NotNull(groups = FormatChecks.class)
    @ValueInListConstraint(groups = BusinessLogicChecks.class, name = "RequestedStatuses")
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

    public String getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }
}
