package io.dreamstudio.architecture.user.web.context;

import java.util.Date;

/**
 * @author Ricky Fung
 */
public interface RequestContext {
    Long getUserId();

    Date getLastLoginTime();
}
