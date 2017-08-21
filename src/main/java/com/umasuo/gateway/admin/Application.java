package com.umasuo.gateway.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringCloudApplication
@RestController
public class Application {

  @GetMapping("/")
  public String healthCheck() {
    return "Hello World";
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class).web(true).run(args);
  }
}