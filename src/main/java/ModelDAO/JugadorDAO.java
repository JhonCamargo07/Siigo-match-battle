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
public class JugadorDAO implements IJugador {

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
     */
    public JugadorDAO(JugadorVO jugadorVO) {

        //Asignar valor a variable  con variables del VO
        idJugador = jugadorVO.getIdjugador();
        Nombre = jugadorVO.getNombre();
        List<String> listaNumeros = null;
        Imagen = jugadorVO.getIdjugador();
    }

    @Override
    public boolean ingresarPartida(String codigoMesa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> escogerCaracteristica() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
