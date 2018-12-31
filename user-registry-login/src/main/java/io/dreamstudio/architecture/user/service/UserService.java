package io.dreamstudio.architecture.user.service;

import io.dreamstudio.architecture.user.dao.model.UserDO;
import io.dreamstudio.architecture.user.web.vo.AuthCodeRequestVO;
import io.dreamstudio.architecture.user.web.vo.LoginRequestVO;
import io.dreamstudio.architecture.user.web.vo.LoginResultVO;
import io.dreamstudio.common.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ricky Fung
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "userTxService")
    private UserTxService userTxService;

    /**
     * 用户登录接口
     * @param req
     * @return
     */
    public ApiResult<LoginResultVO> login(LoginRequestVO req) {
        String mobile = req.getMobile();
        UserDO userDO = userTxService.getUserByMobile(mobile);
        if (userDO==null) {
            //注册流程

        } else {
            //登录流程

        }
        return ApiResult.systemError();
    }

    public ApiResult getAuthCode(AuthCodeRequestVO req) {
        String mobile = req.getMobile();
        UserDO userDO = userTxService.getUserByMobile(mobile);
        if (userDO==null) {
            //注册流程

        } else {
            //登录流程

        }
        return ApiResult.systemError();
    }
}
