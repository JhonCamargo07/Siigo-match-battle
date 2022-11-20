package Controllers;

import ModelVO.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * Esta clase se encargara de los metodos de la partida, ya cuando se esta jugando
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
@WebServlet(name = "JugadorOnlineController", urlPatterns = {"/JugadorOnline"})
public class JugadorOnlineController extends HttpServlet {

    private ServletContext aplicacion = null;

    private JugadorVO jugadorVo = null;

    private HttpSession sesion = null;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigoPartida = request.getParameter("codigoPartida");
        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1:
                this.obtenerGanadorRonda(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void obtenerGanadorRonda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sesion = request.getSession();
        String codigoPartida = request.getParameter("codigoPartida");
        int turno = Integer.parseInt(request.getParameter("turno"));
        String atributo = request.getParameter("atributo");
        List<JugadorVO> jugadoresEnLaMismaPartida = this.obtenerLosJugadoresEnLaMismaPartida(request);

        List<JugadorVO> JugadoresEnMismaPartidaActualizada = this.getGanadorRondaYActualizarListaJugadores(request, response, jugadoresEnLaMismaPartida);

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

        jugadores.addAll(JugadoresEnMismaPartidaActualizada);

        aplicacion.setAttribute("jugadoresOnline", jugadores);

        this.cambiarTurnoAlSiguienteJugador(request, codigoPartida);
        request.getRequestDispatcher("partida.jsp").forward(request, response);

    }

    private List<JugadorVO> obtenerLosJugadoresEnLaMismaPartida(HttpServletRequest request) {
        String codigoPartida = request.getParameter("codigoPartida");
        aplicacion = request.getServletContext();

        List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

        for (JugadorVO jugador : jugadores) {
            if (jugador.getCodigoPartida().equalsIgnoreCase(codigoPartida) && !jugador.getBajara().isEmpty()) {
                jugadoresEnLaMismaPartida.add(jugador);
            }
        }

        return jugadoresEnLaMismaPartida;
    }

    private List<List<CartaVO>> obtenerElMasoDeCartasDeLosJugadoresEnLaMismaPartida(HttpServletRequest request, HttpServletResponse response, List<JugadorVO> jugadoresEnMismaPartida) {

        List<List<CartaVO>> masoCartas = new ArrayList();

        for (JugadorVO jugadorVO : jugadoresEnMismaPartida) {
            if (jugadorVO.getBajara().size() > 0) {
                masoCartas.add(jugadorVO.getBajara());
            }
        }

        return masoCartas;
    }

    private List<CartaVO> obtenerPrimeraCartadeCadaMasoDeLosJugadoresEnLaMismaPartida(HttpServletRequest request, HttpServletResponse response, List<List<CartaVO>> masoCartas) {

        List<CartaVO> primeraCartaDeCadaJugador = new ArrayList();

        for (List<CartaVO> masoCarta : masoCartas) {
            primeraCartaDeCadaJugador.add(masoCarta.get(0));
        }

        return primeraCartaDeCadaJugador;
    }

    private List<JugadorVO> getGanadorRondaYActualizarListaJugadores(HttpServletRequest request, HttpServletResponse response, List<JugadorVO> jugadoresEnLaMismaPartida) {
        String atributo = request.getParameter("atributo");

        List<List<CartaVO>> masoCartas = this.obtenerElMasoDeCartasDeLosJugadoresEnLaMismaPartida(request, response, jugadoresEnLaMismaPartida);

        List<CartaVO> primeraCartaDeCadaJugador = this.obtenerPrimeraCartadeCadaMasoDeLosJugadoresEnLaMismaPartida(request, response, masoCartas);

        String valorMaximoDelAtributo = String.valueOf(this.getValorMaximoAtributoDeUnaCarta(primeraCartaDeCadaJugador, atributo));

        String codigoJugadorGanador = this.getIdJugadorQueTengaLaCartaConElAtributoMasAlto(jugadoresEnLaMismaPartida, atributo, valorMaximoDelAtributo);

        this.eliminarJugadoresConDatosAntiguos(request);

        List<JugadorVO> jugadoresEnLaMismaPartidaActualizada = this.agregarCartasDeLosJugadoresPerdedoresAlGanador(request, jugadoresEnLaMismaPartida, primeraCartaDeCadaJugador, codigoJugadorGanador);

        request.setAttribute("nombreJugador", this.getNombreJugadorGanadorPorId(request, jugadoresEnLaMismaPartidaActualizada, request.getParameter("codigoPartida"), codigoJugadorGanador));

        return jugadoresEnLaMismaPartidaActualizada;
    }

