package com.umasuo.gateway.admin.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Davis on 17/8/25.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

  /**
   * Init filter.
   *
   * @param fc filter config
   * @throws ServletException exception
   */
  @Override
  public void init(FilterConfig fc) throws ServletException {
    // TODO: 17/8/29  
  }

  /**
   * Do filter.
   *
   * @param req request
   * @param resp response
   * @param chain filter chain
   * @throws IOException exception
   * @throws ServletException exception
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpServletRequest request = (HttpServletRequest) req;
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers",
        "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      String rqHeader =
          URLDecoder.decode(request.getHeader("Origin"), StandardCharsets.UTF_8.displayName());
      response.setHeader("Access-Control-Allow-Origin", rqHeader);
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, resp);
    }
  }

  /**
   * Destroy filter.
   */
  @Override
  public void destroy() {
    // TODO: 17/8/29
  }
}
