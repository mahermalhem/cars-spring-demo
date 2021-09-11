package com.swithExample.driven.jwt;

import com.swithExample.driven.auth.AuthUser;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.var;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.swithExample.driven.common.constant.AuthenticationConstant.*;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECURE.getBytes());

    private Claims getBody(String authorizationHeader) {
        String token = authorizationHeader.replace(HEADER_PREFIX, "");
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public String getUnAuthorizationToken(String authorizationHeard) {
        return authorizationHeard.replace(HEADER_PREFIX, "");
    }

    public String getUsername(String authorizationHeader) {
        return getBody(authorizationHeader).getSubject();
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(String authorizationHeader) {
        var authorities = (List<Map<String, String>>) getBody(authorizationHeader).get(AUTHORITIES);

        return authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get(AUTHORITY)))
                .collect(Collectors.toSet());
    }

    public String generateToken(AuthUser authUser) {

        String token = Jwts
                .builder()
                .setSubject(authUser.getUsername())
                .claim(AUTHORITIES, authUser.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(secretKey)
                .compact();
        return HEADER_PREFIX + token;
    }

    boolean validateToken(String authorizationHeader) {
        return !Strings.isNullOrEmpty(authorizationHeader) && authorizationHeader.startsWith(HEADER_PREFIX);
    }
}
