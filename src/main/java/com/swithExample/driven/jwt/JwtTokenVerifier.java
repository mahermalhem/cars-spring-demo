package com.swithExample.driven.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (!jwtTokenProvider.validateToken(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String username = jwtTokenProvider.getUsername(authorizationHeader);
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = jwtTokenProvider.getGrantedAuthorities(authorizationHeader);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            JwtException(response, request);
        }
        filterChain.doFilter(request, response);

    }

    static void JwtException(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpResponse httpResponse =
                new HttpResponse(FORBIDDEN.value(),
                        FORBIDDEN,
                        FORBIDDEN.getReasonPhrase().toUpperCase(),
                        String.format("Token".toUpperCase() + " %s " + "not valid".toUpperCase(), request.getHeader(AUTHORIZATION)));
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, httpResponse);
        out.flush();
    }

}
