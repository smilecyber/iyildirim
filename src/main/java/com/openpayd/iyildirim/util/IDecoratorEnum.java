package com.openpayd.iyildirim.util;

public interface IDecoratorEnum<T extends Enum<T>> {

    T getActualEnum();
}
