package de.smava.homework.loanapplications.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER="Authorization";

  @Autowired
  private HttpServletRequest request;

  @Override
  public void apply(RequestTemplate requestTemplate) {
      requestTemplate.header(AUTHORIZATION_HEADER, request.getHeader(AUTHORIZATION_HEADER));
  }
}