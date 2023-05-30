package com.zerobase.task.invite.persistence.converter;

import com.zerobase.task.invite.persistence.enums.InviteStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class InviteStatusConverter extends CodeValueConverter<InviteStatus> {
    public InviteStatusConverter() {
        super(InviteStatus.class);
    }
}
