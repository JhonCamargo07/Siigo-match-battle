package Controllers;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Este controlador se encargara de conectar las vistas con los modelos del
 * juego
 *
 * @author Karen
 * @version 1.0
 *
 */
@WebServlet(name = "JugadorController", urlPatterns = {"/JugadorController"})
public class JugadorController extends HttpServlet {

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
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        
        switch (opcion) {
            case 1: // Esperar inicio del juego (que se una la cantidad de jugadores posibles)
                aplicacion = request.getServletContext();
                this.salaDeEspera(request, response);
                break;
            default:
                this.accionPorDefault(request, response);
        }
    }

    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.generarMensaje(request, response, "Estas de vuelta", "De vuelta a inicio", "index.jsp");
    }

    public String obtenerNumRamdom(int max) {
        int num = (int)((Math.random() * (max - 0)) + 1);
        return String.valueOf(num);
    }

    private void generarMensaje(HttpServletRequest request, HttpServletResponse response, String titulo, String descripcion, String redirigir) throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("titulo", titulo);
        sesion.setAttribute("descripcion", descripcion);
        request.getRequestDispatcher(redirigir).forward(request, response);
    }

    private void salaDeEspera(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
