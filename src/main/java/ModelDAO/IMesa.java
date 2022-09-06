package ModelDAO;

import ModelVO.MesaVO;
import java.util.List;

/**
 * Esta interfaz se encargara de tener los metodos de los usuarios
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public interface IMesa {

    /**
     * Este metodo se encarga de repartir las cartas a cada jugador
     * @return List<List<MesaVO>>, retorna una lista de lista (paquete de cartas para cada jugador)
     */
    public List<List<MesaVO>> repartirCartas();
}
