package edu.nuc.light_system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @ClassName JwtUtils
 * @Description //jwt组件类
 * @Author 19265
 * @Date 2026/4/13 10:41
 * @Version 1.0.0
 */
@Component
public class JwtUtils {
    private static String secret;
    private static Long expireTime;

    @Value("${jwt.secret}")
    public void setSecret(String jwtSecret) {
        secret = jwtSecret;
    }

    @Value("${jwt.expire-time}")
    public void setExpireTime(Long jwtExpireTime) {
        expireTime = jwtExpireTime;
    }

    @PostConstruct
    public void init() {
    }

    public static String createToken(String userId, Integer right, String nickName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("right", right);
        claims.put("nickName", nickName);

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims != null ? (String) claims.get("userId") : null;
    }

    public static Integer getRightFromToken(String token) {
        Claims claims = parseToken(token);
        return claims != null ? (Integer) claims.get("right") : null;
    }

    public static String getNickNameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims != null ? (String) claims.get("nickName") : null;
    }

    public static boolean validateToken(String token) {
        return parseToken(token) != null;
    }
}
