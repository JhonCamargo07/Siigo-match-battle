package Controllers;

import ModelDAO.*;
import ModelVO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
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

    private static final String[] IMG_AVATAR = {"avartar1.png", "avavar2.png", "avatar3.png", "avatar4.png", "avatar5.png", "avatar6.png", "avatar7.png", "avatar8.png", "avatar9.png", "avatar10.png", "avatar11.png"};

    PartidaDAO partidaDao = new PartidaDAO();
    CartaDAO cartaDao = new CartaDAO();

    JugadorVO jugadorVo = null;
    List<JugadorVO> jugadores = new ArrayList();
    PartidaVO partidaVo = null;
    CartaVO cartaVo = null;
    
    List<List<CartaVO>> barajas = new ArrayList<>();
    
    ServletContext aplicacion = null;

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
        this.accionPorDefault(request, response);
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

//        String idJugador1 = request.getParameter("idJugador1");
//        String idJugador2 = request.getParameter("idJugador2");
//        String idJugador3 = request.getParameter("idJugador3");
//        String idJugador4 = request.getParameter("idJugador4");
//        String idJugador5 = request.getParameter("idJugador5");
//        String idJugador6 = request.getParameter("idJugador6");
//        String idJugador7 = request.getParameter("idJugador7");
//        String nombreJugador1 = request.getParameter("nombreJugador1");
//        String nombreJugador2 = request.getParameter("nombreJugador2");
//        String nombreJugador3 = request.getParameter("nombreJugador3");
//        String nombreJugador4 = request.getParameter("nombreJugador4");
//        String nombreJugador5 = request.getParameter("nombreJugador5");
//        String nombreJugador6 = request.getParameter("nombreJugador6");
//        String nombreJugador7 = request.getParameter("nombreJugador7");

        int cantidadJugadores = Integer.parseInt(request.getParameter("cantidadJugadores"));

        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: // Iniciar partida

                this.iniciarPartida(request, response, cantidadJugadores);

                break;
            default:
                this.accionPorDefault(request, response);
        }

    }

    
    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try ( PrintWriter out = response.getWriter()) {
            out.print("De vuelta al juego");
        }
    }

    public static String generarCodigoPartida() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999);
        String hex = Integer.toHexString(randomNumber);
        return hex;
    }

    public String obtenerNumRamdom(int max) {
        double num = ((Math.random() * (max - 0)) + 1);
        return String.valueOf(num);
    }

    private void iniciarPartida(HttpServletRequest request, HttpServletResponse response, int cantidadJugadores) throws IOException {

        aplicacion = request.getServletContext();
        
        List<CartaVO> baraja = new ArrayList();
        
        String codigoPartida = this.generarCodigoPartida();
        
        // Se guarda el codigo de la partida
        partidaVo = new PartidaVO(codigoPartida, "1:00:00");

        aplicacion.setAttribute("partida", partidaVo);
        
        barajas = cartaDao.generarCartas(cantidadJugadores);
        
        System.out.println("barajas = " + barajas);
        
        aplicacion.setAttribute("barajas", barajas);
        aplicacion.setAttribute("Stirng", "DFFSDGNDF");
                
        String nombreJugador = "";
        // Generar la lista de los jugadores que van a la partida
        for (int i = 0; i < cantidadJugadores; i++) {
            for (int j = 1; j <= 7; j++) {
                nombreJugador = "nombreJugador" + j;
                jugadorVo = new JugadorVO(request.getParameter(nombreJugador), request.getParameter("nombreJugador" + i), obtenerNumRamdom(IMG_AVATAR.length), codigoPartida, baraja);
                jugadores.add(jugadorVo);
                
            }
        }
        
        for (List<CartaVO> baraja1 : barajas) {
            
        }
        
//        aplicacion.setAttribute("baraja", baraja);
        aplicacion.setAttribute("jugadores", jugadores);
        
        response.sendRedirect("index.jsp");
    }

}
