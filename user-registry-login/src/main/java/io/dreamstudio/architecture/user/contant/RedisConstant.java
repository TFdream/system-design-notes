package io.dreamstudio.architecture.user.contant;

/**
 * @author Ricky Fung
 */
public abstract class RedisConstant {
    private static final String KEY_PREFIX = "user:v1";

    private static final String USER_TOKEN_KEY = String.format("%s:%s", KEY_PREFIX, "token");

    private static final String USER_REGISTRY_CODE_KEY = String.format("%s:%s", KEY_PREFIX, "registry_code");

    private static final String USER_LOGIN_CODE_KEY = String.format("%s:%s", KEY_PREFIX, "login_code");

    //----------
    public static String getUserRegistryCodeKey(String mobile) {
        return String.format("%s:%s", USER_REGISTRY_CODE_KEY, mobile);
    }

    public static String getUserLoginCodeKey(String mobile) {
        return String.format("%s:%s", USER_REGISTRY_CODE_KEY, mobile);
    }

    public static String getUserTokenKey(Long userId) {
        return String.format("%s:%s", USER_TOKEN_KEY, userId);
    }

}
