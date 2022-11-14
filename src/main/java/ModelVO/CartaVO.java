package ModelVO;

import java.util.List;

/**
 * Esta clase es de dominiio, se encarga de manejar los atributos de la carta
 *
 * @author Aprendiz
 * @version 1.0.0
 */
public class CartaVO {

    private String identificador;
    private String imgComputador;
    private String titulo;
    private String pantalla;
    private String procesador;
    private String ram;
    private String discoDuro;
    private String motherBoard;

    public CartaVO() {
    }

    public CartaVO(String identificador) {
        this.identificador = identificador;
    }

    public CartaVO(String titulo, String pantalla, String procesador, String ram, String discoDuro, String discoMotherBoard) {
        this.titulo = titulo;
        this.pantalla = pantalla;
        this.procesador = procesador;
        this.ram = ram;
        this.discoDuro = discoDuro;
        this.motherBoard = discoMotherBoard;
    }

    public CartaVO(String identificador, String titulo, String pantalla, String procesador, String ram, String discoDuro, String discoMotherBoard) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.pantalla = pantalla;
        this.procesador = procesador;
        this.ram = ram;
        this.discoDuro = discoDuro;
        this.motherBoard = discoMotherBoard;
    }

    public CartaVO(String identificador, String imgComputador, String titulo, String pantalla, String procesador, String ram, String discoDuro, String motherBoard) {
        this.identificador = identificador;
        this.imgComputador = imgComputador;
        this.titulo = titulo;
        this.pantalla = pantalla;
        this.procesador = procesador;
        this.ram = ram;
        this.discoDuro = discoDuro;
        this.motherBoard = motherBoard;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }

    public String getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(String motherBoard) {
        this.motherBoard = motherBoard;
    }

    public String getImgComputador() {
        return imgComputador;
    }

    public void setImgComputador(String imgComputador) {
        this.imgComputador = imgComputador;
    }

    @Override
    public String toString() {
        return "CartaVO{" + "identificador=" + identificador + ", imgComputador=" + imgComputador + ", titulo=" + titulo + ", pantalla=" + pantalla + ", procesador=" + procesador + ", ram=" + ram + ", discoDuro=" + discoDuro + ", motherBoard=" + motherBoard + '}';
    }

}
