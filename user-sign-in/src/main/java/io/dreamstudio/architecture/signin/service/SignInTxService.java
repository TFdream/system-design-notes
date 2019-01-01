package io.dreamstudio.architecture.signin.service;

import io.dreamstudio.architecture.signin.dao.mapper.UserSignInLogMapper;
import io.dreamstudio.architecture.signin.dao.mapper.UserSignInMapper;
import io.dreamstudio.architecture.signin.dao.model.UserDO;
import io.dreamstudio.architecture.signin.dao.model.UserSignInDO;
import io.dreamstudio.architecture.signin.dao.model.UserSignInLogDO;
import io.dreamstudio.common.util.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * @author Ricky Fung
 */
@Component
public class SignInTxService {
    @Autowired
    private UserSignInMapper signInMapper;

    @Autowired
    private UserSignInLogMapper signInLogMapper;

    @Transactional
    public int saveSignIn(Long userId, UserSignInDO signInRecord, UserSignInLogDO signInLogRecord) {
        if (signInRecord.getId()==null) {
            signInMapper.insert(signInRecord);
        } else {
            signInMapper.updateByPrimaryKeySelective(signInRecord);
        }

        return signInLogMapper.insert(signInLogRecord);
    }

    //-------
    public UserSignInDO createUserSignInRecordV1(UserSignInDO userSignInDO, boolean continuousSignIn, Integer signInDate, Date now) {
        UserSignInDO signInDO = new UserSignInDO();
        //主键id
        signInDO.setId(userSignInDO.getId());

        signInDO.setLastSignInDate(signInDate);
        if (continuousSignIn) {
            //连续签到
            signInDO.setContinuousSignInDays(userSignInDO.getContinuousSignInDays() + Ints.ONE);
        } else {
            signInDO.setContinuousSignInDays(Ints.ONE);
        }

        signInDO.setVersion(userSignInDO.getVersion());
        signInDO.setUpdateTime(now);
        return signInDO;
    }

    public UserSignInDO createUserSignInRecord(UserDO userDO, Integer signIdDate, Date now) {
        UserSignInDO signInDO = new UserSignInDO();
        signInDO.setUserId(userDO.getId());
        signInDO.setNickname(userDO.getNickname());
        signInDO.setLastSignInDate(signIdDate);
        signInDO.setContinuousSignInDays(Ints.ONE);
        signInDO.setVersion(Ints.ONE);
        signInDO.setCreateTime(now);
        signInDO.setUpdateTime(now);
        return signInDO;
    }

    public UserSignInLogDO createUserSignInLogRecord(Long userId, Integer signIdDate, Date now) {
        UserSignInLogDO signInLogDO = new UserSignInLogDO();
        signInLogDO.setUserId(userId);
        signInLogDO.setSignInDate(signIdDate);
        signInLogDO.setCreateTime(now);
        signInLogDO.setUpdateTime(now);
        return signInLogDO;
    }

    //-------
    public UserSignInDO queryUserSignInRecord(Long userId) {
        return signInMapper.selectByUserId(userId);
    }

    public UserSignInLogDO queryUserSignInRecord(Long userId, Integer signIdDate) {
        return signInLogMapper.selectByUserIdAndDate(userId, signIdDate);
    }

}
