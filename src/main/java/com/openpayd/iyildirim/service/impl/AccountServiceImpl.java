package com.openpayd.iyildirim.service.impl;

import com.openpayd.iyildirim.dto.AccountDTO;
import com.openpayd.iyildirim.dto.AccountListResponseDTO;
import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.entity.Client;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.repository.AccountRepository;
import com.openpayd.iyildirim.repository.ClientRepository;
import com.openpayd.iyildirim.service.AccountService;
import com.openpayd.iyildirim.service.ClientService;
import com.openpayd.iyildirim.util.AccountType;
import com.openpayd.iyildirim.util.AccountTypeView;
import com.openpayd.iyildirim.util.BalanceStatusView;
import com.openpayd.iyildirim.util.EnumUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(ClientRepository clientRepository, ClientService clientService, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createNewAcoount(AccountDTO accountDTO) throws BadRequestException, RecordNotFoundException {
        Client client = clientRepository.findById(accountDTO.getClientId()).orElseThrow(() -> new RecordNotFoundException("Record not found"));
        try {
            Account account = new Account();
            account.setBalance(accountDTO.getBalance());
            account.setBalanceStatus(accountDTO.getBalanceStatus());
            account.setClient(client);
            account.setCreationDate(new Date());
            account.setType(accountDTO.getType());
            accountRepository.save(account);
            return convertDtoFromEntity(account);
        }catch (Exception e){
            throw new BadRequestException("Request format invalid");
        }
    }
    @Override
    public AccountListResponseDTO listAllAccount(){
        AccountListResponseDTO accountListResponseDTO = new AccountListResponseDTO();
        List<Account> accountList = accountRepository.findAll();
        List<AccountDTO> clientDTOS = new ArrayList<>();
        accountList.forEach(client -> clientDTOS.add(convertDtoFromEntity(client)));
        accountListResponseDTO.setAccountList(clientDTOS);
        return accountListResponseDTO;
    }

    public AccountDTO convertDtoFromEntity(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setType(account.getType());
        accountDTO.setBalanceStatus(account.getBalanceStatus()) ;
        accountDTO.setClientDTO(clientService.convertEntityToDTO(account.getClient()));
        accountDTO.setBalanceStatusText(EnumUtil.safeDecoratorEnumOf(BalanceStatusView.class, account.getBalanceStatus()).getText());
        accountDTO.setTypeText(EnumUtil.safeDecoratorEnumOf(AccountTypeView.class, account.getType()).getText());
        accountDTO.setCreationDate(account.getCreationDate());
        return accountDTO;
    }
}
