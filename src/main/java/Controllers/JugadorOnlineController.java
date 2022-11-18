package Controllers;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import ModelVO.JugadorVO;
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

    private void obtenerGanadorRonda(HttpServletRequest request, HttpServletResponse response) {
        String codigoPartida = request.getParameter("codigoPartida");
        String turno = request.getParameter("turno");
        String atributo = request.getParameter("atributo");
        List<JugadorVO> jugadoresEnLaMismaPartida = this.obtenerLosJugadoresEnLaMismaPartida(request, response, codigoPartida);
        
        List<JugadorVO> JugadoresEnMismaPartidaActualizada = this.obtenerGanadorRondaYActualizarListaJugadores(request, response, jugadoresEnLaMismaPartida, atributo);
    }

    private List<JugadorVO> obtenerLosJugadoresEnLaMismaPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) {
        aplicacion = request.getServletContext();

        List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

        List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadores");

        for (JugadorVO jugador : jugadores) {
            jugadoresEnLaMismaPartida.add(jugador);
        }

        return jugadoresEnLaMismaPartida;
    }

    private List<JugadorVO> obtenerGanadorRondaYActualizarListaJugadores(HttpServletRequest request, HttpServletResponse response, List<JugadorVO> jugadoresEnLaMismaPartida, String atributo) {
        List<JugadorVO> jugadoresEnLaMismaPartidaActualizada = new ArrayList();
        switch (atributo) {
            case "ram":
                
                break;
            case "pantalla":

                break;
            case "board":

                break;
            case "procesador":

                break;
            case "disco":

                break;
            default:
                throw new AssertionError();
        }
        return jugadoresEnLaMismaPartidaActualizada;
    }


}
