package ua.com.alevel.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.alevel.service.ApiVersionService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiVersionFilter extends OncePerRequestFilter {

    private static final String API_VERSION_HEADER = "x-api-version";
    private final ApiVersionService apiVersionService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String apiVersion = request.getHeader(API_VERSION_HEADER);
        final String path = request.getRequestURI();
        if (StringUtils.isNotBlank(path) && path.startsWith("/api/version")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.isBlank(apiVersion)) {
            sendRedirect(request, response);
            return;
        }

        if (apiVersionService.checkApiVersion(apiVersion)) {
            filterChain.doFilter(request, response);
            return;
        }
        sendRedirect(request, response);
    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/api/version/invalid");
    }
}
