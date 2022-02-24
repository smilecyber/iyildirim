package com.openpayd.iyildirim.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Transaction {
    private Long sender;
    private Long receiver;
    private BigDecimal amount;
    private String message;
    private Date creationDate;
}
