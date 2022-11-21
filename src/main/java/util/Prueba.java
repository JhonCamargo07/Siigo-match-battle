/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import Controllers.PartidaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author jhona
 */
public class Prueba {

    public static int getNumFileImg() {
        File carpeta = new File(PartidaController.URL_IMGS);
        File[] lista = carpeta.listFiles();
        int cuenta = 0;

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isFile()) {
                cuenta++;
            }
        }
        return cuenta;
    }

    public static int getNumRandom(int max) {
        return (int) (Math.random() * max + 1);
    }

    private int h = 1, m, s, cs;

    public void main2() {
//        int numRandom = getNumRandom(getNumFileImg());
//        File carpeta = new File(PartidaController.URL_IMGS);
//        File[] lista = carpeta.listFiles();
//        int cuenta = 0;

        --h; 
       do {
            --s;
            if (s < 0) {
                s = 59;
                --m;
            }
            if (m < 0) {
                m = 59;
            }
            delaySegundo();
            printHora();
            if(h==0 && m == 0 & s == 0){
                break;
            }
        } while (h == 0 && m >= 0 && s >= -1);
    }

    public static void main(String[] args) {
        Prueba prueba = new Prueba();
        prueba.main2();
    }

    public void printHora() {
        System.out.println("Hora:" + (h <= 9 ? "0" : "") + h + ":" + (m <= 9 ? "0" : "") + m + ":" + (s <= 9 ? "0" : "") + s);
    }

    private void delaySegundo() {
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
