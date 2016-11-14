package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "created", updatable = false, insertable = false, nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public TransactionEntity() { }

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

//
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Moscow")
//    public Date getCreatedDate() {
//        return created;
//    }

}
