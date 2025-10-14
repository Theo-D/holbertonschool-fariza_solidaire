package com.hbtn.zafirasolidaire.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.model.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretKey = "";

    public JwtService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private SecretKey getKey() {
        byte[] keyToBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyToBytes);
    }

    public String generateToken(UUID userId, boolean isAdmin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", List.of(isAdmin ? "ROLE_ADMIN" : "ROLE_USER"));

        return Jwts.builder().claims()
                             .add(claims)
                             .subject(userId.toString())
                             .issuedAt(new Date(System.currentTimeMillis()))
                             .expiration(new Date(System.currentTimeMillis() +  600 * 60 * 30))
                             .and()
                             .signWith(getKey())
                             .compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidToken(String token, CustomUserDetails userDetails) {
        final String id = extractUserId(token);
        return (id.equals(userDetails.getId()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationToken(token).before(new Date());
    }

    private Date extractExpirationToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