    private int getValorMaximoAtributoDeUnaCarta(List<CartaVO> primeraCartaDeCadaJugador, String atributo) {

        int valorMaximoDelAtributo = 0;
        for (CartaVO cartaVO : primeraCartaDeCadaJugador) {
            switch (atributo) {
                case "ram":
                    if (Integer.parseInt(cartaVO.getRam()) > valorMaximoDelAtributo) {
                        valorMaximoDelAtributo = Integer.parseInt(cartaVO.getRam());
                    }
                    break;
                case "pantalla":
                    if (Integer.parseInt(cartaVO.getPantalla()) > valorMaximoDelAtributo) {
                        valorMaximoDelAtributo = Integer.parseInt(cartaVO.getPantalla());
                    }
                    break;
                case "board":
                    if (Integer.parseInt(cartaVO.getMotherBoard()) > valorMaximoDelAtributo) {
                        valorMaximoDelAtributo = Integer.parseInt(cartaVO.getMotherBoard());
                    }
                    break;
                case "procesador":
                    if (Integer.parseInt(cartaVO.getProcesador()) > valorMaximoDelAtributo) {
                        valorMaximoDelAtributo = Integer.parseInt(cartaVO.getProcesador());
                    }
                    break;
                case "disco":
                    if (Integer.parseInt(cartaVO.getDiscoDuro()) > valorMaximoDelAtributo) {
                        valorMaximoDelAtributo = Integer.parseInt(cartaVO.getDiscoDuro());
                    }
                    break;
                default:
                    valorMaximoDelAtributo = 0;
            }
        }
        return valorMaximoDelAtributo;
    }

    private String getIdJugadorQueTengaLaCartaConElAtributoMasAlto(List<JugadorVO> jugadoresEnLaMismaPartida, String atributo, String valorMaximoDelAtributo) {

        String idJugador = "0";
        for (JugadorVO jugadorVO : jugadoresEnLaMismaPartida) {
            switch (atributo) {
                case "ram":
                    if (jugadorVO.getBajara().get(0).getRam().equalsIgnoreCase(valorMaximoDelAtributo)) {
                        idJugador = jugadorVO.getIdjugador();
                    }
                    break;
                case "pantalla":
                    if (jugadorVO.getBajara().get(0).getPantalla().equalsIgnoreCase(valorMaximoDelAtributo)) {
                        idJugador = jugadorVO.getIdjugador();
                    }
                    break;
                case "board":
                    if (jugadorVO.getBajara().get(0).getMotherBoard().equalsIgnoreCase(valorMaximoDelAtributo)) {
                        idJugador = jugadorVO.getIdjugador();
                    }
                    break;
                case "procesador":
                    if (jugadorVO.getBajara().get(0).getProcesador().equalsIgnoreCase(valorMaximoDelAtributo)) {
                        idJugador = jugadorVO.getIdjugador();
                    }
                    break;
                case "disco":
                    if (jugadorVO.getBajara().get(0).getDiscoDuro().equalsIgnoreCase(valorMaximoDelAtributo)) {
                        idJugador = jugadorVO.getIdjugador();
                    }
                    break;
                default:
                    break;
            }
        }
        return idJugador;
    }

    private void eliminarJugadoresConDatosAntiguos(HttpServletRequest request) {
        aplicacion = request.getServletContext();

        String codigoPartida = request.getParameter("codigoPartida");

        List<JugadorVO> jugadoresActualizados = new ArrayList<>();

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

        for (JugadorVO jugador : jugadores) {
            if (!jugador.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                jugadoresActualizados.add(jugador);
            }
        }

        aplicacion.setAttribute("jugadoresOnline", jugadoresActualizados);
    }

    private List<JugadorVO> agregarCartasDeLosJugadoresPerdedoresAlGanador(HttpServletRequest request, List<JugadorVO> jugadoresEnLaMismaPartida, List<CartaVO> primeraCartaDeCadaJugador, String codigoJugadorGanador) {

        for (JugadorVO jugadorVO : jugadoresEnLaMismaPartida) {
            jugadorVO.getBajara().remove(0);
            if (jugadorVO.getIdjugador().equalsIgnoreCase(codigoJugadorGanador)) {
                jugadorVO.getBajara().addAll(primeraCartaDeCadaJugador);
            }
        }

        return jugadoresEnLaMismaPartida;
    }

    private void cambiarTurnoAlSiguienteJugador(HttpServletRequest request, String codigoPartida) {
        aplicacion = request.getServletContext();

        List<PartidaVO> partidas = (List<PartidaVO>) aplicacion.getAttribute("partidas");

        for (PartidaVO partida : partidas) {
            if (partida.getCodigo().equalsIgnoreCase(codigoPartida)) {
                if (partida.getTurno() >= this.contarCuantosJugadoresTienenCartasSegunPartida(request, codigoPartida) - 1) {
                    partida.setTurno(-1);
                }
                partida.setTurno(partida.getTurno() + 1);
            }
        }

    }

    private int contarCuantosJugadoresTienenCartasSegunPartida(HttpServletRequest request, String codigoPartida) {
        int jugadoresConCartas = 0;
        List<JugadorVO> jugadoresOnline = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");
        for (JugadorVO jugador : jugadoresOnline) {
            if (jugador.getCodigoPartida().equalsIgnoreCase(codigoPartida) && !jugador.getBajara().isEmpty()) {
                jugadoresConCartas++;
            }
        }
        return jugadoresConCartas;
    }

    public String getNombreJugadorGanadorPorId(HttpServletRequest request, List<JugadorVO> jugadoresEnLaMismaPartida, String codigoPartida, String idUser) {
        String nombreJugador = "Desconocido";

        for (JugadorVO jugadorVO : jugadoresEnLaMismaPartida) {
            if(jugadorVO.getCodigoPartida().equalsIgnoreCase(codigoPartida) && jugadorVO.getIdjugador().equalsIgnoreCase(idUser)){
                nombreJugador = jugadorVO.getNombre();
            }
        }
        return nombreJugador;
    }
}
