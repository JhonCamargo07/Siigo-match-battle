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
        
    </body>
</html>

