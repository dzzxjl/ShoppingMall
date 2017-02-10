package com.dante.angular.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Dante on 2016/12/21.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.dante.angular"})
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
