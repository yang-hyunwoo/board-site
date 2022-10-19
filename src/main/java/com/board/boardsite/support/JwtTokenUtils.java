package com.board.boardsite.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenUtils {

    public static String getEmail(String token , String key) {
        return extractClaims(token , key).get("email",String.class);
    }

    //토큰 기간이 만료 되었는지 확인하는 메소드
    public static boolean isExpired(String token , String key) {
        Date expiredDate = extractClaims(token,key).getExpiration();
        return expiredDate.before(new Date());
    }

    //Claims의 값들을 꺼내오는 메소드
    private static Claims extractClaims(String token , String key) {
        return Jwts.parserBuilder().setSigningKey(getKey(key))
                .build().parseClaimsJws(token).getBody();
    }

    public static String generateToken(String email , String key ,String role,Long travelAgencyId,long expiredTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("email",email);
        claims.put("role", role);
        claims.put("travelId", travelAgencyId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(getKey(key) , SignatureAlgorithm.HS256)
                .compact();
    }

    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
