package ModelVO;

import java.util.List;

/**
 * Esta clase se utiliza para definir los parametros que se van a necesitar en
 * el transcurso del proyecto
 *
 * @author Karen
 * @version 1.0
 *
 */
public class JugadorVO {

    /**
     * Defininir variables
     */
    private String idjugador, nombre, imagen, codigoPartida;
    private List<CartaVO> bajara;

    /**
     * Generamos constructores
     */
    public JugadorVO() {
    }

    public JugadorVO(String idjugador, String nombre, String imagen) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public JugadorVO(String idjugador, String nombre, String imagen, String codigoPartida) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.codigoPartida = codigoPartida;
    }

    public JugadorVO(String idjugador, String nombre, String imagen, String codigoPartida, List<CartaVO> bajara) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.codigoPartida = codigoPartida;
        this.bajara = bajara;
    }

    /**
     * Generamos getter and setter de los parametros
     *
     * @return String
     */
    public String getIdjugador() {
        return idjugador;
    }

    public void setIdjugador(String idjugador) {
        this.idjugador = idjugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public List<CartaVO> getBajara() {
        return bajara;
    }

    public void setBajara(List<CartaVO> bajara) {
        this.bajara = bajara;
    }

    /**
     * Generamos to String
     */
    @Override
    public String toString() {
        return "JugadorVO{" + "idjugador=" + idjugador + ", nombre=" + nombre + ", imagen=" + imagen + ", codigoPartida=" + codigoPartida + ", bajara=" + bajara + '}';
    }

}
