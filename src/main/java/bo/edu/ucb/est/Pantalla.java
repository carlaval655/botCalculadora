/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Windows
 */
public class Pantalla {
    private int tipoRespuesta = 0;
    public Pantalla() {
    }
    public void mostrarMensajeInicial(SendMessage message){
        message.setText("Bienvenido al Bot Calculadora. ");
    }
    public void enviarMenu (SendMessage message){
        boolean flag = false;
        while (flag==false){
            try{
                message.setText("Seleccione una de las siguientes opciones: \n1. Sumar dos numeros. \n2. Calcular serie de fibonacci.");
            }
            catch (NumberFormatException ex){
            }
        } 
    }
    public void validarRespuesta (Update update, SendMessage message){
        boolean flag = false;
        while (flag==false){
            if (message.toString().equals("Seleccione una de las siguientes opciones: \n1. Sumar dos numeros. \n2. Calcular serie de fibonacci.")){
            try{
                int opcion = Integer.parseInt(update.getMessage().toString());
                flag = true;
            }
            catch (NumberFormatException e){
                
            }
        }
        else{
            if{
                
            }
            else
        }
        }
    }
}
