package org.phpbee.t5.Repository;

import org.phpbee.t5.Entity.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by alexk on 11/15/16.
 */
public interface TokenRepository extends DocumentRepository<TokenEntity, String> {
    TokenEntity findById(String id);
}
