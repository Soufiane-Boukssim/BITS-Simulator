package com.simulator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.globalService.TokenError;
import com.simulator.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final TokenError tokenError = new TokenError();
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/api/o/**",
            "/simulator.html",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/favicon.ico",
            "/api/sa/bank/getAllBanks"

    );


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            //logger.info(headerName + ": " + request.getHeader(headerName));
        }
        String requestUrl = request.getServletPath();
        if (isPathExcluded(requestUrl)) {
          filterChain.doFilter(request, response);
          return;
        }

        String jwt;
        String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          response.setStatus(200);
          response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
          response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(1)));
          response.flushBuffer();
          return;
        }
        jwt = authHeader.substring(7);
        try {
            userEmail = jwtService.extractUsername(jwt);
            logger.info(userEmail);
        } catch (Exception e) {
            logger.info(e.getClass().getName());
            e.printStackTrace();
            response.setStatus(200);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            if (e.getClass().getName().equalsIgnoreCase("io.jsonwebtoken.ExpiredJwtException")) {
                response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(2)));
            } else {
                response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(0)));
            }
            response.flushBuffer();
            return;
        }

try {
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        var isTokenExpired = tokenRepository.findByToken(jwt)
                .map(t -> !t.isExpired())
                .orElse(false);
        var isTokenRevoked = tokenRepository.findByToken(jwt)
                .map(t -> !t.isRevoked())
                .orElse(false);

        if (jwtService.isTokenValid(jwt, userDetails)) {
            if (isTokenExpired) {
                if (isTokenRevoked) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                } else {
                    response.setStatus(200);
                    response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                    response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(4)));
                    return;
                }
            } else {
                response.setStatus(200);
                response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(2)));
                return;
            }
        } else {
            response.setStatus(200);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(3)));
            return;
        }
    } else {
        response.setStatus(200);
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(0)));
        return;
    }
}catch (Exception e){
    logger.info(e.getMessage());
    e.printStackTrace();
    response.setStatus(200);
    response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    response.getOutputStream().print(new ObjectMapper().writeValueAsString(tokenError.issue(3)));
    return;
}
        try {
            logger.info(request.toString());
            filterChain.doFilter(request, response);
        }catch (Exception e){
            logger.info(e.getMessage());
            e.printStackTrace();
        }

    }

    private boolean isPathExcluded(String requestURL) {
      return EXCLUDED_PATHS.stream().anyMatch(path -> antPathMatcher.match(path, requestURL));
    }
}
