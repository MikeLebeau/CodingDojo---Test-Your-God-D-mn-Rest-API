package com.oodrive.codingdojoapitest.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ConditionalOnProperty(name = "db.mode", havingValue = "postgres")
@Import({ DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class JdbcConfiguration {}
