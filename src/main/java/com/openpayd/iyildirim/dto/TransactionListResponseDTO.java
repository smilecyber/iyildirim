package com.openpayd.iyildirim.dto;

import java.util.List;

public class TransactionListResponseDTO {
    private List<TransactionDTO> transactionList;

    public List<TransactionDTO> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionDTO> transactionList) {
        this.transactionList = transactionList;
    }
}
