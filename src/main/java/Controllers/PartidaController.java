package Controllers;

import ModelVO.JugadorVO;
import ModelVO.PartidaVO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigoPartida = request.getParameter("codigoPartida");
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        
        System.out.println("opcion = " + opcion);
        
        switch (opcion) {
            case 1: //Iniciar partida
                response.sendRedirect("partida.jsp");;
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
                this.usuariosEnPartida(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        List<JugadorVO> jugadores = new ArrayList();
        

        jugadorVo = new JugadorVO(idJugador, nombreJugador, "avatar2.png", codigoPartida);
        
        jugadores.add(jugadorVo);

        if (aplicacion.getAttribute("jugadoresEnLaMismaPartida") != null) {
            List<JugadorVO> jugadoresEnLaMismaPartida = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");
            jugadores.addAll(jugadoresEnLaMismaPartida);
            aplicacion.setAttribute("jugadoresEnLaMismaPartida", jugadores);
        }else{
            System.out.println("Jugadores en la misma partida son 0");
        }
        
        System.out.println("jugadores = " + jugadores);
        
        this.generarMensage(request, response, "Todo listo para jugar", "Solo comparte el codigo de la partida (" + codigoPartida + ") para que se conecten mas jugadores", "saladeespera.jsp");
    }

    private void usuariosEnPartida(HttpServletRequest request, HttpServletResponse response) {
        aplicacion = request.getServletContext();
        
        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");
        
        System.out.println("jugadores = " + jugadores);
        
    }

}
