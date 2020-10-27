package com.openpayd.iyildirim.service.impl;

import com.openpayd.iyildirim.dto.TransactionDTO;
import com.openpayd.iyildirim.dto.TransactionListResponseDTO;
import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.entity.Transaction;
import com.openpayd.iyildirim.exception.BadRequestException;
import com.openpayd.iyildirim.exception.RecordNotFoundException;
import com.openpayd.iyildirim.repository.AccountRepository;
import com.openpayd.iyildirim.repository.TransactionRepository;
import com.openpayd.iyildirim.service.TransactionService;
import com.openpayd.iyildirim.util.BalanceStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO makeTransfer(TransactionDTO transactionDTO) throws RecordNotFoundException, BadRequestException {
        if (transactionDTO.getDebitAccountId() == null || transactionDTO.getCreditAccountId() == null){
            throw new BadRequestException("Debit account or credit account can not be null");
        }
        if (transactionDTO.getDebitAccountId().equals(transactionDTO.getCreditAccountId())){
            throw new BadRequestException("Debit account needs to be different Credit Account");
        }

        validateAccounts(transactionDTO.getDebitAccountId(),transactionDTO.getCreditAccountId());
        Account debitAccount = findAccountById(transactionDTO.getDebitAccountId());
        Account creditAccount = findAccountById(transactionDTO.getCreditAccountId());
        executeBalanceAndStatusAndUpdateIfRequired(transactionDTO.getAmount(), debitAccount);
        executeBalanceAndStatusAndUpdateIfRequired(transactionDTO.getAmount(), creditAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCreationDate(new Date());
        transaction.setCreditAccount(creditAccount);
        transaction.setDebitAccount(debitAccount);
        transaction.setMessage(transactionDTO.getMessage());
        transactionRepository.save(transaction);
        return convertDtoFromEntity(transaction);
    }

    @Override
    public TransactionListResponseDTO listAccountTransactionList(Long accountId) throws RecordNotFoundException {

        findAccountById(accountId);
        List<Transaction> transactionList = transactionRepository.findTransactionByCreditAccountId(accountId);
        transactionList.addAll(transactionRepository.findTransactionByDebitAccountId(accountId));
        TransactionListResponseDTO transactionListResponseDTO = new TransactionListResponseDTO();

        List<TransactionDTO> transactionDTOList = new ArrayList<>();
        transactionList.forEach(transaction -> transactionDTOList.add(convertDtoFromEntity(transaction)));
        transactionListResponseDTO.setTransactionList(transactionDTOList);
        return transactionListResponseDTO;

    }

    public void executeBalanceAndStatusAndUpdateIfRequired(double amount, Account account){
        if (account.getBalanceStatus().equals(BalanceStatus.CREDIT.getValue())){
            account.setBalance(account.getBalance() + amount);
        }else {
            if (account.getBalance() - amount > 0){
                account.setBalance(account.getBalance() - amount);
            }else {
                account.setBalance(Math.abs(account.getBalance() - amount));
                account.setBalanceStatus(BalanceStatus.CREDIT.getValue());
            }
        }
        accountRepository.save(account);

    }

    public void validateAccounts(Long debitAccountId, Long creditAccountId ) throws RecordNotFoundException {
        findAccountById(debitAccountId);
        findAccountById(creditAccountId);
    }

    public Account findAccountById(Long accountId) throws RecordNotFoundException {
        return accountRepository.findById(accountId).orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    public TransactionDTO convertDtoFromEntity(Transaction transaction){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setCreationDate(transaction.getCreationDate());
        transactionDTO.setCreditAccountId(transaction.getCreditAccount().getId());
        transactionDTO.setDebitAccountId(transaction.getDebitAccount().getId());
        transactionDTO.setMessage(transaction.getMessage());
        return transactionDTO;
    }
}
