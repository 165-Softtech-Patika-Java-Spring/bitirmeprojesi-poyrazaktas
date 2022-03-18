package com.poyrazaktas.bitirme.sec.security;

import com.poyrazaktas.bitirme.sec.enums.JwtConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (StringUtils.hasText(token)) {
            boolean isTokenValid = jwtTokenGenerator.validateToken(token);
            if (isTokenValid) {
                String userName = jwtTokenGenerator.findUserNameByToken(token);
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String fullToken = request.getHeader("Authorization");

        String token = null;

        String bearerPrefix = JwtConstant.BEARER.getConstant();
        if (StringUtils.hasText(fullToken)) {
            if (fullToken.startsWith(bearerPrefix)) {
                token = fullToken.substring(bearerPrefix.length());
            }
        }

        return token;
    }
}
