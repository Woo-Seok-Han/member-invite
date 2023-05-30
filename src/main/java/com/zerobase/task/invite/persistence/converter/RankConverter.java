package com.zerobase.task.invite.persistence.converter;

import com.zerobase.task.invite.persistence.enums.Rank;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RankConverter extends CodeValueConverter<Rank>{
    public RankConverter() {
        super(Rank.class);
    }
}
