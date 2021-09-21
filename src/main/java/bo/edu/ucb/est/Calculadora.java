/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.List;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author Windows
 */
public class Calculadora extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "2023303071:AAGm3zEb-mtOQjw-y2d3Ma07lBPPKiPGIPo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if(update.hasMessage()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
            Pantalla pantalla = new Pantalla();
            pantalla.enviarMenu(message);
            pantalla.validarRespuesta(update);
            //message.setText("Hello " + update.getMessage().getFrom().getFirstName());
            try {
                execute(message); // Envia el mensaje
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Calculadora_est_bot";
    }
    
}