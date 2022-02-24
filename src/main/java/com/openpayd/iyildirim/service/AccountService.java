package com.openpayd.iyildirim.service;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.enums.AccountType;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {
    Account createNewAcount(Long accountId, BigDecimal balance, Date creationDate,
                             AccountType accountType, Long userId) throws BadRequestException, RecordNotFoundException;

    List<Account> listAllAccount();
}
