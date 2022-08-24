package com.frog.authority.admin.api.constant;

/**
 * 接口url常量类
 * @author frog
 */
public class ApiConstants {

    private static final String BASE_URL = "api/";

    private static final String LIST = "list";

    public interface Auth {

        String BASE_URL = ApiConstants.BASE_URL + "auth/";

        String CAPTCHA = BASE_URL + "/captcha";

        String LOGIN = BASE_URL + "/login";

    }

    public interface User {

        String BASE_URL = ApiConstants.BASE_URL + "user/";

        String USER_NAME = BASE_URL + "username/{username}";

    }
}
