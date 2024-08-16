package com.b1aboa.wedug.filters;

import com.b1aboa.wedug.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.*;

@Slf4j
public class UserFavoritesLoggingFilter implements Filter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put("traceId", String.format("[%s]", UUID.randomUUID()));

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Map<String, Object> logData = new HashMap<>();

        // js, css, html 요청 포함 x
        if (!isExcludedPath(httpRequest)) {
            logRequest(httpRequest, logData);

            filterChain.doFilter(httpRequest, httpResponse);

            boolean isSuccess = isResponseSuccessful(httpResponse, logData);
            // 응답 200 일때
            if (isSuccess) {
                try {
                    String jsonLog = objectMapper.writeValueAsString(logData);
                    log.info("{}", jsonLog);
                } catch (JsonProcessingException e) {
                    log.error("Failed to log JSON", e);
                }
            }
        } else {
            filterChain.doFilter(httpRequest, httpResponse);
        }

        // Clear MDC
        MDC.clear();
    }

    private boolean isExcludedPath(HttpServletRequest request) {
        String url = request.getRequestURI();

        // 제외할 경로 목록
        List<String> excludedPaths = Arrays.asList("/images", "/font", "/css", "/js");


        return excludedPaths.stream().anyMatch(url::startsWith);
    }

    private boolean isResponseSuccessful(HttpServletResponse servletResponse, Map<String, Object> logData) throws IOException {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(servletResponse);
        boolean isSuccess = (responseWrapper.getStatus() == HttpServletResponse.SC_OK);

        if (isSuccess) {
            logData.put("status", HttpServletResponse.SC_OK);
        }

        responseWrapper.copyBodyToResponse();

        return isSuccess;
    }

    private void logRequest(HttpServletRequest request, Map<String, Object> logData) {
        String paths = "/places";
        String requestUri = request.getRequestURI();

        // GET 요청들어오고 URL에 "/places"만 로깅함
        if (requestUri.contains(paths)) {
            logData.put("method", request.getMethod());
            logData.put("url", requestUri);

            if ("GET".equalsIgnoreCase(request.getMethod())) {
                logRequestParameters(request, logData); // GET 요청일 경우 파라미터 로깅
            }
        }
    }


    private void logRequestParameters(HttpServletRequest request, Map<String, Object> logData) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> parameters = new HashMap<>();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();

            for (String paramValue : paramValues) {
                parameters.put(paramName, paramValue);
            }
        }

        logData.put("parameters", parameters);
    }

}
