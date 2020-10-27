package com.openpayd.iyildirim.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debitAccountId", referencedColumnName = "id", nullable = false)
    private Account debitAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditAccountId", referencedColumnName = "id", nullable = false)
    private Account creditAccount;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
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
}
