package org.phpbee.t5;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "created", updatable = false, insertable = false, nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;


    public Transaction () { }

    public Transaction(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
