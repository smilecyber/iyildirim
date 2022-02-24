package com.openpayd.iyildirim.repository;

import com.openpayd.iyildirim.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionRepository{
    public static List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findAll(){
        return transactions;
    }
}
