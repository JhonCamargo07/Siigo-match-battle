package ModelDAO;

import ModelVO.CartaVO;
import java.util.List;

/**
 * Esta interfaz se encarga de tener los metodos de las cartas
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public interface ICartaDAO {

    /**
     * Este metodo se encarga de mostrar la primera carta del jugador
     */
    public void verCarta();

    /**
     * Este metodo se encarga de restarle a cada jugador perdedor la carta
     * principal
     *
     * @param idJugadorGanador, el identificador del jugador que gano la ronda
     * @param idJugadores, lista de los identificadores de los constructores
     * @return List<String>, retorna una lista con las cartas que se le quitaron
     * a cada jugador
     */
    public List<String> restarCartas(String idJugadorGanador, List<String> idJugadores);

    /**
     * Este metodo se encarga de agregar las cartas de los jugadores perdedores
     * al ganador
     *
     * @param idJugadorGanador, se pide el id del jugador ganador
     * @param idCartas, lista de los identificadores de las cartas
     * @return boolean, retorna si se pudo o agregar las cartas al jugador
     */
    public boolean agregarCartas(String idJugadorGanador, List<String> idCartas);
    

    /**
     * Este metodo se encarga de generar las 32 cartas y ponerlas en diferentes paquetes
     * @param cantidadJugadores, los numeros de jugadores que van a jugar la partida
     * @return List<List<CartaVO>>, retorna una lista con la lista de las cartas
     */
    public List<List<CartaVO>> generarCartas(int cantidadJugadores);
}
