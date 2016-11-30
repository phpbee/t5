package org.phpbee.t5.Controller;

import org.phpbee.t5.Entity.AbstractSale;
import org.phpbee.t5.Repository.TransactionRepository;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.customer.sale.test.FormLists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class TransactionController {

    @Bean
    public FormLists requestedStatuses() {
        return new FormLists();
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @RequestMapping(value="/transaction", method=RequestMethod.POST)
    public ResponseEntity create() {
        Transaction transaction = new Transaction();
        transactionRepository.save(transaction);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/transaction/{id}", method=RequestMethod.GET)
    public HttpEntity<Transaction> transaction(@PathVariable(value="id") String id) {

        Transaction transaction = transactionRepository.findById(id);
        transaction.add(linkTo(methodOn(TransactionController.class).transaction(id)).withSelfRel());
        transaction.add(linkTo(methodOn(SaleController.class).getSales(id)).withRel("sales"));


        for (AbstractSale sale : transaction.getSales().values()) {
            transaction.add(linkTo(methodOn(SaleController.class).findById(id, sale.getSaleId().toString())).withRel("sale"));
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

}
