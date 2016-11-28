package org.phpbee.t5.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Document
public class Sale {

    @Id
    private UUID id = UUID.randomUUID();

    public UUID getId() {
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

    private String requestedStatus = "";

    public String getRequestedStatus() {
        return requestedStatus;
    }

    public void setRequestedStatus(String requestedStatus) {
        this.requestedStatus = requestedStatus;
    }


}
