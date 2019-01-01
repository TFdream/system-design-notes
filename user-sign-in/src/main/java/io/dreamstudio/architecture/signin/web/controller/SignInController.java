package io.dreamstudio.architecture.signin.web.controller;

import io.dreamstudio.architecture.signin.service.SignInService;
import io.dreamstudio.common.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Ricky Fung
 */
@RestController
@RequestMapping("/api/user/sign-in/")
public class SignInController {
    @Resource(name = "signInService")
    private SignInService signInService;

    @PostMapping("/tick")
    public ApiResult doSignIn(Long userId) {
        Date now = new Date();
        return signInService.doSignIn(userId, now);
    }

    @GetMapping("/month")
    public ApiResult getSignRecord() {
        return ApiResult.systemError();
    }
}
