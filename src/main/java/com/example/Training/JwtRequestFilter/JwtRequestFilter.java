package com.example.Training.JwtRequestFilter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Training.entity.User;
import com.example.Training.service.UserService;
import com.example.Training.utility.JWTUtility;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtility jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        if (!requestUrl.equals("/users/authenticate") && !requestUrl.equals("/users/register")) {

            System.out.println("requestUrl: " + requestUrl);
            final String authorization = request.getHeader("Authorization");
            String username = null, jwt = null;

            if (authorization != null && authorization.startsWith("Bearer ")) {
                jwt = authorization.substring(7);
            }
            // check if token is expired
            if (jwt != null && !jwtUtil.isTokenExpired(jwt)) {

                username = jwtUtil.extractUsername(jwt);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = (User) this.userService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}