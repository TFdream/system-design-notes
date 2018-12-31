package io.dreamstudio.common;

/**
 * @author Ricky Fung
 */
public class ApiResult<T> {
    private static final int SUCCESS_CODE = 1;
    private static final String SUCCESS_MESSAGE = "OK";

    /**  **/
    private static final int INVALID_REQUEST_PARAMETER = 400;
    private static final int SYSTEM_ERROR_CODE = 500;

    private int code;
    private String message;
    private T data;

    public ApiResult() {
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功
     * @param <T>
     * @return
     */
    public static <T> ApiResult ok() {
        return new ApiResult(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }
    /**
     * 请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResult ok(T data) {
        return new ApiResult(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ApiResult invalidParam() {
        return new ApiResult(INVALID_REQUEST_PARAMETER, "参数不合法", null);
    }
    /**
     * 参数非法
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResult invalidParam(String message) {
        return new ApiResult(INVALID_REQUEST_PARAMETER, message, null);
    }

    /**
     * 定制错误(有特定业务含义)
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResult failure(int code, String message) {
        return new ApiResult(code, message, null);
    }

    public static <T> ApiResult failure(int code, String message, T data) {
        return new ApiResult(code, message, data);
    }

    /**
     * 系统异常
     * @param <T>
     * @return
     */
    public static <T> ApiResult systemError() {
        return new ApiResult(SYSTEM_ERROR_CODE, "服务器开小差了，请稍后再试！", null);
    }

    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
