package io.dreamstudio.architecture.user.web.vo;

/**
 * @author Ricky Fung
 */
public class AuthCodeRequestVO {
    private String mobile;
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

    public ClientInfoVO getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoVO clientInfo) {
        this.clientInfo = clientInfo;
    }
}
