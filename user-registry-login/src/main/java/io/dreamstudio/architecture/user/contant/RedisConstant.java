package io.dreamstudio.architecture.user.contant;

/**
 * @author Ricky Fung
 */
public abstract class RedisConstant {
    private static final String KEY_PREFIX = "user:v1";

    private static final String USER_TOKEN_KEY = String.format("%s:%s", KEY_PREFIX, "token");

    public static String getUserTokenKey(Long userId) {
        return String.format("%s:%s", USER_TOKEN_KEY, userId);
    }

}
