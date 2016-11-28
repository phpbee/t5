package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Document(collection = "transaction")
public class TransactionEntity {

    public TransactionEntity() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    @Column(name = "created", nullable = false)
    private Long created = new Date().getTime();

    public Long getCreated() {
        return created;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreatedDate() {
        return new Date(created);
    }

    private ArrayList<Sale> sales = new ArrayList<Sale>();

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

}
