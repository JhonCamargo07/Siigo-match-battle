package ModelVO;

import java.util.Date;
import java.util.Random;

/**
 * Esta clase es de domio, se encarga de tener los atributos de la mesa
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public class PartidaVO {

    private String codigo;
    private String tiempo;

    public PartidaVO() {
    }

    public PartidaVO(String codigo) {
        this.codigo = codigo;
    }

    public PartidaVO(String codigo, String tiempo) {
        this.codigo = codigo;
        this.tiempo = tiempo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "MesaVO{" + "codigo=" + codigo + ", tiempo=" + tiempo + '}';
    }
    
    public static String generarCodigoPartida() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999);
        String hex = Integer.toHexString(randomNumber);
        return hex;
    }

}
