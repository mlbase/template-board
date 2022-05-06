package com.sweetk.excercise.config.jwt;

import io.jsonwebtoken.*;
import com.sweetk.excercise.DTO.AccessTokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    //https://bcp0109.tistory.com/301

    private static final String AUTHORITY_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACESS_TOKEN_VALID_MILISECOND = 1000L*60*60*2; //2시간
    private static final long REFRESH_TOKEN_VALID_MILISECOND = 1000L*60*60*48; //2일

    @Value("spring.jwt.secrete")
    private String secreteKey;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Qualifier("userDetailsService") UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init(){
        secreteKey = Base64.getEncoder().encodeToString(secreteKey.getBytes());
    }

    public AccessTokenDTO.TokenInfo generateToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        log.info("authentication 권한 {}",authorities);

        Long now = (new Date().getTime());

        Date accessTokenExpireIn = new Date(now + ACESS_TOKEN_VALID_MILISECOND);
        String acessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITY_KEY,authorities)
                .setExpiration(accessTokenExpireIn)
                .signWith(SignatureAlgorithm.HS256, secreteKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_VALID_MILISECOND))
                .signWith(SignatureAlgorithm.HS256, secreteKey)
                .compact();

        return AccessTokenDTO.TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(acessToken)
                .refreshToken(refreshToken)
                .refeshTokenExpirationTime(REFRESH_TOKEN_VALID_MILISECOND)
                .build();

    }

    public Authentication getAuthentication(String acessToken){
        Claims claims = parseClaims(acessToken);

        if (claims.get(AUTHORITY_KEY) == null){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITY_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(),"",authorities);
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);
    }

    private Claims parseClaims(String accessToken) {

        try {
            return Jwts.parserBuilder().setSigningKey(secreteKey).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getExpiration(String accessToken) {
        //accessToken 남은 유효시간
        Date expiration = Jwts.parserBuilder().setSigningKey(secreteKey).build().parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();

        return (expiration.getTime() - now);
    }

    public boolean validationToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(secreteKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expire JWT Token {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("UnSupproted JWT Token {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty {}",e);
        }
        return false;
    }

}
