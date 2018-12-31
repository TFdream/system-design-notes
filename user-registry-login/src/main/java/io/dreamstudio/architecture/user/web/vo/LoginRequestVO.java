package io.dreamstudio.architecture.user.web.vo;

/**
 * @author Ricky Fung
 */
public class LoginRequestVO {
    private String mobile;
    private String password;
    private String authCode;
    private Integer loginType;
    /**
     * 客户端信息
     */
    private ClientInfoVO clientInfo;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public ClientInfoVO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoVO clientInfo) {
        this.clientInfo = clientInfo;
    }
}
