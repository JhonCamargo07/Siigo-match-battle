package Controllers;

import ModelVO.*;
import java.io.*;
import ModelDAO.*;
import util.Files;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Esta clase se encarga de conectar las vistas con los modelos
 *
 * @author Jhon Camargo
 * @version 1.0.0
 */
@WebServlet(name = "PartidaController", urlPatterns = {"/Partida"})
public class PartidaController extends HttpServlet {

    public static final String URL_IMGS = Files.URL_FILES + "avatars\\";

    private List<String> img_avatars;

    public void init() {
        img_avatars = new ArrayList();
        File carpeta = new File(URL_IMGS);
        File[] lista = carpeta.listFiles();

        for (int i = 0; i < lista.length; i++) {
            if (lista[i].isFile()) {
                img_avatars.add(lista[i].getName());
            }
        }
        Collections.shuffle(img_avatars);
    }

    private ServletContext aplicacion = null;

    private JugadorVO jugadorVo = null;

    private HttpSession sesion = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Collections.shuffle(img_avatars);
        String codigoPartida = request.getParameter("codigoPartida");
        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: //Iniciar partida
                int cantidadJugadores = Integer.parseInt(request.getParameter("numPlayers"));
                this.iniciarPartida(request, response, cantidadJugadores, codigoPartida);
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

        List<PartidaVO> partidas = new ArrayList<>();

        String idJugador = request.getParameter("idJugador");
        String codigoPartida = request.getParameter("codigoPartida");
        String nombreJugador = request.getParameter("nombreJugador");

        jugadorVo = new JugadorVO(idJugador, nombreJugador, img_avatars.get(10), codigoPartida, true);

        PartidaVO partidaVo = new PartidaVO(codigoPartida, "10000", "en espera");

        sesion = request.getSession();
        sesion.setAttribute("jugadorVoSesion", jugadorVo);
        sesion.setAttribute("partidaVoSesion", partidaVo);

        partidas.add(partidaVo);

        // Agregar la partida a la lista de partidas que hay
        if (aplicacion.getAttribute("partidas") == null) {
            aplicacion.setAttribute("partidas", partidas);
        } else {
            List<PartidaVO> partidasActuales = (List<PartidaVO>) aplicacion.getAttribute("partidas");
            partidas.addAll(partidasActuales);
            aplicacion.setAttribute("partidas", partidas);
        }

        sesion = request.getSession();
        sesion.setAttribute("codigoPartida", codigoPartida);

