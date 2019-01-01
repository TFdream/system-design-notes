package io.dreamstudio.architecture.signin.dao.model;

import java.util.Date;

/**
 * @author Ricky Fung
 */
public class UserSignInDO {
    private Long id;
    private Long userId;
    private String nickname;
    private Integer lastSignInDate;
    private Integer continuousSignInDays;
    private Integer version;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLastSignInDate() {
        return lastSignInDate;
    }

    public void setLastSignInDate(Integer lastSignInDate) {
        this.lastSignInDate = lastSignInDate;
    }

    public Integer getContinuousSignInDays() {
        return continuousSignInDays;
    }

    public void setContinuousSignInDays(Integer continuousSignInDays) {
        this.continuousSignInDays = continuousSignInDays;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
