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
     * @return List<String>, retornara un string con la caracteristica y valor seleccionada
     */
    
    public List<String>escogerCaracteristica();
    
}
