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
package br.eti.romel.lounge.taglines.chatbot;

import com.google.gson.*;
import com.google.gson.stream.*;
import java.io.*;
import java.util.logging.*;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.telegram.telegrambots.*;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.exceptions.*;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
public class TagLinesChatBot extends TelegramLongPollingBot {

    private final String url = "https://taglines.herokuapp.com/v1/tagline";

    public static void init() {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new TagLinesChatBot());
        } catch (TelegramApiException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message mensagem = update.getMessage();
        SendMessage resposta = new SendMessage();
        String resultado = getNewTagLine();

        resposta.setChatId(mensagem.getChatId());
        resposta.setText(resultado);

        try {
            execute(resposta);
        } catch (TelegramApiException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
        }
    }

    public String getNewTagLine() {
        TagLine tag = new TagLine();

        try (CloseableHttpClient httpClient = new DefaultHttpClient()) {
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(content);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            JsonReader jsonReader = new JsonReader(bufferedReader);

            tag = new Gson().fromJson(jsonReader, TagLine.class);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
        }

        return tag.tagLine;
    }

    @Override
    public String getBotUsername() {

        return "TagLinesBot";
    }

    @Override
    public String getBotToken() {

        return System.getenv("TAGLINES_BOT_API");
    }
}

class TagLine {

    String tagLine = "";
}
