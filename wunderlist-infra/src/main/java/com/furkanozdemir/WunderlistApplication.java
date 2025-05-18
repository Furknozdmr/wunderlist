package com.furkanozdemir;


import com.furkanozdemir.common.DomainComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainComponent.class})})
public class WunderlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WunderlistApplication.class, args);
    }
}