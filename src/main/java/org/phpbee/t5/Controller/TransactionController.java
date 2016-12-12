package org.phpbee.t5.Controller;

import org.phpbee.t5.Repository.TransactionRepository;
import org.phpbee.t5.Entity.Transaction;
import org.phpbee.t5.Resource.TransactionResource;
import org.phpbee.t5.TestBank.FormLists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

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
    public TransactionResource transaction(@PathVariable(value="id") String id) {
        return new TransactionResource(transactionRepository.findById(id));
    }

}
