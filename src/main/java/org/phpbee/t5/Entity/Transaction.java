package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.*;

@Document(collection = "transaction")
public class Transaction extends ResourceSupport {

    public Transaction() {
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    public String getTransactionId() {
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

    private HashMap<String, AbstractSale> sales = new HashMap<>();

    @JsonIgnore
    public HashMap<String, AbstractSale> getSales() {
        return sales;
    }

    public void addSale(AbstractSale sale) {
        sales.put(sale.getSaleId().toString(), sale);
    }

    public AbstractSale findSaleById(String saleId) {
        return sales.get(saleId);
    }
}
