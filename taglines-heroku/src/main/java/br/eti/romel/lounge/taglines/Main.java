/**
 * Copyright 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines;

import com.zaxxer.hikari.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@SpringBootApplication
public class Main {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping("/")
    String index(Map<String, Object> model) {
        String fairEnoughRandomTag = ""
                                     + "  select t.tagline_id, t.tag, t.usage_count"
                                     + "    from tagline t"
                                     + "   where t.usage_count = (select min(usage_count)"
                                     + "                            from tagline)"
                                     + "order by random()"
                                     + "   limit 1";

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(fairEnoughRandomTag);

            if (rs.next()) {
                model.put("tagLine", rs.getString("tag"));

                stmt.executeUpdate(""
                                   + "update tagline"
                                   + "   set usage_count = usage_count + 1"
                                   + " where tagline_id = " + rs.getInt("tagline_id"));
            } else {
                model.put("tagLine", "O banco de dados está vazio?");
            }

            return "index";
        } catch (SQLException ex) {
            model.put("message", ex.getMessage());

            return "error";
        }
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
