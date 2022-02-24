package com.openpayd.iyildirim.repository;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {
    public static List<Account> accountList = new ArrayList<>();

    public List<Account> findAll(){
        return accountList;
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public Account findById(Long accountId){
        return accountList.stream().filter(account -> account.getId().equals(accountId)).findAny().
                orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }
}
