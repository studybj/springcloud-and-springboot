package com.justtide.tms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@Data
@ConfigurationProperties("jwt.config")  //哪个模块调用那块配置
public class JwtUtil {
    private String key = "study"; //盐
    private long ttl;  //过期时间

    /**
     * 创建jwt
     * @Description: 生成JWT字符串
     * 格式：A.B.C
     * A-header头信息
     * B-payload 有效负荷
     * C-signature 签名信息 是将header和payload进行加密生成的
     * @param id 用户id
     * @param subject 用户名
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles){
        long nowTimes = System.currentTimeMillis();
        Date now = new Date(nowTimes);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("roles", roles);
        if (ttl > 0){
            builder.setExpiration(new Date(nowTimes + ttl));
        }
        return builder.compact();
    }

    public Claims parseJwt(String jwtstr){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtstr)
                .getBody();

        return claims;
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String jwt = jwtUtil.createJwt("1", "zhangsan", "aa");
        Claims claims = jwtUtil.parseJwt(jwt);
    }
}
