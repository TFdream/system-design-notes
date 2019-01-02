package io.dreamstudio.architecture.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.dreamstudio.architecture.user.contant.Constant;
import io.dreamstudio.architecture.user.contant.RedisConstant;
import io.dreamstudio.architecture.user.enums.ClientTypeEnum;
import io.dreamstudio.architecture.user.model.UserToken;
import io.dreamstudio.architecture.user.web.context.InvalidTokenException;
import io.dreamstudio.common.util.JsonUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky Fung
 */
@Component
public class TokenService {
    private static final String SECRET = "secret";

    private static final String JWT_ISSUER = "auth0";

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 创建/续约token
     * @param userId
     * @param issuedAt
     * @param expireAt
     * @param now
     * @return
     */
    public String refreshToken(Long userId, Date issuedAt, Date expireAt, Date now, long ttlSeconds) {
        ClientTypeEnum clientType  = ClientTypeEnum.APP;

        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setIssuedAt(issuedAt);
        userToken.setExpireAt(expireAt);
        userToken.setLastLoginTime(now);
        userToken.setClientType(clientType.getValue());

        String token = genToken(userToken);
        String key = RedisConstant.getUserTokenKey(userId);

        stringRedisTemplate.opsForValue().set(key, token, ttlSeconds, TimeUnit.SECONDS);
        return token;
    }

    public UserToken decodeToken(String token) {
        String subject = verifyToken(token);
        UserToken userToken = JsonUtils.parseObject(subject, UserToken.class);
        return userToken;
    }

    public String getServerToken(Long userId) {
        String key = RedisConstant.getUserTokenKey(userId);
        return stringRedisTemplate.opsForValue().get(key);
    }

    public long getTtl(DateTime now, DateTime expireAt) {
        return Seconds.secondsBetween(now, expireAt).getSeconds();
    }

    private String genToken(UserToken userToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withSubject(JsonUtils.toJson(userToken))
                    .withIssuedAt(userToken.getIssuedAt()) // sign time
                    .withExpiresAt(userToken.getExpireAt()) // expire time
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new InvalidTokenException("产生token失败", e);
        }
    }

    private String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException e){
            //Invalid signature/claims
            throw new InvalidTokenException("验证token失败", e);
        }
    }

}
