package com.openpayd.iyildirim.util;


public enum BalanceStatus implements IValueEnum {

    CREDIT(1),
    DEBIT(2);

    private Integer value;

    BalanceStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}