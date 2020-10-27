package com.openpayd.iyildirim.service;

import com.openpayd.iyildirim.dto.AccountDTO;
import com.openpayd.iyildirim.dto.AccountListResponseDTO;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.exception.BadRequestException;

public interface AccountService {
    AccountDTO createNewAcoount(AccountDTO accountDTO) throws BadRequestException, RecordNotFoundException;

    AccountListResponseDTO listAllAccount();
}
