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
    private String estado;

    public PartidaVO() {
    }

    public PartidaVO(String codigo) {
        this.codigo = codigo;
    }

    public PartidaVO(String codigo, String tiempo) {
        this.codigo = codigo;
        this.tiempo = tiempo;
//        this.estado = "Creada";
    }
    
    public PartidaVO(String codigo, String tiempo, String estado) {
        this.codigo = codigo;
        this.tiempo = tiempo;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PartidaVO{" + "codigo=" + codigo + ", tiempo=" + tiempo + ", estado=" + estado + '}';
    }

    public static String generarCodigoPartida() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999);
        String hex = Integer.toHexString(randomNumber);
        return hex;
    }

}
