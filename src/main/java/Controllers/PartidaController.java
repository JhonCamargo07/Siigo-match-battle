package Controllers;

import ModelDAO.CartaDAO;
import ModelVO.CartaVO;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import ModelVO.JugadorVO;
import ModelVO.PartidaVO;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Esta clase se encarga de conectar las vistas con los modelos
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
@WebServlet(name = "PartidaController", urlPatterns = {"/Partida"})
public class PartidaController extends HttpServlet {

    private static final String[] IMG_AVATAR = {"avartar1.png", "avatar2.png", "avatar3.png", "avatar4.png", "avatar5.png", "avatar6.png", "avatar7.png", "avatar8.png", "avatar9.png", "avatar10.png", "avatar11.png"};
    private static final int CANTIDAD_TOTAL_CARTAS = 32;

    List<PartidaVO> partidas = new ArrayList<>();

    ServletContext aplicacion = null;

    JugadorVO jugadorVo = null;

    HttpSession sesion = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigoPartida = request.getParameter("codigoPartida");
        int opcion = Integer.parseInt(request.getParameter("opcion"));

        System.out.println("opcion = " + opcion);

        switch (opcion) {
            case 1: //Iniciar partida
                int cantidadJugadores = 7;
                this.iniciarPartida(request, response, cantidadJugadores);
                break;
            case 2: //Eliminar partida

                break;
            case 3: //Crear partida
                this.crearPartida(request, response);
                break;
            case 4: //Sala de espera

                break;
            case 5: //Ingresar a partida
                this.ingresarAPartida(request, response);
                break;
            case 6: //Ver usuarios en partida
                System.out.println("Codigo partida: " + codigoPartida);
                this.usuariosEnPartida(request, response, codigoPartida);
                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void crearPartida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        aplicacion = request.getServletContext();
        String idJugador = request.getParameter("idJugador");
        String codigoPartida = request.getParameter("codigoPartida");
        String nombreJugador = request.getParameter("nombreJugador");

        jugadorVo = new JugadorVO(idJugador, nombreJugador, "avatar1.png", codigoPartida);

        PartidaVO partidaVo = new PartidaVO(codigoPartida, "1:00:00");

        partidas.add(partidaVo);

        // Agregar la partida a la lista de partidas que hay
        if (aplicacion.getAttribute("partidas") == null) {
            aplicacion.setAttribute("partidas", partidas);
        } else {
            List<PartidaVO> partidasActuales = (List<PartidaVO>) aplicacion.getAttribute("partidas");
            partidasActuales.addAll(partidas);
            aplicacion.setAttribute("partidas", partidasActuales);
        }

        sesion = request.getSession();
        sesion.setAttribute("codigoPartida", codigoPartida);

        this.irASalaDeEspera(request, response, jugadorVo, codigoPartida);

    }

    private void irASalaDeEspera(HttpServletRequest request, HttpServletResponse response, JugadorVO jugadorVo, String codigoPartida) throws ServletException, IOException {
        aplicacion = request.getServletContext();

        List<JugadorVO> jugadores = new ArrayList();

        jugadores.add(jugadorVo);

        if (aplicacion.getAttribute("jugadoresEnLaMismaPartida") == null) {
            aplicacion.setAttribute("jugadoresEnLaMismaPartida", jugadores);
        } else {
            List<JugadorVO> jugadoresEnLaMismaPartida = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");
            jugadores.addAll(jugadoresEnLaMismaPartida);
            aplicacion.setAttribute("jugadoresEnLaMismaPartida", jugadoresEnLaMismaPartida);
        }

        System.out.println("jugadores = " + jugadores);

        this.generarMensage(request, response, "Todo listo para jugar", "Solo comparte el codigo de la partida (" + codigoPartida + ") para que se conecten mas jugadores", "saladeespera.jsp");
    }

