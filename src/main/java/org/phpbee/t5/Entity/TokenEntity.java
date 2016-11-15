package org.phpbee.t5.Entity;

import org.springframework.data.annotation.Id;

/**
 * Created by alexk on 11/15/16.
 */

public class TokenEntity {
    @Id
    private String id;

    public TokenEntity() {
    }

    public String getId() {
        return id;
    }
}
