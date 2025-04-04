package com.john.library.mdm.common.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSourceConfig {

  // Remove the messageSource bean definition
  // @Bean
  // public MessageSource messageSource() {
  //     ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
  //     messageSource.setBasename("classpath:messages");
  //     messageSource.setDefaultEncoding("UTF-8");
  //     return messageSource;
  // }
}
