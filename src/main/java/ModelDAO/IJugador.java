/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ModelDAO;

import java.util.List;

/**
 *
 * Esta interface tendra los metodos utilizados en el juego
 *
 * @author Karen
 * @version 1.0
 */
public interface IJugador {

    /**
     * Este metodo se encargara de crear la partida del juego
     *
     * @return String, retorna el codigo de la partida
     */
    public String crearPartida();

    /**
     * Este metodo se encargara de iniciar la partida del juego una vez ya este
     * el numero minido de participantes o el maximo
     */
    public void iniciarPartida();

    /**
     * Este metodo validara el ingreso a la Partida
     *
     * @param codigoMesa
     * 
     * @return boolean, retornara true o false y segun la respuesta se ingresara
     * o no a la partida
     *
     */
    public boolean ingresarPartida(String codigoMesa);

    /**
     *Este metodo retornara una lista con la caracteristica seleccionada
     * @return String, retornara un string con la caracteristica y valor seleccionada
     */
    
    public List<String>escogerCaracteristica();

}
