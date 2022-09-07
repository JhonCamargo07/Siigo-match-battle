package ModelDAO;

import ModelVO.CartaVO;
import ModelVO.JugadorVO;
import ModelVO.PartidaVO;
import java.util.List;

/**
 * Esta interfaz se encargara de tener los metodos de los jugadores
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public interface IPartidaDAO {

    /**
     * Este metodo se encarga de repartir las cartas a cada jugador
     * @return List<List<MesaVO>>, retorna una lista de lista (paquete de cartas para cada jugador)
     */
    public List<List<PartidaVO>> repartirCartas();
    
    /**
     * Este metodo se encargara de seleccionar un jugador para que inicie la partida
     * @return int, retorna el id del jugador que debe iniciar la partida
     */    
    public int seleccionarJugador();
       
    /**
     * Este metodo se encarga de comparar la caracteristica que el jugador eligio por cada carta de cada jugador
     * @param caracteristica, se pide el nombre del atributo y el valor
     * @return int, retorna el id del jugador que gano
     */
    public int compararCaracteristicas(List<String> caracteristica);
    
    /**
     * Este metodo se encarga de seleccionar al jugador ganador de la ronda
     * @return int, retorna el ganador de la ronda
     */
    public int seleccionarGanador();
    
    /**
     * Este metodo se encarga de finalizar la partida
     * @return boolean, se pudo o no finalizar la partida
     */
    public boolean finalizarPartida(String codigoPartida);
    
    
    /**
     * Este metodo se encargara de crear la partida del juego
     *
     * @return String, retorna el codigo de la partida
     */
    public String crearPartida();

    /**
     * Este metodo se encargara de iniciar la partida del juego una vez ya este
     * el numero minido de participantes o el maximo
     * @return List<String>, retornara un String de los jugadores
     */
    public List<String> iniciarPartida(List<List<CartaVO>> barajas, List<JugadorVO> jugadores);
}
