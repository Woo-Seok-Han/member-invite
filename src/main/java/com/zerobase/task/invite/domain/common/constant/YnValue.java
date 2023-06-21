package com.zerobase.task.invite.domain.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YnValue implements CodeValue {

    Y("1", "Y"),
    N("0", "N");

    private final String code;
    private final String value;
}
