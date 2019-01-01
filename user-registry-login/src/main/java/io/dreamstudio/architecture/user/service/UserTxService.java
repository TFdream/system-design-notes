package io.dreamstudio.architecture.user.service;

import io.dreamstudio.architecture.user.dao.mapper.UserMapper;
import io.dreamstudio.architecture.user.dao.model.UserDO;
import io.dreamstudio.architecture.user.enums.IdCardTypeEnum;
import io.dreamstudio.architecture.user.enums.IdVerifiedEnum;
import io.dreamstudio.architecture.user.enums.UserStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Ricky Fung
 */
@Component
public class UserTxService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public boolean saveUser(UserDO userDO) {

        userMapper.insert(userDO);

        return true;
    }

    public UserDO createUser(String mobile, Date now) {
        UserDO userDO = new UserDO();
        userDO.setMobile(mobile);
        userDO.setNickname(StringUtils.EMPTY);
        userDO.setLoginPassword(StringUtils.EMPTY);
        userDO.setHeadImgUrl(StringUtils.EMPTY);

        userDO.setIdVerified(IdVerifiedEnum.NEW.getValue());
        userDO.setRealName(StringUtils.EMPTY);

        userDO.setIdCardType(IdCardTypeEnum.NULL.getValue());
        userDO.setIdCardNo(StringUtils.EMPTY);

        userDO.setStatus(UserStatusEnum.NORMAL.getValue());

        userDO.setCreateTime(now);
        userDO.setUpdateTime(now);
        return userDO;
    }

    public UserDO getUserByMobile(String mobile) {
        return userMapper.selectUserByMobile(mobile);
    }

    public UserDO getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
