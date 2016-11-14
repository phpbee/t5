package org.phpbee.t5.Repository;

import org.phpbee.t5.Entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexk on 11/14/16.
 */
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    TransactionEntity findById(Long id);
}
