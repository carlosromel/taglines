/**
 * Copyright 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines.heroku;

import com.zaxxer.hikari.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
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
        String tag = getTagLine();

        if (!tag.isEmpty()) {
            model.put("tagLine", tag);
        } else {
            model.put("tagLine", "O banco de dados está vazio?");
        }

        return "index";
    }

    @RequestMapping(path = "/v1/tagline",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    Map<String, String> tagline(Map<String, Object> model) {
        Map<String, String> result = new HashMap();
        result.put("tagline", getTagLine());

        return result;
    }

    private String getTagLine() {
        String tag = "";
        String fairEnoughRandomTag = ""
                                     + "    update tagline t"
                                     + "       set usage_count = x.new_usage_count"
                                     + "      from (select t.tagline_id, t.usage_count + 1"
                                     + "              from tagline t"
                                     + "             where t.usage_count = (select min(usage_count)"
                                     + "                                      from tagline)"
                                     + "          order by random()"
                                     + "             limit 1) x (id, new_usage_count)"
                                     + "     where t.tagline_id = x.id"
                                     + " returning t.tag;";

        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(fairEnoughRandomTag);

            if (rs.next()) {
                tag = rs.getString("tag");
            }
        } catch (SQLException ex) {
        }

        return tag;
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
