package io.dreamstudio.architecture.signin.dao.model;

import java.util.Date;

/**
 * @author Ricky Fung
 */
public class UserSignInLogDO {
    private Long id;
    private Long userId;
    private Integer signInDate;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Integer signInDate) {
        this.signInDate = signInDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
