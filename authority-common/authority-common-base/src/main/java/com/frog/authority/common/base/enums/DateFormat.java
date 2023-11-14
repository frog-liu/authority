package com.frog.authority.common.base.enums;

import lombok.Getter;

/**
 * 日期格式枚举类
 *
 * @author liuhuan
 */
@Getter
public enum DateFormat {

    /**
     * example: 2020-06-01 00:00:00
     */
    YMD_HMS_HYPHEN("yyyy-MM-dd HH:mm:ss"),

    /**
     * example: 01-06-2020 00:00:00
     */
    DMY_HMS_HYPHEN("dd-MM-yyyy HH:mm:ss"),

    /**
     * example: 06-01-2020 00:00:00
     */
    MDY_HMS_HYPHEN("MM-dd-yyyy HH:mm:ss"),

    /**
     * example: 2020/06/01 00:00:00
     */
    YMD_HMS_SLASH("yyyy/MM/dd HH:mm:ss"),

    /**
     * example: 01/06/2020 00:00:00
     */
    DMY_HMS_SLASH("dd/MM/yyyy HH:mm:ss"),

    /**
     * example: 06/01/2020 00:00:00
     */
    MDY_HMS_SLASH("MM/dd/yyyy HH:mm:ss"),

    /**
     * example: 2020.06.01 00:00:00
     */
    YMD_HMS_DOT("yyyy.MM.dd HH:mm:ss"),

    /**
     * example: 01.06.2020 00:00:00
     */
    DMY_HMS_DOT("dd.MM.yyyy HH:mm:ss"),

    /**
     * example: 06.01.2020 00:00:00
     */
    MDY_HMS_DOT("MM.dd.yyyy HH:mm:ss"),

    /**
     * example: 20200601 00:00:00
     */
    YMD_HMS("yyyyMMdd HH:mm:ss"),

    /**
     * example: 01062020 00:00:00
     */
    DMY_HMS("ddMMyyyy HH:mm:ss"),

    /**
     * example: 06012020 00:00:00
     */
    MDY_HMS("MMddyyyy HH:mm:ss"),

    /**
     * example: 20200601000000
     */
    YMDHMS("yyyyMMddHHmmss"),

    /**
     * example: 2020-06-01
     */
    YMD_HYPHEN("yyyy-MM-dd"),

    /**
     * example: 01-06-2020
     */
    DMY_HYPHEN("dd-MM-yyyy"),

    /**
     * example: 06-01-2020
     */
    MDY_HYPHEN("MM-dd-yyyy"),

    /**
     * example: 2020/06/01
     */
    YMD_SLASH("yyyy/MM/dd"),

    /**
     * example: 01/06/2020
     */
    DMY_SLASH("dd/MM/yyyy"),

    /**
     * example: 06/01/2020
     */
    MDY_SLASH("MM/dd/yyyy"),

    /**
     * example: 2020.06.01
     */
    YMD_DOT("yyyy.MM.dd"),

    /**
     * example: 01.06.2020
     */
    DMY_DOT("dd.MM.yyyy"),

    /**
     * example: 06.01.2020
     */
    MDY_DOT("MM.dd.yyyy"),

    /**
     * example: 20200601
     */
    YMD("yyyyMMdd"),

    /**
     * example: 01062020
     */
    DMY("ddMMyyyy"),

    /**
     * example: 06012020
     */
    MDY("MMddyyyy");

    private final String pattern;

    DateFormat(String pattern) {
        this.pattern = pattern;
    }
}
