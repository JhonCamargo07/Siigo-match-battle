/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelVO;

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
    private String idjugador, nombre, imagen;

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

    /**
     * Generamos to String
     */
    @Override
    public String toString() {
        return "JugadorVO{" + "idjugador=" + idjugador + ", nombre=" + nombre + ", imagen=" + imagen + '}';
    }

}
