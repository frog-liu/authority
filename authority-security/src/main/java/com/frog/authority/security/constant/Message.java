package com.frog.authority.security.constant;

/**
 * @author frog
 */
public class Message {

    public interface Captcha {

        String KEY_NOT_EMPTY = "验证码key不能为空!";

        String NOT_EMPTY = "验证码不能为空!";

        String EXPIRED = "验证码已过期!";

        String MISTAKE = "验证码错误!";
    }
}
