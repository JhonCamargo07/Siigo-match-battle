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
    private static final int CANTIDAD_TOTAL_CARTAS = 32;
//    private static final int MAX_CARTAS = 12;
    private static final int MIN_CARTAS = 4;

    List<PartidaVO> partidas = new ArrayList<>();

    List<CartaVO> cartas = null;

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
        String codigoPartida = request.getParameter("cod");
        this.ingresarAPartidad(request, response, codigoPartida);
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
        String codigoPartida = request.getParameter("codigoPartida");

        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: // Iniciar partida
                int cantidadJugadores = Integer.parseInt(request.getParameter("cantidadJugadores"));
                this.iniciarPartida(request, response, cantidadJugadores);

                break;
            case 2: // Eliminar a una partida
                this.terminarPartida(request, response, codigoPartida);
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

    private void iniciarPartida(HttpServletRequest request, HttpServletResponse response, int cantidadJugadores) throws IOException, ServletException {
        aplicacion = request.getServletContext();
        List<CartaVO> baraja = new ArrayList();

        int cantidadDeCartasPorJugador = CANTIDAD_TOTAL_CARTAS / cantidadJugadores;

        String codigoPartida = this.generarCodigoPartida();

        // Se guarda el codigo de la partida
        partidaVo = new PartidaVO(codigoPartida, "1:00:00");

        // Se agrega la partida a la lista
        partidas.add(partidaVo);

        // Se guardan las cartas
        cartas = cartaDao.generarCartas(cantidadJugadores);

        String nombreJugador = "";
        int contador = 0;

        for (int i = 0; i < cantidadJugadores; i++) {
//            for (int j = 0; j < cantidadDeCartasPorJugador; j++) {
//            contador++;
            nombreJugador = "nombreJugador" + i;
            jugadorVo = new JugadorVO(request.getParameter(nombreJugador), request.getParameter("nombreJugador" + i), obtenerNumRamdom(IMG_AVATAR.length), codigoPartida);
            for (CartaVO carta : cartas) {
                contador++;
                if (contador <= cantidadDeCartasPorJugador) {
                    carta.setCodigoPartida(codigoPartida);
                    jugadorVo.agregarCartas(carta);
                } else {
                    contador = 0;
                    break;
                }

//                }
            }
            jugadores.add(jugadorVo);
        }

//        // Generar la lista de los jugadores que van a la partida
//        for (int i = 0; i < cantidadJugadores; i++) {
//            for (int j = 1; j <= cantidadJugadores; j++) {
//            }
//        }
        aplicacion.setAttribute("partida", codigoPartida);
        aplicacion.setAttribute("partidas", partidas);

//        System.out.println("barajas = " + barajas);
        aplicacion.setAttribute("barajas", barajas);
        aplicacion.setAttribute("Stirng", "DFFSDGNDF");

//        for (List<CartaVO> baraja1 : barajas) {
//            System.out.println("baraja1 = " + baraja1);
//        }
//        aplicacion.setAttribute("baraja", baraja);
        aplicacion.setAttribute("jugadores", jugadores);

        request.getRequestDispatcher("index.jsp").forward(request, response);
//            response.sendRedirect("index.jsp");
//        }
    }

    private void ingresarAPartidad(HttpServletRequest request, HttpServletResponse response, String codigoPartida) throws ServletException, IOException {
        aplicacion = request.getServletContext();

        if (aplicacion.getAttribute("partidas") != null) {
            List<PartidaVO> partidas = (List<PartidaVO>) aplicacion.getAttribute("partidas");
            for (PartidaVO partida : partidas) {
                System.out.println("partida = " + partida);
                if (partida.getCodigo().equals(codigoPartida)) {
                    request.getRequestDispatcher("partida.jsp").forward(request, response);
                } else {
                    this.generarMensaje(request, response, "Partida no existe", "La partida no existe o ya culmin&#243;", "index.jsp");
                }
            }
        } else {
            this.generarMensaje(request, response, "Partida no existe", "La partida no existe o ya culmin&#243;", "index.jsp");
        }

    }

    private void generarMensaje(HttpServletRequest request, HttpServletResponse response, String titulo, String descripcion, String redirigir) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("titulo", titulo);
        sesion.setAttribute("descripcion", descripcion);
        request.getRequestDispatcher(redirigir).forward(request, response);
    }

    private void terminarPartida(HttpServletRequest request, HttpServletResponse response, String codigoPartida) throws ServletException, IOException {
        aplicacion = request.getServletContext();
        int posicion = 0;
        if (aplicacion.getAttribute("partidas") != null) {
            List<PartidaVO> partidas = (List<PartidaVO>) aplicacion.getAttribute("partidas");
            for (int i = 0; i < partidas.size(); i++) {
                System.out.println("posicion = " + posicion);
                if (partidas.get(i).getCodigo().equals(codigoPartida)) {
                    posicion = i;
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
                partidas.remove(posicion);
                System.out.println("partidas = " + partidas);
                aplicacion.setAttribute("partidas", partidas);

            }

        } else {
            this.generarMensaje(request, response, "Partida no existe", "La partida no existe o ya culmin&#243;", "index.jsp");
        }
    }
}
