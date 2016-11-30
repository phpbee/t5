package org.phpbee.t5.Repository;

import org.phpbee.t5.Entity.Transaction;

/**
 * Created by alexk on 11/14/16.
 */
public interface TransactionRepository extends DocumentRepository<Transaction, String> {
    Transaction findById(String id);
    boolean exists(String id);
}
