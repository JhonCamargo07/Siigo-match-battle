package ModelDAO;

import ModelVO.CartaVO;
import java.util.List;

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
    private static final char[] NUMEROS_CARTAS = {'1', '2', '3', '4'};
    private static final String[] LETRAS_CARTAS = {"A", "C", "D", "E", "F", "G", "H", "J"};
    private static final String[] TITULO_CARTAS = {"Lenovo 2022", "Azus 2015", "Samsung 2020", "Dell 2012", "Huawei 2015", "Acer 2017", "HP 2021", "Toshiba 2022", "Apple 2018", "Apple 2019", "Lenovo 2012", "Toshiba 2018", "Samsung 2021", "Huawei 2014", "Asus 2016", "HP 2020", "Acer ", "Apple 2018"};
    
    private static final String[] BOARD_ASUS_CARTAS = {"560", "550", "540", "520", "570", "580", "590", "510", "530", "510", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520"};
    
    private static final String[] DISCO_DURO_CARTAS = {"250", "300", "500", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800","250", "300", "500", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800", "250", "300", "500", "520", "700", "920", "1000", "2000",};
    

    private static final String[] PANTALLA_CARTAS = {"10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12"};

    private static final String[] PROCESADOR_INTEL_CARTAS = {"79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500"};

    
    private static final String[] RAM_CARTAS = {"4", "16", "14", "24", "8", "20", "32", "34", "18", "30", "12", "64", "40", "100", "72", "128", "45", "68", "18", "110", "21", "120", "10", "47", "16","45", "68", "18", "110", "21", "120", "10"};
    
    
    @Override
    public List<List<CartaVO>> generarCartas(int cantidadJugadores) {

        // Obtener el numero de cartas que debe tener cada jugador
        int cantidadCartasPorJugador = (CANTIDAD_TOTAL_CARTAS / cantidadJugadores);

        for (int i = 0; i <= cantidadCartasPorJugador; i++) {

        }

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void verCarta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> restarCartas(String idJugadorGanador, List<String> idJugadores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean agregarCartas(String idJugadorGanador, List<String> idCartas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
