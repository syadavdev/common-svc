package com.sandi.commonsvc.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.sandi.commonsvc.dto.ApiErrorDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authHeader = request.getHeader("authorization");
        if(request.getRequestURI().startsWith(request.getContextPath() + "/api")) {
            if (StringUtils.isBlank(authHeader) || authHeader.trim().length() < 7) {
                handleException(servletResponse, "Provide Token");
                return;
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

    protected void handleException(ServletResponse response, String ex) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        //ResponseEntity<ApiErrorDto> errorDTO = exceptionHandlerController.handleApplicationException(ex);
        //res.setStatus(errorDTO.getStatusCodeValue());
        res.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat());
        PrintWriter out = res.getWriter();
        //out.print(mapper.writeValueAsString(errorDTO));
        out.flush();
    }
}
