/**
 * Copyright (C) 2018 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package br.eti.romel.lounge.taglines.heroku;

import java.sql.*;
import java.util.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Controller
@SpringBootApplication
public class TagLinesWebApp extends TagLines {

    private static String lastTagLine = "";

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

    public String getTagLine() {
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
                this.lastTagLine = rs.getString("tag");
            }
        } catch (SQLException ex) {
        }

        return this.lastTagLine;
    }

    public static String getLastTagLine() {

        return TagLinesWebApp.lastTagLine;
    }
}
