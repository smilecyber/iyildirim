package com.openpayd.iyildirim.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openpayd.iyildirim.entity.Client;

import javax.persistence.*;
import java.util.Date;

public class AccountDTO {
    private Long id;
    private int type;
    private String typeText;
    private Integer balanceStatus;
    private String balanceStatusText;
    private double balance;
    private Date creationDate;
    private ClientDTO clientDTO;
    @JsonIgnore
    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(Integer balanceStatus) {
        this.balanceStatus = balanceStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getBalanceStatusText() {
        return balanceStatusText;
    }

    public void setBalanceStatusText(String balanceStatusText) {
        this.balanceStatusText = balanceStatusText;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
