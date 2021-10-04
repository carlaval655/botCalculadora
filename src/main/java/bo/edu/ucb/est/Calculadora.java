/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    /**
     * estado = 0 >>> Debo mostrar menu inicial
     * estado = 1 >>> Pedir un numero del menu de opciones
     * estado = 2 >>> Menu sumar y debo pedir primer numero
     * estado = 3 >>> Menu sumar y pidiendo segundo numero
     * estado = 4 >>> Menu fibonacci
     */
    private Map estadoUsuario = new HashMap();
    private Map numeroUnoUsuario = new HashMap();
    private Map numeroDosUsuario = new HashMap();

    
    @Override
    public String getBotToken() {
        return "2023303071:AAGm3zEb-mtOQjw-y2d3Ma07lBPPKiPGIPo";
    }

    @Override
        public void onUpdateReceived(Update update) {
        Long userId = update.getMessage().getChatId();
        Integer estado = (Integer) estadoUsuario.get(userId);
        if (estado == null){
            estado = 0;
            estadoUsuario.put(userId,estado);
        }
        System.out.println(update.getMessage().toString());
        if(update.hasMessage()) {
            
            String mensajeAMostrar = null;
            String menuInicial = "Bienvenido al Bot Calculadora!\nElija una opcion\n1. Sumar dos numeros.\n2. Fibonacci.\n Por favor elija una opcion: ";
            String primerNumero = "Ingrese el primer numero";
            String segundoNumero = "Ingrese el segundo numero";
            String resultadoSuma = "El resultado es: ";
            String fibonacci = "Fibonacci aun no esta disponible retorne otro dia";
            String mensajeUsuario = null;
            switch (estado){
                case 0: 
                mostrarMensaje(menuInicial, String.valueOf(userId));
                estadoUsuario.put(userId,1);
                break;
                case 1: 
                    mensajeUsuario = update.getMessage().getText();
                    try {
                        int opcion = Integer.parseInt(mensajeUsuario);
                        if(opcion==1 || opcion==2){
                            if (opcion==1){ //Quiere sumar dos numeros
                                mostrarMensaje(primerNumero, String.valueOf(userId));
                                estadoUsuario.put(userId,2);
                            }
                            else {
                                mostrarMensaje(fibonacci, String.valueOf(userId));
                                mostrarMensaje(menuInicial, String.valueOf(userId));
                                estadoUsuario.put(userId,1);
                            }
                        }
                        else{
                            mostrarMensaje(menuInicial, String.valueOf(userId));
                            estadoUsuario.put(userId,1);
                        }
                    }
                    catch(Exception ex){
                        mostrarMensaje(menuInicial, String.valueOf(userId));
                        estadoUsuario.put(userId,1);
                    }
                    break;
                case 2:
                     mensajeUsuario = update.getMessage().getText();
                     try {
                        numeroUnoUsuario.put(userId,Integer.parseInt(mensajeUsuario));
                        mostrarMensaje(segundoNumero, String.valueOf(userId));
                        estadoUsuario.put(userId,3);
                    }
                    catch(Exception ex){
                        mostrarMensaje(menuInicial, String.valueOf(userId));
                        estadoUsuario.put(userId,0);
                    }
                     break;
                case 3:
                     mensajeUsuario = update.getMessage().getText();
                     try {
                        int numeroDos = Integer.parseInt(mensajeUsuario);
                        int numeroUno = (int) numeroUnoUsuario.get(userId);
                        mostrarMensaje(resultadoSuma + (numeroUno + numeroDos), String.valueOf(userId));
                        mostrarMensaje(menuInicial, String.valueOf(userId));
                        estadoUsuario.put(userId,1);
                    }
                    catch(Exception ex){
                        mostrarMensaje(menuInicial, String.valueOf(userId));
                        estadoUsuario.put(userId,1);
                    }
                     break;
                default:
                    mostrarMensaje(menuInicial, String.valueOf(userId));
                    estadoUsuario.put(userId,1);
                    break;
            }
            
        }
    }
    @Override
    public String getBotUsername() {
        return "Calculadora_est_bot";
    }
    public void mostrarMensaje(String mensaje, String Id){
        SendMessage message = new SendMessage();
        message.setChatId(Id);
        message.setText(mensaje);
            message.setChatId(Id+"");
            try {
                execute(message);
            } catch (TelegramApiException ex) {
                
            }
    }
}