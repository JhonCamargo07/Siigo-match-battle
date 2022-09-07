<%@page import="ModelVO.JugadorVO"%>
<%@page import="java.util.List"%>
<%@include file="cache.jsp" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <h1>
            <form action="${pageContext.request.contextPath}/Partida" method="POST">
                <input type="text" name="nombreJugador1" value="Jacc">
                <input type="text" name="nombreJugador2" value="Martha">
                <input type="hidden" name="cantidadJugadores" value="7">
                <input type="hidden" name="opcion" value="1">
                <input type="submit" value="Enviar">
            </form>

            <p></p>
            <a href="${pageContext.request.contextPath}/Partida?cod=1234567">Ingresar</a>
            <p>${titulo}</p>
            <p>${descripcion}</p>
            <%                
                ServletContext aplicacion = request.getServletContext();

                if (aplicacion.getAttribute("partidas") != null) {

                    List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadores");

                    for (JugadorVO elem : jugadores) {
                     out.print(elem);
                     out.print("<br>");
                     out.print("<br>");
                     out.print("<br>");
                    }
                }
            %>


        </h1>
    </body>

</html>