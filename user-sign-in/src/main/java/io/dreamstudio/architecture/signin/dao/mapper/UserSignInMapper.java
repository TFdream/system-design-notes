package io.dreamstudio.architecture.signin.dao.mapper;

import io.dreamstudio.architecture.signin.dao.model.UserSignInDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Ricky Fung
 */
public interface UserSignInMapper {

    int insert(UserSignInDO record);

    int updateByPrimaryKeySelective(UserSignInDO record);

    UserSignInDO selectByUserId(@Param("userId") Long userId);

}
