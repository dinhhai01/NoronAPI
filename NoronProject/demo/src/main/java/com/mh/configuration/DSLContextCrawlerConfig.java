//package com.mh.configuration;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.jooq.DSLContext;
//import org.jooq.SQLDialect;
//import org.jooq.conf.Settings;
//import org.jooq.impl.DSL;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
////@Qualifier("DSLContextCrawlerConfig")
//public class DSLContextCrawlerConfig {
//    private final HikariDataSource dataSource;
//
//    public DSLContextCrawlerConfig(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Primary
//    @Bean
//    public DSLContext dslContext() {
//        Settings settings = new Settings().withRenderSchema(true);
//        return DSL.using(dataSource, SQLDialect.MYSQL, settings);
//    }
//}