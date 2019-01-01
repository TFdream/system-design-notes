package io.dreamstudio.architecture.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.dreamstudio.architecture.user.contant.RedisConstant;
import io.dreamstudio.architecture.user.enums.ClientTypeEnum;
import io.dreamstudio.architecture.user.model.UserToken;
import io.dreamstudio.architecture.user.web.context.InvalidTokenException;
import io.dreamstudio.common.util.JsonUtils;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Ricky Fung
 */
@Component
public class TokenService {
    private static final String SECRET = "secret";

    private static final String JWT_ISSUER = "auth0";

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    public String createToken(Long userId) {
        ClientTypeEnum clientType  = ClientTypeEnum.APP;
        DateTime now = DateTime.now();
        DateTime expireAt = now.plusDays(3);

        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setIssuedAt(now.toDate());
        userToken.setExpireAt(expireAt.toDate());
        userToken.setLastLoginTime(now.toDate());
        userToken.setClientType(clientType.getValue());

        String token = genToken(userToken);
        String key = RedisConstant.getUserTokenKey(userId);
        stringRedisTemplate.opsForHash().put(key, token, clientType.name());
        stringRedisTemplate.expireAt(key, expireAt.toDate());
        return token;
    }

    public UserToken decodeToken(String token) {
        String subject = verifyToken(token);
        UserToken userToken = JsonUtils.parseObject(subject, UserToken.class);
        return userToken;
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
