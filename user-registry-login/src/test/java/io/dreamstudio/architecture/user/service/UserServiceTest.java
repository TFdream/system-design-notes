package io.dreamstudio.architecture.user.service;

import io.dreamstudio.architecture.user.BaseSpringBootJUnitTest;
import io.dreamstudio.architecture.user.enums.LoginTypeEnum;
import io.dreamstudio.architecture.user.web.vo.*;
import io.dreamstudio.common.ApiResult;
import io.dreamstudio.common.util.JsonUtils;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Ricky Fung
 */
public class UserServiceTest extends BaseSpringBootJUnitTest {

    @Resource(name = "userService")
    private UserService userService;

    private String mobile = "18622226666";

    @Test
    @Ignore
    public void testRefreshToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoyLFwiaXNzdWVkQXRcIjpcIjIwMTktMDEtMDMgMTc6MDc6NDZcIixcImV4cGlyZUF0XCI6XCIyMDE5LTAxLTA4IDE3OjA3OjQ2XCIsXCJsYXN0TG9naW5UaW1lXCI6XCIyMDE5LTAxLTAzIDE3OjUwOjQxXCIsXCJyZW5ld2FsVGltZXNcIjoyLFwiY2xpZW50VHlwZVwiOjF9IiwiaXNzIjoiYXV0aDAiLCJleHAiOjE1NDY5Mzg0NjYsImlhdCI6MTU0NjUwNjQ2Nn0.7vKZZ6yXmlDrPN3bLjKAf4Fgx88wLMEZcIj7IM-m-V4";
        ApiResult<TokenRenewalResultVO>  result = userService.refreshToken(token);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    @Ignore
    public void testLogin() {
        LoginRequestVO req = new LoginRequestVO();
        req.setMobile(mobile);
        req.setAuthCode("6933");
        req.setLoginType(LoginTypeEnum.SMS_CODE.getValue());
        ApiResult<LoginResultVO> result = userService.login(req);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    @Ignore
    public void testRegistry() {
        RegistryRequestVO req = new RegistryRequestVO();
        req.setMobile(mobile);
        req.setAuthCode("5021");
        req.setNickname("ricky");
        req.setPassword("root");
        ApiResult<SuccessResultVO> result = userService.registry(req);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    @Ignore
    public void testAuthCode() {
        AuthCodeRequestVO req = new AuthCodeRequestVO();
        req.setMobile(mobile);
        ApiResult<SuccessResultVO> result = userService.getAuthCode(req);
        System.out.println(JsonUtils.toJson(result));
    }
}
