package com.openpayd.iyildirim.service.impl;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.enums.AccountType;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.repository.AccountRepository;
import com.openpayd.iyildirim.service.AccountService;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAcount(Long accountId, BigDecimal balance, Date creationDate, AccountType accountType, Long userId) throws BadRequestException, RecordNotFoundException {
        try {
            Account account = Account.builder().id(accountId).userId(userId).accountType(accountType)
                    .balance(balance).creationDate(creationDate).build();
            return accountRepository.save(account);
        }catch (Exception e){
            throw new BadRequestException("Request format invalid");
        }
    }
    @Override
    public List<Account> listAllAccount(){
        return accountRepository.findAll();
    }
}
