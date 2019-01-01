package io.dreamstudio.architecture.user.service;

import io.dreamstudio.architecture.user.contant.RedisConstant;
import io.dreamstudio.architecture.user.dao.model.UserDO;
import io.dreamstudio.architecture.user.web.vo.*;
import io.dreamstudio.common.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "userTxService")
    private UserTxService userTxService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "tokenService")
    private TokenService tokenService;


    public ApiResult<UserInfoVO> getUserInfo(Long userId) {
        UserDO userDO = userTxService.getUserById(userId);
        if (userDO==null) {
            return ApiResult.failure(1000, "用户不存在");
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setMobile(userDO.getMobile());
        userInfoVO.setNickname(userDO.getNickname());
        userInfoVO.setHeadImgUrl(userDO.getHeadImgUrl());
        return ApiResult.ok(userInfoVO);
    }

    /**
     * 用户登录接口
     * @param req
     * @return
     */
    public ApiResult<LoginResultVO> login(LoginRequestVO req) {
        String mobile = req.getMobile();
        UserDO userDO = userTxService.getUserByMobile(mobile);
        if (userDO==null) {
            return ApiResult.failure(1000, "用户不存在");
        }

        //校验验证码
        String key = RedisConstant.getUserLoginCodeKey(mobile);
        String randomCode = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(randomCode)) {
            return ApiResult.failure(1000, "验证码已失效");
        }

        if (!req.getAuthCode().equals(randomCode)) {
            return ApiResult.failure(1001, "验证码不正确");
        }
        Long userId = userDO.getId();
        logger.info("用户服务-登录接口, 用户mobile:{}, userId:{} 身份认证通过", mobile, userId);

        String token = tokenService.createToken(userId);
        LoginResultVO resultVO = new LoginResultVO();
        resultVO.setToken(token);
        resultVO.setNickname(userDO.getNickname());
        return ApiResult.ok(resultVO);
    }

    /**
     * 用户注册
     * @param req
     * @return
     */
    public ApiResult<SuccessResultVO> registry(RegistryRequestVO req) {
        //注册流程
        Date now = new Date();

        String mobile = req.getMobile();
        UserDO userDO = userTxService.getUserByMobile(mobile);
        if (userDO!=null) {
            return ApiResult.failure(1000, "手机号已注册");
        }

        String key = RedisConstant.getUserRegistryCodeKey(mobile);
        String randomCode = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(randomCode)) {
            return ApiResult.failure(1001, "验证码已失效");
        }

        if (!req.getAuthCode().equals(randomCode)) {
            return ApiResult.failure(1002, "验证码不正确");
        }

        //保存用户注册信息
        userDO = userTxService.createUser(mobile, now);
        userTxService.saveUser(userDO);
        logger.info("用户服务-注册接口, 用户mobile:{} 注册成功", mobile);

        SuccessResultVO resultVO = new SuccessResultVO();
        resultVO.setSuccess(Boolean.TRUE);
        return ApiResult.ok(resultVO);
    }

    public ApiResult<SuccessResultVO> getAuthCode(AuthCodeRequestVO req) {
        String mobile = req.getMobile();
        UserDO userDO = userTxService.getUserByMobile(mobile);
        if (userDO==null) {
            //注册流程, 发送注册验证码
            //1.生成4位随机数
            String randomCode = genRandomCode(4);
            logger.info("用户服务-发送注册验证码, mobile:{}, randomCode:{}", mobile, randomCode);

            //2.调用短信网关发送验证码
            sendSmsCode(mobile, randomCode);

            //3.redis中记录最新的验证码
            String key = RedisConstant.getUserRegistryCodeKey(mobile);
            //过期时间5分钟
            stringRedisTemplate.opsForValue().set(key, randomCode, 5, TimeUnit.MINUTES);

            SuccessResultVO resultVO = new SuccessResultVO();
            resultVO.setSuccess(Boolean.TRUE);
            return ApiResult.ok(resultVO);
        } else {
            //登录流程, 发送注册验证码
            //1.生成4位随机数
            String randomCode = genRandomCode(4);
            logger.info("用户服务-发送登录验证码, mobile:{}, randomCode:{}", mobile, randomCode);

            //2.调用短信网关发送验证码
            sendSmsCode(mobile, randomCode);

            //3.redis中记录最新的验证码
            String key = RedisConstant.getUserLoginCodeKey(mobile);
            //过期时间5分钟
            stringRedisTemplate.opsForValue().set(key, randomCode, 5, TimeUnit.MINUTES);

            SuccessResultVO resultVO = new SuccessResultVO();
            resultVO.setSuccess(Boolean.TRUE);
            return ApiResult.ok(resultVO);
        }
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param randomCode
     * @return
     */
    private boolean sendSmsCode(String mobile, String randomCode) {
        return true;
    }

    private String genRandomCode(int length) {
        return null;
    }

}
