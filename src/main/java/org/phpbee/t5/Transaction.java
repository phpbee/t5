package org.phpbee.t5;

import javax.persistence.*;

@Entity
@Table( name = "transactions" )
@NamedQueries({
        @NamedQuery(name = "Transaction.findById", query = "select id from Transaction where id = 5")
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected Transaction() {
    }

    public Transaction(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
