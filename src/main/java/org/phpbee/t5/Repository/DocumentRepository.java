package org.phpbee.t5.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by alexk on 11/15/16.
 */
@NoRepositoryBean
abstract interface DocumentRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
}
