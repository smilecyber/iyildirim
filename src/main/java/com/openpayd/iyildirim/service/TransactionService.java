package com.openpayd.iyildirim.service;

import com.openpayd.iyildirim.dto.TransactionDTO;
import com.openpayd.iyildirim.dto.TransactionListResponseDTO;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;

public interface TransactionService {

    TransactionDTO makeTransfer(TransactionDTO transactionDTO) throws RecordNotFoundException, BadRequestException;

    TransactionListResponseDTO listAccountTransactionList(Long accountId) throws RecordNotFoundException;
}
