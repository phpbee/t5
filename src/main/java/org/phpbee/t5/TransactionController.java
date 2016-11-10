package org.phpbee.t5;

import org.phpbee.t5.dao.TransactionDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class TransactionController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/transaction", method=RequestMethod.POST)
    public Transaction transaction() {
        return new Transaction(counter.incrementAndGet());
    }

    @RequestMapping(value="/transaction/{id}", method=RequestMethod.GET)
    public Transaction transaction(TransactionDao transactionDao, @PathVariable("id") Long id) {
        return transactionDao.findById(id);
    }

}
