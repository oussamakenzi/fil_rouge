package com.kenzi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "a75a07d3438ede9ef3a9bf4fbcea3749c85898f89545279fd2caecf2ec90a982";

    private final JwtService jwtService;

    public JwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    // Vérifier si le token a expiré
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // Récupérer la date d'expiration du token JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
    }
    // Générer un token
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }
    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 heures d'expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public  <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();



    }

    private Key getSignInKey() {
        byte[] byteKeys= Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(byteKeys);
    }
}
