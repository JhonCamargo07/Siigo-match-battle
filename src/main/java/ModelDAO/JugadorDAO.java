package ModelDAO;

import ModelVO.CartaVO;
import ModelVO.JugadorVO;
import java.util.List;

/**
 * * Esta clase tendra los metodos necesarios para la clase Jugador
 *
 * @author Karen
 * @version 1.0
 */
public class JugadorDAO {

    /**
     * Declarar variables e inicializarlas
     */
    private String idJugador = "", Nombre = "", Imagen = "";
    private List<CartaVO> cartas;

    /**
     * Crear constructor vacio
     */
    public JugadorDAO() {
    }

    /**
     * Crear metodo para recibir los datos del JugadorVO
     *
     * @param jugadorVO
     */
    public JugadorDAO(JugadorVO jugadorVO) {

        //Asignar valor a variable  con variables del VO
        idJugador = jugadorVO.getIdjugador();
        Nombre = jugadorVO.getNombre();
        List<String> listaNumeros = null;
        Imagen = jugadorVO.getIdjugador();
    }

    public static String generarCodigoJugador() {
        int num = (int) ((Math.random() * (999999998 - 0)) + 1);
        return String.valueOf(num);
    }

}
