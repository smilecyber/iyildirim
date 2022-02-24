package com.openpayd.iyildirim.entity;

import com.openpayd.iyildirim.enums.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Account {
    private Long id;
    private BigDecimal balance;
    private AccountType accountType;
    private Date creationDate;
    private Long userId;
}
