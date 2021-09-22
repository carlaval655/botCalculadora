/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Windows
 */
public class Pantalla {
    public Pantalla() {
        
    }
    public String enviarMenu (){
        return "Bienvenido al Bot Calculadora.\nSeleccione una de las siguientes opciones: \n1. Sumar dos números. \n2. Calcular serie de fibonacci.";
    }
    public int eleccion (SendMessage mensajeEnviado, Update update){
        int opcion=0;
        try {
            opcion = Integer.parseInt(update.getMessage().getText());
        }
        catch (NumberFormatException e){
            opcion = 0;
        }
        return opcion;
    }
}
