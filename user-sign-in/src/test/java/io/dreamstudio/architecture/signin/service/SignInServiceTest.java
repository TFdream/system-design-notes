package io.dreamstudio.architecture.signin.service;

import io.dreamstudio.architecture.signin.BaseSpringBootJUnitTest;
import io.dreamstudio.common.ApiResult;
import io.dreamstudio.common.util.DateUtils;
import io.dreamstudio.common.util.JsonUtils;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Ricky Fung
 */
public class SignInServiceTest extends BaseSpringBootJUnitTest {

    @Resource(name = "signInService")
    private SignInService signInService;

    private Long userId = 3L;

    @Test
    @Ignore
    public void testSign() {
        DateTime now = DateTime.now();
        Date signDate = now.plusDays(1).toDate();
        ApiResult result = signInService.doSignIn(userId, signDate);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    @Ignore
    public void testSignBatch() {
        DateTime now = DateTime.now();
        DateTime startTime = now.minusMonths(1);

        for (int i=0; i<20; i++) {
            Date signDate = startTime.toDate();
            ApiResult result = signInService.doSignIn(userId, signDate);
            System.out.println(JsonUtils.toJson("signDate:" +DateUtils.format(signDate) +", 结果: "+JsonUtils.toJson(result)));

            startTime = startTime.plusDays(1);
        }
    }
}
