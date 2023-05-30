package com.zerobase.task.invite.persistence.converter;

import com.zerobase.task.invite.persistence.enums.YnValue;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class YnConverter extends CodeValueConverter<YnValue> {
    public YnConverter() {
        super(YnValue.class);
    }
}
