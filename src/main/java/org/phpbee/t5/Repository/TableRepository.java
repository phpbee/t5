package org.phpbee.t5.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by alexk on 11/15/16.
 */
@NoRepositoryBean
interface TableRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
}
