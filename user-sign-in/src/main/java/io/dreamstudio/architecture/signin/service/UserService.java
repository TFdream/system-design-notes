package io.dreamstudio.architecture.signin.service;

import io.dreamstudio.architecture.signin.dao.model.UserDO;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ricky Fung
 */
@Service
public class UserService {

    public UserDO getUserById(Long userId) {
        UserDO userDO = new UserDO();
        userDO.setId(userId);
        userDO.setNickname("ricky_"+userId);
        userDO.setMobile("18622228888");
        userDO.setLoginPassword("123456");
        userDO.setHeadImgUrl("");

        userDO.setCreateTime(new Date());
        userDO.setUpdateTime(new Date());
        return userDO;
    }
}
