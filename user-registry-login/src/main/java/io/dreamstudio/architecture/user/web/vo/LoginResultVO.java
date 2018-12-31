package io.dreamstudio.architecture.user.web.vo;

/**
 * @author Ricky Fung
 */
public class LoginResultVO {
    private String nickname;
    private String token;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
