package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "created", nullable = false)
    private Long created = new Date().getTime();

    public String getId() {
        return id;
    }

    public TransactionEntity() {
    }

    public Long getCreated() {
        return created;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Moscow")
    public Date getCreatedDate() {
        return new Date(created);
    }

}
