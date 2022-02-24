package com.openpayd.iyildirim;

import com.openpayd.iyildirim.entity.Account;
import com.openpayd.iyildirim.enums.AccountType;
import com.openpayd.iyildirim.service.AccountService;
import com.openpayd.iyildirim.service.TransactionService;
import com.openpayd.iyildirim.service.impl.AccountServiceImpl;
import com.openpayd.iyildirim.service.impl.TransactionServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		AccountService accountService = applicationContext.getBean(AccountServiceImpl.class);
		TransactionService transactionService = applicationContext.getBean(TransactionServiceImpl.class);
		Account receiver = accountService.createNewAcount(123L, BigDecimal.TEN, new Date(), AccountType.CHECKING, 1L);
		Account sender =  accountService.createNewAcount(234L, new BigDecimal(100), new Date(), AccountType.CHECKING, 2L);
		accountService.listAllAccount().forEach(System.out::println);
		transactionService.makeTransfer(BigDecimal.TEN, new Date(), sender, receiver, "transfer no:1");
		System.out.println(transactionService.findAll().get(0));
		accountService.listAllAccount().forEach(System.out::println);
		transactionService.makeTransfer(new BigDecimal(25), new Date(), sender, receiver, "transfer no:2");
		System.out.println(transactionService.findAll().get(1));
		accountService.listAllAccount().forEach(System.out::println);
	}
}
