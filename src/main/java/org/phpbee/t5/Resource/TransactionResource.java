package org.phpbee.t5.Resource;

import org.phpbee.t5.Controller.SaleController;
import org.phpbee.t5.Controller.TransactionController;
import org.phpbee.t5.Entity.AbstractSale;
import org.phpbee.t5.Entity.Transaction;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by alexk on 12/5/16.
 */
public class TransactionResource extends ResourceSupport {
    private Transaction transaction;

    public TransactionResource(Transaction transaction) {
        this.transaction = transaction;


        this.add(linkTo(methodOn(TransactionController.class).transaction(transaction.getId())).withSelfRel());
        this.add(linkTo(methodOn(SaleController.class).getSales(transaction.getId())).withRel("sales"));


        for (AbstractSale sale : transaction.getSales().values()) {
            this.add(linkTo(methodOn(SaleController.class).findById(transaction.getId(), sale.getId().toString())).withRel("sale"));
        }


    }

    public Transaction getTransaction() {
        return transaction;
    }
}
