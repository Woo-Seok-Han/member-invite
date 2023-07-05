package com.zerobase.task.invite.domain.common.util;

import org.apache.commons.lang.RandomStringUtils;

public class RandomCode {

    public static String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }
}
