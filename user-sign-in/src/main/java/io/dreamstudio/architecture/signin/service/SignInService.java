package io.dreamstudio.architecture.signin.service;

import io.dreamstudio.architecture.signin.dao.model.UserDO;
import io.dreamstudio.architecture.signin.dao.model.UserSignInDO;
import io.dreamstudio.architecture.signin.dao.model.UserSignInLogDO;
import io.dreamstudio.architecture.signin.web.vo.UserSignInResultVO;
import io.dreamstudio.common.ApiResult;
import io.dreamstudio.common.util.DateUtils;
import io.dreamstudio.common.util.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Ricky Fung
 */
@Service
public class SignInService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Resource(name = "signInTxService")
    private SignInTxService signInTxService;

    public ApiResult<UserSignInResultVO> doSignIn(Long userId, Date now) {
        UserDO userDO = userService.getUserById(userId);
        if (userDO==null) {
            return ApiResult.failure(1000, "用户不存在");
        }

        Integer signInDate = Integer.parseInt(DateUtils.format(now, DateUtils.DATE_COMPACT_FORMAT));
        try {
            UserSignInDO userSignInDO = signInTxService.queryUserSignInRecord(userId);
            Integer continuousSignInDays;
            if (userSignInDO==null) {
                //第一次签到
                UserSignInDO signInRecord = signInTxService.createUserSignInRecord(userDO, signInDate, now);
                UserSignInLogDO signInLogRecord = signInTxService.createUserSignInLogRecord(userId, signInDate, now);

                //保存
                signInTxService.saveSignIn(userId, signInRecord, signInLogRecord);
                continuousSignInDays = Ints.ONE;
                logger.info("用户签到服务-签到, userId:{}, signIdDate:{} 第一次签到成功", userId, signInDate);
            } else {
                if (userSignInDO.getLastSignInDate().intValue() == signInDate) {
                    return ApiResult.failure(1001, "今日已签到");
                }

                boolean continuousSignIn = true;
                //计算时间差
                Date lastSignDate = DateUtils.parseDate(userSignInDO.getLastSignInDate().toString(), DateUtils.DATE_COMPACT_FORMAT);
                if (Math.abs(DateUtils.daysBetween(lastSignDate, now)) > Ints.ONE) {
                    continuousSignIn = false;
                }
                UserSignInDO signInRecord = signInTxService.createUserSignInRecordV1(userSignInDO, continuousSignIn, signInDate, now);
                UserSignInLogDO signInLogRecord = signInTxService.createUserSignInLogRecord(userId, signInDate, now);
                //保存
                signInTxService.saveSignIn(userId, signInRecord, signInLogRecord);
                continuousSignInDays = signInRecord.getContinuousSignInDays();
                logger.info("用户签到服务-签到, userId:{}, signIdDate:{} continuousSignIn:{}, continuousSignInDays:{} 签到成功",
                        userId, signInDate, continuousSignIn, continuousSignInDays);
            }
            UserSignInResultVO resultVO = new UserSignInResultVO();
            resultVO.setContinuousSignInDays(continuousSignInDays);
            return ApiResult.ok(resultVO);
        } catch (Exception e) {
            logger.error("用户签到服务-签到接口异常, userId:{}, signInDate:{}", userId, signInDate, e);
        }
        return ApiResult.systemError();
    }
}
