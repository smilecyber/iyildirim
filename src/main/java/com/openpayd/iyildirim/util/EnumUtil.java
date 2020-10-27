package com.openpayd.iyildirim.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EnumUtil {

    public static <T extends Enum<T> & IValueEnum, D extends Enum<D> & IDecoratorEnum<T>> D safeDecoratorEnumOf(Class<D> decoratorEnumType, Integer value) {
        D foundDecorator = null;
        if (value != null) {
            D[] enums = decoratorEnumType.getEnumConstants();
            for (D decorator : enums) {
                if (decorator.getActualEnum().getValue().equals(value)) {
                    foundDecorator = decorator;
                    break;
                }
            }
        }
        return foundDecorator;
    }


}
