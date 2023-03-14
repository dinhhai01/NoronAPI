//package com.mh.configuration;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//@Component
//@Configuration
//@ConfigurationProperties("spring.datasource")
//public class DataSourceCrawlerConfig extends HikariConfig {
////    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Bean
//    @Primary
//    public HikariDataSource dataSource() {
//        return new HikariDataSource(this);
//    }
//}
//
