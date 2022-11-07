<%@page import="java.util.ArrayList"%>
<%@page import="ModelVO.JugadorVO"%>
<%@page import="java.util.List"%>

<%
    HttpSession sesion = request.getSession();
    String codigoPartida = (String) sesion.getAttribute("codigoPartida");
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Sala de Espera</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class=" justify-content-center align-items-center">
                        <main class="p-5 mt-5">
                            <h1 class="text-center display-1 py-3 text-white title">Siigo Match Battle</h1>
                            <div class="text-center d-flex flex-wrap justify-content-evenly align-items-center pt-3">
                                <div class="text-center">
                                    <!-- <img src="img/reloj1.png" width="350px" class="img-fluid" /> -->
                                </div>
                            </div>
                            <div class="mt-3 text-white">
                                <h4>${titulo}</h4>
                                <p>${descripcion}</p>
                            </div>
                            <div id="msg">
                            </div>
                            <%
                                ServletContext aplicacion = request.getServletContext();
                                List<JugadorVO> jugadores = new ArrayList();
                                if (aplicacion.getAttribute("jugadoresEnLaMismaPartida") != null) {
                                    jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");
                                }
                                List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

                                for (int i = 0; i < jugadores.size(); i++) {
                                    JugadorVO jugadorVo = jugadores.get(i);
                                    if (jugadorVo.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                                        jugadoresEnLaMismaPartida.add(jugadorVo);
                                    }
                                }
                                if (jugadoresEnLaMismaPartida.size() > 1) {
                            %>
                            <form action="${pageContext.request.contextPath}/Partida" method="POST">
                                <input type="hidden" name="opcion" value="1" />
                                <input type="hidden" name="codigoPartida" id="codigoPartida" value="<%= codigoPartida%>" />
                                <input type="hidden" name="numPlayers" value="<%= jugadoresEnLaMismaPartida.size()%>" />
                                <button type="submit" class="btn btn-success">Iniciar partida</button><br><br>
                            </form>
                            <div class="players mt-3">
                                <%
                                    }
                                    // out.print(jugadoresEnLaMismaPartida.size());
//                                    out.print(jugadoresEnLaMismaPartida);
                                    for (int i = 0; i < jugadoresEnLaMismaPartida.size(); i++) {
                                        JugadorVO player = jugadoresEnLaMismaPartida.get(i);
                                %>
                                <!--<div class="bg-warning border-1 p-3">-->
                                <div class="bg-warning d-flex justify-content-center align-items-center border border-danger rounded">
                                    <img src="img/<%= player.getImagen()%>" width="50px" alt="alt"/>
                                    <div class="d-flex justify-content-center align-items-center flex-column p-2">
                                        <p class="mb-1"><%= player.getIdjugador()%></p>
                                        <p class="mb-1"><%= player.getNombre()%></p>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>

                        </main>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <!-- Archivos js -->
    <jsp:include page="WEB-INF/paginas/comunes/files-js.jsp" />


    <%
        if (jugadoresEnLaMismaPartida.size() < 7) {
    %> 
    <script>
        $(document).ready(function () {
            setInterval(() => {
                saludar();
            }, 5000);
        });
        function saludar() {
            location.href = "saladeespera.jsp";
        };
    </script>
    <%
        }
    %>
</html>
