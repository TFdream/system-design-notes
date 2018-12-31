package io.dreamstudio.architecture.user.service;

import io.dreamstudio.architecture.user.dao.mapper.UserMapper;
import io.dreamstudio.architecture.user.dao.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ricky Fung
 */
@Component
public class UserTxService {

    @Autowired
    private UserMapper userMapper;

    public UserDO getUserByMobile(String mobile) {
        return userMapper.selectUserByMobile(mobile);
    }

    public UserDO getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
