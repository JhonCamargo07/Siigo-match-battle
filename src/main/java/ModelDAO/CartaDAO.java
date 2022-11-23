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
public class CartaDAO {

    private static final int CANTIDAD_TOTAL_CARTAS = 32;
    private static final int MIN_CARTAS = 4;

    //====================================
    //======= Datos para las cartas ======
    //====================================
    private static final List<String> NUMEROS_CARTAS = Arrays.asList("1", "2", "3", "4");

    private static final List<String> LETRAS_CARTAS = Arrays.asList("A", "C", "D", "E", "F", "G", "H", "J");

    private static final List<String> TITULO_CARTAS = Arrays.asList("Lenovo IdeaPad", "Azus Tuf", "Samsung GL Book", "Dell TabPro", "Huawei NoteBook", "Acer Ultrabook", "HP Book Flex", "Toshiba nitro", "NoteBook Swift", "NiPoGi mini", "Lenovo Inspirion", "Toshiba BMAX", "Samsung Book2", "Huawei MateBook", "Asus X415JA", "HP Surface", "Acer Aspire", "MacBook air", "Lenovo VPRO", "Microsoft surface", "Print CPK", "Dell ThinkPad", "Lenovo IdeaOad", "Asus Zenbook", "BEASTCOM Q3", "Jumper Full", "Admite KII", "Acer multi", "Dell cuanty", "Asus ChromeBook", "Sony ThinkBook", "Hp Inspiron 15");

    private static final List<String> BOARD_ASUS_CARTAS = Arrays.asList("560", "550", "540", "520", "570", "580", "590", "510", "530", "510", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520", "570", "580", "590", "510", "530", "560", "550", "540", "520");

    private static final List<String> DISCO_DURO_CARTAS = Arrays.asList("500", "300", "490", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800", "250", "300", "500", "520", "700", "920", "1000", "2000", "2100", "350", "400", "800", "250", "300", "500", "520", "700", "920", "1000", "2000");

    private static final List<String> PANTALLA_CARTAS = Arrays.asList("10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12", "9", "23", "11", "14", "18", "20", "13", "17", "16", "13", "10", "15", "10", "12");

    private static final List<String> PROCESADOR_INTEL_CARTAS = Arrays.asList("79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500", "34300", "57500", "912900", "33300", "910900", "56500", "711700", "33300", "79700", "53500");

    private static final List<String> RAM_CARTAS = Arrays.asList("16", "32", "12", "20", "8", "36", "32", "34", "18", "30", "12", "64", "40", "100", "72", "128", "45", "68", "18", "110", "21", "120", "10", "47", "16", "45", "68", "18", "110", "21", "120", "10");

    private final List<String> identificadorCartas = new ArrayList();

    public List<String> generarIdentificadorCartas() {
        for (int i = 0; i < NUMEROS_CARTAS.size(); i++) {
            for (int j = 0; j < LETRAS_CARTAS.size(); j++) {
                identificadorCartas.add(NUMEROS_CARTAS.get(i) + "" + LETRAS_CARTAS.get(j));
            }
        }
        return identificadorCartas;
    }

    public List<CartaVO> generarCartas(int cantidadJugadores) {
        List<String> idCartas = generarIdentificadorCartas();

        List<CartaVO> cartas = new ArrayList();

        List<String> nameImgs = Files.getListImgComputers();

        for (int j = 0; j < CANTIDAD_TOTAL_CARTAS; j++) {
            CartaVO cartaVo = new CartaVO(idCartas.get(j), nameImgs.get(j), TITULO_CARTAS.get(j), PANTALLA_CARTAS.get(j), PROCESADOR_INTEL_CARTAS.get(j), RAM_CARTAS.get(j), DISCO_DURO_CARTAS.get(j), BOARD_ASUS_CARTAS.get(j));
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
        if (!masoJugador.isEmpty()) {
            masos.add(masoJugador);
        }

        return masos;
    }

}
