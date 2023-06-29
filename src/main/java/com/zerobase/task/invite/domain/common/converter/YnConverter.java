package com.zerobase.task.invite.domain.common.converter;

import com.zerobase.task.invite.domain.common.constant.YnValue;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class YnConverter extends CodeValueConverter<YnValue> {
    public YnConverter() {
        super(YnValue.class);
    }
}
