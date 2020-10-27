package com.openpayd.iyildirim.util;

public enum AccountTypeView implements ITextEnum, IDecoratorEnum<AccountType> {

    INITIAL(AccountType.CURRENT, "Current"),
    SUCCESS(AccountType.SAVINGS, "Savings");

    private AccountType actualEnum;
    private String text;

    private AccountTypeView(AccountType actualStatus, String text) {
        this.actualEnum = actualStatus;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public AccountType getActualEnum() {
        return actualEnum;
    }
}