package io.dreamstudio.architecture.user.web.context;

/**
 * @author Ricky Fung
 */
public abstract class RequestContextHolder {
    private static final ThreadLocal<RequestContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(RequestContext context) {
        THREAD_LOCAL.set(context);
    }

    public static RequestContext getContext() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
