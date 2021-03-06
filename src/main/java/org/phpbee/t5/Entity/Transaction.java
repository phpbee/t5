package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "transaction")
public class Transaction {

    public Transaction() {
    }

    private String id;

    public String getId() {
        return id;
    }

    private Long created = new Date().getTime();

    public Long getCreated() {
        return created;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreatedDate() {
        return new Date(created);
    }

    private HashMap<String, Sale> sales = new HashMap<>();

    @JsonIgnore
    public HashMap<String, Sale> getSales() {
        return sales;
    }

    public void addSale(Sale sale) {
        sales.put(sale.getId().toString(), sale);
    }

    public Sale findSaleById(String saleId) {
        return sales.get(saleId);
    }
}
