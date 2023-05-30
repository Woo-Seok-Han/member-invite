package com.zerobase.task.invite.persistence.converter;

import com.zerobase.task.invite.persistence.enums.InviteStatus;
import com.zerobase.task.invite.persistence.enums.MemberStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MemberStatusConverter extends CodeValueConverter<MemberStatus> {
    MemberStatusConverter() {
        super(MemberStatus.class);
    }
}
