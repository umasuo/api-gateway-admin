package com.umasuo.gateway.admin.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * CORS filter.
 */
@Component
public class CorsFilter extends ZuulFilter {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

  /**
   * Filter type.
   *
   * @return
   */
  @Override
  public String filterType() {
    // use "pre", so we can check the auth before router to back end services.
    return "pre";
  }

  /**
   * Filter order.
   *
   * @return int
   */
  @Override
  public int filterOrder() {
    return 6;
  }

  /**
   * Check if we need to run this filter for this request.
   *
   * @return boolean
   */
  @Override
  public boolean shouldFilter() {
    RequestContext ctx = getCurrentContext();
    String host = ctx.getRouteHost().getHost();
    HttpServletRequest request = ctx.getRequest();
    String method = request.getMethod();
    String path = request.getRequestURI();
    LOGGER.debug("Check for host: {}, path: {}, method: {}.", host, path, method);
    boolean shouldFilter = true;
    if ("OPTIONS".equals(method)) {
      LOGGER.debug("Ignore host: {}, Path: {}, action: {}.", host, path, method);
      shouldFilter = false;
    }
    return shouldFilter;
  }


  /**
   * Run function.
   *
   * @return always return null
   */
  @Override
  public Object run() {
    return null;
  }
}
