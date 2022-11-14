package ModelVO;

import Controllers.PartidaController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se utiliza para definir los parametros que se van a necesitar en el transcurso del proyecto
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
    private boolean creadorDeLaPartida, suTurno;
    private List<CartaVO> bajara = new ArrayList<>();

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
        this.bajara = new ArrayList();
    }

    public JugadorVO(String idjugador, String nombre, String imagen, String codigoPartida, boolean creadorDeLaPartida) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.codigoPartida = codigoPartida;
        this.creadorDeLaPartida = creadorDeLaPartida;
    }

    public JugadorVO(String idjugador, String nombre, String imagen, String codigoPartida, List<CartaVO> baraja, boolean creadorDeLaPartida) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.codigoPartida = codigoPartida;
        this.bajara = baraja;
        this.creadorDeLaPartida = creadorDeLaPartida;
    }

    public JugadorVO(String idjugador, String nombre, String imagen, String codigoPartida, List<CartaVO> baraja, boolean creadorDeLaPartida, boolean suTurno) {
        this.idjugador = idjugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.codigoPartida = codigoPartida;
        this.creadorDeLaPartida = creadorDeLaPartida;
        this.bajara = baraja;
        this.suTurno = suTurno;
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

    public void agregarCartas(CartaVO cartaVo) {
        this.bajara.add(cartaVo);
    }

    public void agregarBaraja(CartaVO cartaVo) {
        this.bajara.add(cartaVo);
    }

    public boolean isCreadorDeLaPartida() {
        return creadorDeLaPartida;
    }

    public void setCreadorDeLaPartida(boolean creadorDeLaPartida) {
        this.creadorDeLaPartida = creadorDeLaPartida;
    }

    public boolean isSuTurno() {
        return suTurno;
    }

    public void setSuTurno(boolean suTurno) {
        this.suTurno = suTurno;
    }

    @Override
    public String toString() {
        return "JugadorVO{" + "idjugador=" + idjugador + ", nombre=" + nombre + ", imagen=" + imagen + ", codigoPartida=" + codigoPartida + ", creadorDeLaPartida=" + creadorDeLaPartida + ", suTurno=" + suTurno + ", bajara=" + bajara + '}';
    }

}
