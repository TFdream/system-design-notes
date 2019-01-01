package io.dreamstudio.architecture.signin.web.vo;

/**
 * @author Ricky Fung
 */
public class UserSignInResultVO {
    private Integer continuousSignInDays;

    public Integer getContinuousSignInDays() {
        return continuousSignInDays;
    }

    public void setContinuousSignInDays(Integer continuousSignInDays) {
        this.continuousSignInDays = continuousSignInDays;
    }
}
