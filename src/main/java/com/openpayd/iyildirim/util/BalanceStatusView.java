package com.openpayd.iyildirim.util;

public enum BalanceStatusView implements ITextEnum, IDecoratorEnum<BalanceStatus> {

    INITIAL(BalanceStatus.CREDIT, "Credit"),
    SUCCESS(BalanceStatus.DEBIT, "Debit");

    private BalanceStatus actualEnum;
    private String text;

    private BalanceStatusView(BalanceStatus actualStatus, String text) {
        this.actualEnum = actualStatus;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public BalanceStatus getActualEnum() {
        return actualEnum;
    }
}