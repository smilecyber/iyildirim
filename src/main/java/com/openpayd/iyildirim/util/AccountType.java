package com.openpayd.iyildirim.util;


public enum AccountType implements IValueEnum {

    CURRENT(1),
    SAVINGS(2);

    private Integer value;

    AccountType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}