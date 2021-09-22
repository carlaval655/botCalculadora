/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.est;

/**
 *
 * @author Windows
 */
public class Suma {
    int numero1 = 0;
    int numero2 = 0;
    public Suma(){
        
    }
    public int getNumero1(){
        return numero1;
    }
    public int getNumero2(){
        return numero2;
    }
    public void setNumero1(int numero1){
        this.numero1 = numero1;
    }
    public void setNumero2(int numero2){
        this.numero2 = numero2;
    }
    public int calcularSuma(){
        return (numero1+numero2);
    }
    public boolean validarNumero(String numstr){
        int num;
        try{
           num = Integer.parseInt(numstr);
           return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
