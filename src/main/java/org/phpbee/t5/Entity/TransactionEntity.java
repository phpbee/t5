package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "created", nullable = false, columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public TransactionEntity() {
    }

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }


//
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Moscow")
//    public Date getCreatedDate() {
//        return created;
//    }

}
