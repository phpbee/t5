package org.phpbee.t5.Controller;

import org.phpbee.t5.Entity.AbstractSale;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SaleController {
    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/transaction/{transactionId}/sale/{saleId}", method = RequestMethod.GET)
    public HttpEntity<AbstractSale> findById(@PathVariable(value = "transactionId") String transactionId, @PathVariable(value = "saleId") String saleId) {
        Transaction transaction = transactionRepository.findById(transactionId);
        AbstractSale sale = transaction.findSaleById(saleId);

        sale.add(linkTo(methodOn(SaleController.class).findById(transactionId, saleId)).withSelfRel());
        sale.add(linkTo(methodOn(TransactionController.class).transaction(transactionId)).withRel("transaction"));

        return new ResponseEntity<>(sale, HttpStatus.OK);
    }


    @RequestMapping(value = "/transaction/{id}/sales", method = RequestMethod.GET)
    public HttpEntity<HashMap<String, AbstractSale>> getSales(@PathVariable(value = "id") String id) {
        HashMap<String, AbstractSale> sales = transactionRepository.findById(id).getSales();

        for (AbstractSale sale : sales.values()) {
            sale.add(linkTo(methodOn(SaleController.class).findById(id, sale.getSaleId().toString())).withSelfRel());
        }


        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

}
