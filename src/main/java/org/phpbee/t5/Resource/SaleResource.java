package org.phpbee.t5.Resource;

import org.phpbee.t5.Controller.SaleController;
import org.phpbee.t5.Controller.TransactionController;
import org.phpbee.t5.Entity.Sale;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Relation(collectionRelation = "sales")
public class SaleResource extends ResourceSupport {
    private Sale sale;

    public SaleResource(Sale sale, String transactionId) {
        this.sale = sale;

        this.add(linkTo(methodOn(SaleController.class).findById(transactionId, sale.getId())).withSelfRel());
        this.add(linkTo(methodOn(TransactionController.class).transaction(transactionId)).withRel("transaction"));


    }

    public Sale getSale() {
        return sale;
    }
}
