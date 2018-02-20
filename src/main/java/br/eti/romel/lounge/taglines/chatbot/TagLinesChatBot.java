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

import br.eti.romel.lounge.taglines.heroku.*;
import java.util.logging.*;
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
        String resultado = TagLinesWebApp.getLastTagLine();

        resposta.setChatId(mensagem.getChatId());
        resposta.setText(resultado);

        try {
            execute(resposta);
        } catch (TelegramApiException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, null, e);
        }
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
