package ModelDAO;

import ModelVO.CartaVO;
import java.util.*;
import util.Files;

/**
 * Esta clase tiene los metodos para manejar las cartas
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
public class CartaDAO implements ICartaDAO {

    private static final int CANTIDAD_TOTAL_CARTAS = 32;
//    private static final int MAX_CARTAS = 12;
    private static final int MIN_CARTAS = 4;

    //====================================
    //======= Datos para las cartas ======
    //====================================
    private static final List<String> NUMEROS_CARTAS = Arrays.asList("1", "2", "3", "4");

    private static final List<String> LETRAS_CARTAS = Arrays.asList("A", "C", "D", "E", "F", "G", "H", "J");

    private static final List<String> TITULO_CARTAS = Arrays.asList("Lenovo 2022", "Azus 2015", "Samsung 2020", "Dell 2012", "Huawei 2015", "Acer 2017", "HP 2021", "Toshiba 2022", "Apple 2018", "Apple 2019", "Lenovo 2012", "Toshiba 2018", "Samsung 2021", "Huawei 2014", "Asus 2016", "HP 2020", "Acer ", "Apple 2018", "Intel 2019", "Ryzen 2020", "Print 15", "Motorola 2014", "Gigabyte 2050", "Asus for 4", "Pentium 8054", "MSI 1010", "Microsoft intel", "Acer multi", "Dell cuanty", "Toshiba 90k", "Sony cele", "Compac two");

    private static final List<String> BOARD_ASUS_CARTAS = Arrays.asList("560", "550", "540", "520", "570", "580", "590", "510", "530", "510", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520");

    private static final List<String> DISCO_DURO_CARTAS = Arrays.asList("250", "300", "500", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800", "250", "300", "500", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800", "250", "300", "500", "520", "700", "920", "1000", "2000");

    private static final List<String> PANTALLA_CARTAS = Arrays.asList("10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12");

    private static final List<String> PROCESADOR_INTEL_CARTAS = Arrays.asList("79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500");

    private static final List<String> RAM_CARTAS = Arrays.asList("4", "16", "14", "24", "8", "20", "32", "34", "18", "30", "12", "64", "40", "100", "72", "128", "45", "68", "18", "110", "21", "120", "10", "47", "16", "45", "68", "18", "110", "21", "120", "10");

    private List<String> identificadorCartas = new ArrayList();
    
    public List<String> generarIdentificadorCartas(){
        for (int i = 0; i < NUMEROS_CARTAS.size(); i++) {
            for (int j = 0; j < LETRAS_CARTAS.size(); j++) {
                identificadorCartas.add(NUMEROS_CARTAS.get(i) + "" + LETRAS_CARTAS.get(j));
            }
        }
        return identificadorCartas;
    }
    
    public List<CartaVO> generarCartas(int cantidadJugadores) {
        List<String> identificadorCartas = generarIdentificadorCartas();
        
        List<CartaVO> cartas = new ArrayList();
        
        List<String> nameImgs = Files.getListImgComputers();
        
        for (int j = 0; j < CANTIDAD_TOTAL_CARTAS; j++) {
                CartaVO cartaVo = new CartaVO(identificadorCartas.get(j), nameImgs.get(j) ,TITULO_CARTAS.get(j), PANTALLA_CARTAS.get(j), PROCESADOR_INTEL_CARTAS.get(j), RAM_CARTAS.get(j), DISCO_DURO_CARTAS.get(j), BOARD_ASUS_CARTAS.get(j));
                cartas.add(cartaVo);
        }

        return cartas;
    }

    public List<List<CartaVO>> generarMasoPorJugador(List<CartaVO> cartas, int cantidadJugadores) {
        List<List<CartaVO>> masos = new ArrayList();

        List<CartaVO> masoJugador = new ArrayList();

        int cantidadCartasPorJugador = (CANTIDAD_TOTAL_CARTAS / cantidadJugadores);

        for (int i = 0; i < cartas.size(); i++) {
            if (masoJugador.size() < cantidadCartasPorJugador) {
                CartaVO carta = cartas.get(i);
                masoJugador.add(carta);

            } else {
                masos.add(masoJugador);
                masoJugador = new ArrayList();
                i -= 1;
            }
        }
        if (masoJugador.size() > 0) {
            masos.add(masoJugador);
        }

        return masos;
    }

    @Override
    public void verCarta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> restarCartas(String idJugadorGanador, List<String> idJugadores
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean agregarCartas(String idJugadorGanador, List<String> idCartas
    ) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<CartaVO>> generarCartas2(int cantidadJugadores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
