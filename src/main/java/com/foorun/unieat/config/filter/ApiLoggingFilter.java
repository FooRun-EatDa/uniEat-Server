package com.foorun.unieat.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static com.foorun.unieat.util.JsonUtil.asJson;
import static com.foorun.unieat.util.JsonUtil.asJsonWithoutSpaces;

@Slf4j
@Component
public class ApiLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        long startTime = System.currentTimeMillis();
        chain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();

        log.info("[{}] {} {} {}\n" +
                "Headers ==> {}\n" +
                "Request ==> {}\n" +
                "Response ==> {}\n",
                ((HttpServletRequest) request).getMethod(), ((HttpServletRequest) request).getRequestURI(),
                responseWrapper.getStatus(), (endTime - startTime) / 1000.0,
                asJson(getHeaders((HttpServletRequest) request)),
                getRequestBody(requestWrapper),
                getResponseBody(responseWrapper));
    }

    public Map<String, String> getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(headerName -> headerName, request::getHeader));
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        String payload = null;
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                    payload = asJsonWithoutSpaces(payload)
;                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }
        }
        return payload;
    }

    private String getResponseBody(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, StandardCharsets.UTF_8);
                wrapper.copyBodyToResponse();
            }
        }
        return payload;
    }
}
