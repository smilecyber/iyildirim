package com.openpayd.iyildirim.service.impl;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.entity.Transaction;
import com.openpayd.iyildirim.enums.AccountType;
import com.openpayd.iyildirim.exception.*;
import com.openpayd.iyildirim.repository.AccountRepository;
import com.openpayd.iyildirim.repository.TransactionRepository;
import com.openpayd.iyildirim.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(BigDecimal amount, Date creationDate, Account sender, Account receiver, String message) throws RecordNotFoundException, BadRequestException {

        if (!underConstruction) {
            if (checkAccountOwnerShip(sender, receiver)) {
                throw new AccountOwnerShipException("UserId is invalid");
            }
            validateAccounts(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
            return transactionRepository.save(
                    Transaction.builder().
                            amount(amount).
                            creationDate(creationDate).
                            sender(sender.getId()).
                            receiver(receiver.getId()).
                            message(message).
                            build());

        }
        throw new UnderConstructionException("Make transfer is disabled for a while. Please try again later.");
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if (checkSenderBalance(sender, amount)) {
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        } else {
            throw new BalanceNotSufficientException("Balance not sufficient. Please make deposit.");
        }
    }

    public boolean checkAccountOwnerShip(Account sender, Account receiver) {
        if (sender.getAccountType().equals(AccountType.SAVINGS) || receiver.getAccountType().equals(AccountType.SAVINGS)) {
            return !sender.getUserId().equals(receiver.getUserId());
        } else {
            return sender.getUserId().equals(receiver.getUserId());
        }
    }

    public boolean checkSenderBalance(Account senderAccount, BigDecimal transferAmount) {
        return senderAccount.getBalance().subtract(transferAmount).compareTo(BigDecimal.ZERO) > 0;
    }


    public void validateAccounts(Account sender, Account receiver) throws RecordNotFoundException {
        if (sender == null || receiver == null) {
            throw new BadRequestException("Debit account or credit account can not be null");
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new BadRequestException("Debit account needs to be different Credit Account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
    }

    public Account findAccountById(Long accountId) throws RecordNotFoundException {
        return accountRepository.findById(accountId);
    }
}
