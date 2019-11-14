package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 *
 */
@Data
@ConfigurationProperties("jwt.config")  //哪个模块调用那块配置
public class JwtUtil {
    private String key; //盐
    private long ttl;  //过期时间

    /**
     * 创建jwt
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles){
        long nowTimes = System.currentTimeMillis();
        Date now = new Date(nowTimes);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0){
            builder.setExpiration(new Date(nowTimes + ttl));
        }
        return builder.compact();
    }

    public Claims parseJwt(String jwtstr){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJwt(jwtstr)
                .getBody();
    }



}
