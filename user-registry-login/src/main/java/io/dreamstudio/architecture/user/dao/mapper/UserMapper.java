package io.dreamstudio.architecture.user.dao.mapper;

import io.dreamstudio.architecture.user.dao.model.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author Ricky Fung
 */
public interface UserMapper {

    int insert(UserDO user);

    UserDO selectByPrimaryKey(Long id);

    UserDO selectUserByMobile(@Param("mobile") String mobile);
}
