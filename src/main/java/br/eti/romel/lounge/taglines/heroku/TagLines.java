/**
 * Copyright 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines.heroku;

import br.eti.romel.lounge.taglines.chatbot.*;
import com.zaxxer.hikari.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class TagLines {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    protected DataSource dataSource;

    public static void main(String[] args) throws Exception {
        // WebApp, REST API
        SpringApplication.run(TagLinesWebApp.class, args);
        // Telegram ChatBot
        TagLinesChatBot.init();
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