        this.irASalaDeEspera(request, response, jugadorVo, codigoPartida);

    }

    private void irASalaDeEspera(HttpServletRequest request, HttpServletResponse response, JugadorVO jugadorVo, String codigoPartida) throws ServletException, IOException {

        aplicacion = request.getServletContext();

        List<JugadorVO> jugadores = new ArrayList();

        jugadores.add(jugadorVo);

        if (aplicacion.getAttribute("jugadoresOnline") == null) {
            aplicacion.setAttribute("jugadoresOnline", jugadores);
        } else {
            List<JugadorVO> jugadoresOnline = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");
            jugadores.addAll(jugadoresOnline);
            aplicacion.setAttribute("jugadoresOnline", jugadores);
        }

        this.generarMensage(request, response, "Todo listo para jugar", "Solo comparte el codigo de la partida para que se conecten mas jugadores", "saladeespera.jsp");

    }

    private void generarMensage(HttpServletRequest request, HttpServletResponse response, String titulo, String mensaje, String paginaRedirigir) throws ServletException, IOException {
        request.setAttribute("titulo", titulo);
        request.setAttribute("descripcion", mensaje);
        request.getRequestDispatcher(paginaRedirigir).forward(request, response);
    }

    private void ingresarAPartida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        aplicacion = request.getServletContext();

        String idJugador = request.getParameter("idJugador");
        String codigoPartida = request.getParameter("codigoPartida");
        String nombreJugador = request.getParameter("nombreJugador") == null ? "JaneDoe" : request.getParameter("nombreJugador");

        List<JugadorVO> jugadores = new ArrayList();

        Collections.shuffle(img_avatars);

        jugadorVo = new JugadorVO(idJugador, nombreJugador, img_avatars.get(0), codigoPartida);

        jugadores.add(jugadorVo);

        sesion = request.getSession();
        sesion.setAttribute("partidaVoSesion", obtenerVoDePartida(request, response, codigoPartida));
        sesion.setAttribute("jugadorVoSesion", jugadorVo);

        request.setAttribute("nombreJugador", nombreJugador);
        request.setAttribute("codigoPartida", codigoPartida);
        if (aplicacion.getAttribute("jugadoresOnline") != null) {
            List<JugadorVO> jugadoresOnline = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

            boolean isPartidaExiste = false;

            for (int i = 0; i < jugadoresOnline.size(); i++) {
                JugadorVO jugador = jugadoresOnline.get(i);
                if (jugador.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                    isPartidaExiste = true;
                }
                if (isPartidaExiste) {
                    break;
                }
            }

            if (isPartidaExiste) {
                jugadores.addAll(jugadoresOnline);
                aplicacion.setAttribute("jugadoresOnline", jugadores);
                this.generarMensage(request, response, "Todo listo para jugar", "Solo comparte el c\u00f3digo de la partida para que se conecten m\u00e1s jugadores", "saladeespera.jsp");
            } else {
                this.generarMensage(request, response, "No se encontr\u00f3 ninguna partida con ese c\u00f3digo", "No hay un partida que coincida con ese c\u00f3digo, por favor intentalo nuevamente", "ingresarPartida.jsp");
                return;
            }

        } else {
            System.out.println("Jugadores en la misma partida son 0");
            request.setAttribute("codigoPartida", codigoPartida);
            this.generarMensage(request, response, "No se encontr\u00f3 ninguna partida con ese c\u00f3digo", "No hay un partida que coincida con ese c\u00f3digo, por favor intentalo nuevamente", "ingresarPartida.jsp");
            return;
        }

    }

    private void usuariosEnPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) throws IOException, ServletException {

        aplicacion = request.getServletContext();

        request.setAttribute("codigoPartida", codigoPartida);

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

        List<JugadorVO> jugadoresEnLaMismaPartida = obtenerJugadoresEnMismaPartida(request, response, codigoPartida);

        try ( PrintWriter out = response.getWriter()) {
            for (JugadorVO jugadore : jugadoresEnLaMismaPartida) {
                out.print("<div class=\"bg-warning shadow d-flex justify-content-center align-items-center rounded\">");
                out.print("<img src=\"img/avatars/" + jugadore.getImagen() + "\" width=\"50px\" alt=\"alt\"/>");
                out.print("<div class=\"d-flex justify-content-center align-items-center flex-column p-2\">");
                out.print("<p class=\"mb-1 font_two\">" + jugadore.getIdjugador() + "</p>");
                out.print("<p class=\"mb-1\">" + jugadore.getNombre() + "</p>");
                out.print("</div>");
                out.print("</div>");
            }
        }

    }

    private void iniciarPartida(HttpServletRequest request, HttpServletResponse response, int cantidadJugadores, String codigoPartida) throws IOException {

        cambiarEstadoYCantidadJugadoresPartida(request, response, codigoPartida, cantidadJugadores);

        aplicacion = request.getServletContext();

        List<JugadorVO> jugadoresEnLaMismaPartida = obtenerJugadoresEnMismaPartida(request, response, codigoPartida);

        CartaDAO cartasDao = new CartaDAO();

        List<CartaVO> cartas = cartasDao.generarCartas(cantidadJugadores);

        List<List<CartaVO>> masos = cartasDao.generarMasoPorJugador(cartas, cantidadJugadores);

        for (int j = 0, i = 0; j < masos.size() && i < jugadoresEnLaMismaPartida.size(); j++, i++) {
            jugadoresEnLaMismaPartida.get(i).setBajara(masos.get(j));
        }

        try ( PrintWriter out = response.getWriter()) {
            out.print("<script>location.href = \"partida.jsp\";</script>");
        }

    }

    private void cambiarEstadoYCantidadJugadoresPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida, int cantidadJugadores) {

        aplicacion = request.getServletContext();

        List<PartidaVO> partidas = (List<PartidaVO>) aplicacion.getAttribute("partidas");
        List<PartidaVO> partidasActualizadas = new ArrayList();

        for (int i = 0; i < partidas.size(); i++) {
            PartidaVO partidaVo = partidas.get(i);
            if (partidaVo.getCodigo().equalsIgnoreCase(codigoPartida)) {
                partidaVo.setEstado("Jugando");
                partidaVo.setCanditadJugadores(cantidadJugadores);
            }
            partidasActualizadas.add(partidaVo);
        }

        aplicacion.setAttribute("partidas", partidasActualizadas);

    }

    private List<JugadorVO> obtenerJugadoresEnMismaPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) {
        aplicacion = request.getServletContext();

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");

        List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

        for (int i = 0; i < jugadores.size(); i++) {
            JugadorVO jugadorVo = jugadores.get(i);
            if (jugadorVo.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                jugadoresEnLaMismaPartida.add(jugadorVo);
            }
        }

        return jugadoresEnLaMismaPartida;

    }

    private PartidaVO obtenerVoDePartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) {

        PartidaVO partidaVo = new PartidaVO();

        aplicacion = request.getServletContext();

        if (aplicacion.getAttribute("partidas") != null) {
            List<PartidaVO> partidasActuales = (List<PartidaVO>) aplicacion.getAttribute("partidas");
            for (PartidaVO partida : partidasActuales) {
                if (partida.getCodigo().equalsIgnoreCase(codigoPartida)) {
                    partidaVo = partida;
                }
            }
        }

        return partidaVo;

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
}
