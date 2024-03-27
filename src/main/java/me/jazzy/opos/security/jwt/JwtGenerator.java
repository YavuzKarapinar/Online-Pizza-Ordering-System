package me.jazzy.opos.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtGenerator {

    public String generateToken(String email) {
        return  Jwts.builder()
                    .subject(email)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .signWith(secretKey())
                    .compact();
    }

    private SecretKey secretKey() {
        String key = "19283109asdklasd;adasd;1'!+%1a32";
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> function) {
        final Claims claims = extractAllClaims(token);
        return function.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean isValidToken(String token) {
        try {
            if(!isTokenExpired(token)) {
                Jwts.parser()
                        .verifyWith(secretKey())
                        .build()
                        .parseSignedClaims(token);
                return true;
            }
        } catch (JwtException e) {
            throw new RuntimeException("Token Expired");
        }
        return false;
    }
}