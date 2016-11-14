package org.phpbee.t5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class TransactionController {

    @Autowired

    private TransactionDAO transactionDAO;

    @RequestMapping(value="/transaction", method=RequestMethod.POST)
    public Transaction transaction() {
        Transaction transaction = new Transaction();
        transactionDAO.save(transaction);
        return transaction;
    }

}
