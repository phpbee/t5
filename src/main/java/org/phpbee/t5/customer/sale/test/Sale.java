package org.phpbee.t5.customer.sale.test;


import java.net.URI;

public class Sale extends org.phpbee.t5.Entity.Sale {

    public Sale(String authorizationClass) {
        super(authorizationClass);
    }

    private String requestedStatus = "";

    public String getRequestedStatus() {
        return requestedStatus;
    }

    public void setRequestedStatus(String requestedStatus) {
        this.requestedStatus = requestedStatus;
    }

    private URI returnURL;

    public URI getReturnURL() {
        return returnURL;
    }

    public void setReturnURL(URI returnURL) {
        this.returnURL = returnURL;
    }

}
