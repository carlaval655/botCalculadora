/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
/**
 *
 * @author Windows
 */
public class Calculadora extends TelegramLongPollingBot {
    Suma suma = new Suma();
    int val = 1; //Valida la primera interaccion con el usuario
    SendMessage mensajeEnviado = new SendMessage();
    Pantalla pantalla = new Pantalla();
    int opcion = 0;
    public void inicializarBot(Update update){
        
    }
    @Override
    public String getBotToken() {
        return "2023303071:AAGm3zEb-mtOQjw-y2d3Ma07lBPPKiPGIPo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Llego mensaje: " + update.toString());
        if (val==1){
                mostrarMenuInicio(update);
                val++;
            }
        if(update.hasMessage()) {
            /*
            Procedemos a comprobar que mensaje envio el bot para de acuerdo a esto validar la respuesta del usuario
            */
            if (mensajeEnviado.getText().equals(pantalla.enviarMenu())){
                mensajeEnviado.setChatId(update.getMessage().getChatId().toString()); //Define a quien le vamos a enviar el mensaje
                opcion = pantalla.eleccion(mensajeEnviado, update);
                switch (opcion){
                    case 1:{
                        mensajeEnviado.setText("Introduzca el primer numero: ");
                        try {
                            execute(mensajeEnviado);
                        } 
                        catch (TelegramApiException ex) {
                            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }
                    break;
                    case 2:{
                        mensajeEnviado.setText("Funcionalidad no implementada. Intente otro dia. ");
                        try {
                            execute(mensajeEnviado);
                            mostrarMenuInicio(update);
                        } 
                        catch (TelegramApiException ex) {
                            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;
                    default:
                            mostrarMenuInicio(update);
                            break;
                }
            }
            else{
                if (mensajeEnviado.getText().equals("Introduzca el primer numero: ")){
                if(suma.validarNumero(update.getMessage().getText())==true){
                    suma.setNumero1(Integer.parseInt(update.getMessage().getText()));
                    mensajeEnviado.setText("Introduzca el segundo numero: ");
                    mensajeEnviado.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(mensajeEnviado);
                    } 
                    catch (TelegramApiException ex) {
                        Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    mostrarMenuInicio(update);
            }
                else{
                    if (mensajeEnviado.getText().equals("Introduzca el segundo numero: ")){
                if(suma.validarNumero(update.getMessage().getText())==true){
                    suma.setNumero2(Integer.parseInt(update.getMessage().getText()));
                    mensajeEnviado.setText("La suma es: "+suma.calcularSuma());
                    mensajeEnviado.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(mensajeEnviado);
                        mostrarMenuInicio(update);
                    } 
                    catch (TelegramApiException ex) {
                        Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    mostrarMenuInicio(update);
            }
            }
        }
    }
    }
    @Override
    public String getBotUsername() {
        return "Calculadora_est_bot";
    }
    
    public void mostrarMenuInicio(Update update){
                    mensajeEnviado.setText(pantalla.enviarMenu());
                    mensajeEnviado.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(mensajeEnviado);
                } 
                catch (TelegramApiException ex) {
                    Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}