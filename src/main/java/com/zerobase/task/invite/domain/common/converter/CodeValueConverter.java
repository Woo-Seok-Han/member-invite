package com.zerobase.task.invite.domain.common.converter;

import com.zerobase.task.invite.domain.common.constant.CodeValue;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class CodeValueConverter<E extends Enum<E> & CodeValue> implements
    AttributeConverter<E, String> {

    private Class<E> clz;

    CodeValueConverter(Class<E> enumClass) {
        this.clz = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute.getCode();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(clz).stream().filter(e -> e.getCode().equals(dbData)).findAny()
            .orElseThrow(() -> new NoSuchElementException("해당 되는 코드, 코드명 값이 존재하지 않습니다."));
    }
}
