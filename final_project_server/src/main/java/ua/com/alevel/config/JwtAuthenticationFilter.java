package ua.com.alevel.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.com.alevel.service.JwtService;
import ua.com.alevel.util.SecurityUtil;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(BEARER_PREFIX.length());
        final String username = jwtService.extractUsername(token);

        if (StringUtils.isNotBlank(username) && SecurityUtil.getAuthentication() == null) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && jwtService.isTokenValid(token, userDetails)) {
                final SecurityContext context = SecurityContextHolder.createEmptyContext();
                final UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(upat);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
