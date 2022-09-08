<%@include file="cache.jsp" %>


<%@page import="ModelVO.PartidaVO"%>
<%@page import="java.util.List"%>
<%    ServletContext aplicacion = request.getServletContext();
    if (aplicacion.getAttribute("partidas") != null) {
        List<PartidaVO> partidas = (List<PartidaVO>) aplicacion.getAttribute("partidas");
        String partida = (String) aplicacion.getAttribute("partida");
        System.out.println("exp = " + partida);
        System.out.println("partidas = " + partidas);
        for (PartidaVO elem : partidas) {
            if (!elem.getCodigo().equals(partida)) {
                response.sendRedirect("index.jsp");
            }
        }
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="${pageContext.request.contextPath}/Partida" method="POST">
            <input type="hidden" name="codigoPartida" value="${partida}">
            <input type="hidden" name="opcion" value="2">
            <input type="submit" value="Finalizar partida">
        </form>
        <div class="">
            <h4>${titulo}</h4>
            <p>${descripcion}</p>
        </div>
    </body>
</html>

