package org.phpbee.t5;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexk on 11/14/16.
 */
public interface TransactionDAO extends CrudRepository<Transaction, Long> {
    public Transaction findById(Long id);
}
