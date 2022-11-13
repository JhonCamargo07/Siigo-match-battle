package util;

import Controllers.PartidaController;
import java.io.File;

/**
 * Esta clase se encargará de manejar los archivos
 * @author Jhon Camargo
 * @version 1.0
 */
public class Files {
    public static final String URL_FILES = "C:\\cursos\\java\\Siigo-match-battle\\src\\main\\webapp\\img\\";
    
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

    public static String getNameImgRandom() {
        int numRandom = getNumRandom(getNumFileImg());
        File carpeta = new File(Files.URL_FILES + "avatars\\");
        File[] lista = carpeta.listFiles();
        int cuenta = 0;

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isFile() && i == numRandom) {
                return lista[i].getName();
            }
        }
        return "avatar1.png";
    }
}
