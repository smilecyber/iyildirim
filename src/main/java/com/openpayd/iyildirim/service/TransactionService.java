package com.openpayd.iyildirim.service;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.entity.Transaction;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(BigDecimal amount, Date creationDate,
                             Account sender, Account receiver,
                             String message) throws RecordNotFoundException,
            BadRequestException;

    List<Transaction> findAll();
}
