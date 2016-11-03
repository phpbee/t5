package org.phpbee.t5;

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
}
