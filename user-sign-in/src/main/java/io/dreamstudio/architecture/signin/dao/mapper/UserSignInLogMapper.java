package io.dreamstudio.architecture.signin.dao.mapper;

import io.dreamstudio.architecture.signin.dao.model.UserSignInLogDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Ricky Fung
 */
public interface UserSignInLogMapper {

    int insert(UserSignInLogDO record);

    UserSignInLogDO selectByUserIdAndDate(@Param("userId") Long userId, @Param("signInDate") Integer signInDate);

}
