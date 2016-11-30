package org.phpbee.t5.customer.sale.test;


import org.phpbee.t5.Entity.AbstractSale;

import java.net.URI;

public class TestBankSale extends AbstractSale {

    public TestBankSale(String authorizationClass) {
        super(authorizationClass);
    }

    @Override
    public boolean isProcessed() {
        return false;
    }

    @Override
    public boolean isApproved() {
        return requestedStatus.equals("Approved");
    }

    private String requestedStatus = "";

    public void setRequestedStatus(String requestedStatus) {
        this.requestedStatus = requestedStatus;
    }

    private URI returnURL;

    URI getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(URI returnURL) {
        this.returnURL = returnURL;
    }

}
