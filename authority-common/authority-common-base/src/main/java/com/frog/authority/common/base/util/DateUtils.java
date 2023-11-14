package com.frog.authority.common.base.util;

import com.frog.authority.common.base.enums.DateFormat;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author liuhuan
 */
public class DateUtils {

    /**
     * 字符串转化为LocalDateTime
     *
     * @param dateStr 日期字符串
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateStr) {
        if (StringUtils.hasLength(dateStr)) {
            for (DateFormat dateFormat : DateFormat.values()) {
                LocalDateTime value = toLocalDateTime(dateStr, dateFormat.getPattern());
                if (value != null) {
                    return value;
                }
            }
            LocalDate localDate = toLocalDate(dateStr);
            if (localDate != null) {
                return localDate.atStartOfDay();
            }
        }
        return null;
    }

    /**
     * 字符串转化为LocalDateTime
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateStr, String pattern) {
        if (StringUtils.hasLength(dateStr) && StringUtils.hasLength(pattern)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                return LocalDateTime.parse(dateStr, formatter);
            } catch (Exception e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * 字符串转化为LocalDateTime
     *
     * @param dateStr 日期字符串
     * @param defaultDateTime 默认日期
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String dateStr, LocalDateTime defaultDateTime) {
        LocalDateTime value = toLocalDateTime(dateStr);
        if (value != null) {
            return value;
        }
        return defaultDateTime;
    }

    /**
     * 字符串转化为LocalDate
     *
     * @param dateStr 日期字符串
     * @return LocalDate
     */
    public static LocalDate toLocalDate(String dateStr) {
        if (StringUtils.hasLength(dateStr)) {
            for (DateFormat dateFormat : DateFormat.values()) {
                LocalDate value = toLocalDate(dateStr, dateFormat.getPattern());
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * 字符串转化为LocalDate
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return LocalDate
     */
    public static LocalDate toLocalDate(String dateStr, String pattern) {
        if (StringUtils.hasLength(dateStr) && StringUtils.hasLength(pattern)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                // ignore
            }
        }
        return null;
    }

    /**
     * 字符串转化为LocalDate
     *
     * @param dateStr 日期字符串
     * @param defaultDate 默认日期
     * @return LocalDate
     */
    public static LocalDate toLocalDate(String dateStr, LocalDate defaultDate) {
        LocalDate value = toLocalDate(dateStr);
        if (value != null) {
            return value;
        }
        return defaultDate;
    }

    /**
     * localDateTime转化为字符串
     *
     * @param localDateTime localDateTime
     * @param pattern       日期格式
     * @return 字符串
     */
    public static String toString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }

    /**
     * localDate转化为字符串
     *
     * @param localDate localDate
     * @param pattern   日期格式
     * @return 字符串
     */
    public static String toString(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDate);
    }

}