    private void generarMensage(HttpServletRequest request, HttpServletResponse response, String titulo, String mensaje, String paginaRedirigir) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("titulo", titulo);
        sesion.setAttribute("descripcion", mensaje);
        request.getRequestDispatcher(paginaRedirigir).forward(request, response);
    }

    private void ingresarAPartida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        aplicacion = request.getServletContext();
        String idJugador = request.getParameter("idJugador");
        String codigoPartida = request.getParameter("codigoPartida");
        String nombreJugador = request.getParameter("nombreJugador") == null ? "JaneDoe" : request.getParameter("nombreJugador");

        sesion = request.getSession();
        sesion.setAttribute("codigoPartida", codigoPartida);

        List<JugadorVO> jugadores = new ArrayList();

        jugadorVo = new JugadorVO(idJugador, nombreJugador, "avatar2.png", codigoPartida);

        jugadores.add(jugadorVo);

        if (aplicacion.getAttribute("jugadoresEnLaMismaPartida") != null) {
            List<JugadorVO> jugadoresEnLaMismaPartida = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");

            boolean isPartidaExiste = false;

            for (int i = 0; i < jugadoresEnLaMismaPartida.size(); i++) {
                JugadorVO jugador = jugadoresEnLaMismaPartida.get(i);
                if (jugador.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                    isPartidaExiste = true;
                }
                if (isPartidaExiste) {
                    break;
                }
            }

            if (isPartidaExiste) {
                jugadores.addAll(jugadoresEnLaMismaPartida);
                request.setAttribute("codigoPartida", codigoPartida);
                aplicacion.setAttribute("jugadoresEnLaMismaPartida", jugadores);
                this.generarMensage(request, response, "Todo listo para jugar", "Solo comparte el codigo de la partida (" + codigoPartida + ") para que se conecten mas jugadores", "saladeespera.jsp");
            } else {
                request.setAttribute("codigoPartida", codigoPartida);
                this.generarMensage(request, response, "No se encontro ninguna partida con ese codigo", "No hay un paritda que coincida con ese coodigo, por favor intentaloNuevamente", "ingresarPartida.jsp");
                return;
            }

        } else {
            System.out.println("Jugadores en la misma partida son 0");
            request.setAttribute("codigoPartida", codigoPartida);
            this.generarMensage(request, response, "No se encontro ninguna partida con ese codigo", "No hay un paritda que coincida con ese coodigo, por favor intentaloNuevamente", "ingresarPartida.jsp");
            return;
        }

    }

    private void usuariosEnPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) throws IOException, ServletException {
        aplicacion = request.getServletContext();

        request.setAttribute("codigoPartida", codigoPartida);

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");

//        System.out.println("jugadores = " + jugadores);
    }

    private void iniciarPartida(HttpServletRequest request, HttpServletResponse response, int cantidadJugadores) throws IOException {
        CartaDAO cartasDao = new CartaDAO();
        JugadorVO jugadorVo1 = new JugadorVO();
        JugadorVO jugadorVo2 = new JugadorVO();
        JugadorVO jugadorVo3 = new JugadorVO();
        JugadorVO jugadorVo4 = new JugadorVO();
        JugadorVO jugadorVo5 = new JugadorVO();
        JugadorVO jugadorVo6 = new JugadorVO();
        JugadorVO jugadorVo7 = new JugadorVO();

        List<JugadorVO> jugadores = new ArrayList();
        jugadores.add(jugadorVo1);
        jugadores.add(jugadorVo2);
        jugadores.add(jugadorVo3);
        jugadores.add(jugadorVo4);
        jugadores.add(jugadorVo5);
        jugadores.add(jugadorVo6);
        jugadores.add(jugadorVo7);

        List<CartaVO> cartas = cartasDao.generarCartas(cantidadJugadores);

        List<List<CartaVO>> masos = cartasDao.generarMasoPorJugador(cartas, cantidadJugadores);

        for (int j = 0, i = 0; j < masos.size() && i < jugadores.size(); j++, i++) {
            jugadores.get(i).setBajara(masos.get(j));
        }

        try ( PrintWriter out = response.getWriter()) {
            for (JugadorVO jugadore : jugadores) {
                out.print(jugadore);
                out.print("\n\n\n");
                out.print(jugadore.getBajara().size());
                out.print("\n\n\n");
            }
        }
    }

}
