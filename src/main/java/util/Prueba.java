/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.File;
import Controllers.PartidaController;

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

    public static void main(String[] args) {
        int numRandom = getNumRandom(getNumFileImg());
        File carpeta = new File(PartidaController.URL_IMGS);
        File[] lista = carpeta.listFiles();
        int cuenta = 0;
    }
}
