package com.openpayd.iyildirim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openpayd.iyildirim.entity.Client;

import javax.persistence.*;
import java.util.Date;

public class TransactionDTO {
    @JsonIgnore
    private Long id;

    private Long debitAccountId;
    private Long creditAccountId;
    private double amount;
    private String message;
    private Date creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(Long creditAccountId) {
        this.creditAccountId = creditAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(Long debitAccountId) {
        this.debitAccountId = debitAccountId;
    }
}
