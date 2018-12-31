package io.dreamstudio.architecture.user.web.context;

import java.util.Date;

/**
 * @author Ricky Fung
 */
public class DefaultRequestContext implements RequestContext {
    private Long userId;
    private Date lastLoginTime;

    public DefaultRequestContext(Long userId, Date lastLoginTime) {
        this.userId = userId;
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public Long getUserId() {
        return this.userId;
    }

    @Override
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

}
