package com.openpayd.iyildirim.dto;

import java.util.List;

public class AccountListResponseDTO {
    private List<AccountDTO> accountList;

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }
}
