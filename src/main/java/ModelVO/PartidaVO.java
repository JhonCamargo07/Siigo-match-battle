package ModelVO;

import java.util.Date;

/**
 * Esta clase es de domio, se encarga de tener los atributos de la mesa
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public class PartidaVO {

    private String codigo;
    private Date tiempo;

    public PartidaVO() {
    }

    public PartidaVO(String codigo) {
        this.codigo = codigo;
    }

    public PartidaVO(Date tiempo) {
        this.tiempo = tiempo;
    }

    public PartidaVO(String codigo, Date tiempo) {
        this.codigo = codigo;
        this.tiempo = tiempo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "MesaVO{" + "codigo=" + codigo + ", tiempo=" + tiempo + '}';
    }

}
