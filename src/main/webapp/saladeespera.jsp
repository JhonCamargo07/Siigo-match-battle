<%@page import="ModelVO.PartidaVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ModelVO.JugadorVO"%>
<%@page import="java.util.List"%>

<%
    HttpSession sesion = request.getSession();

    JugadorVO jugadorVoSession = new JugadorVO();
    PartidaVO partidaVoSesion = new PartidaVO();

    if (sesion.getAttribute("jugadorVoSesion") != null) {
        jugadorVoSession = (JugadorVO) sesion.getAttribute("jugadorVoSesion");
    }
    if (sesion.getAttribute("partidaVoSesion") != null) {
        partidaVoSesion = (PartidaVO) sesion.getAttribute("partidaVoSesion");
    }

    String titulo = "Todo listo para jugar";
    String mensaje = "Solo comparte el codigo de la partida (" + partidaVoSesion.getCodigo() + ") para que se conecten mas jugadores";
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Sala de Espera</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>

    <body class="position-relative">
        <div class="position-absolute div__cod-partida shadow-lg">
            <p class="mb-0"><%= partidaVoSesion.getCodigo()%></p>
        </div>
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
                                <input type="hidden" name="codigoPartida" id="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                            </div>
                            <div class="mt-3 text-white">
                                <h4 class="font_two"><%=titulo%></h4>
                                <p class="font_two"><%=mensaje%></p>
                            </div>
                            <%
                                ServletContext aplicacion = request.getServletContext();
                                List<JugadorVO> jugadores = new ArrayList();
                                if (aplicacion.getAttribute("jugadoresOnline") != null) {
                                    jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresOnline");
                                }
                                List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

                                for (int i = 0; i < jugadores.size(); i++) {
                                    JugadorVO jugadorVo = jugadores.get(i);
                                    if (jugadorVo.getCodigoPartida().equalsIgnoreCase(partidaVoSesion.getCodigo())) {
                                        jugadoresEnLaMismaPartida.add(jugadorVo);
                                    }
                                }

                                if (jugadoresEnLaMismaPartida.size() > 1 && jugadorVoSession.isCreadorDeLaPartida()) {
                            %>
                            <form action="${pageContext.request.contextPath}/Partida" method="POST" class="my-3">
                                <input type="hidden" name="opcion" value="1" />
                                <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                                <input type="hidden" name="numPlayers" id="numPlayers" value="<%= jugadoresEnLaMismaPartida.size()%>" />
                                <button type="submit" class="btn btn-success">Iniciar partida</button>
                            </form>
                            <%
                                }
                            %>
                            <div class="players mt-3" id="msg">
                                <%
                                    for (int i = 0; i < jugadoresEnLaMismaPartida.size(); i++) {
                                        JugadorVO player = jugadoresEnLaMismaPartida.get(i);
                                %>
                                <div class="bg-warning shadow d-flex justify-content-center align-items-center rounded">
                                    <img src="img/avatars/<%= player.getImagen()%>" width="50px" alt="alt"/>
                                    <div class="d-flex justify-content-center align-items-center flex-column p-2">
                                        <p class="mb-1 font_two"><%= player.getIdjugador()%></p>
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


    <script>
        setInterval(() => {
            location.href = "saladeespera.jsp";
        }, 15000);
    </script>

    <%
        if (jugadoresEnLaMismaPartida.size() < 7) {
    %> 
    <script>

        setInterval(() => {
            recargar();
        }, 5000);

        function recargar() {
            var parametro = {
                "opcion": 6,
                "codigoPartida": document.getElementById('codigoPartida').value
            }

            $.ajax({
                data: parametro,
                url: 'Partida',
                type: 'POST',
                success: function (response) {
                    $('#msg').html(response);
                }
            });
        }
    </script>
    <%
    } else if (jugadoresEnLaMismaPartida.size() >= 7) {
    %>
    <script>
        setInterval(() => {
            ingresarAPartida()
        }, 5000);

        function ingresarAPartida() {
            var parametro = {
                "opcion": 1,
                "numPlayers": document.getElementById('numPlayers').value,
                "codigoPartida": document.getElementById('codigoPartida').value
            }

            $.ajax({
                data: parametro,
                url: 'Partida',
                type: 'POST',
                success: function (response) {
                    $('#msg').html(response);
                }
            });
        }
    </script>
    <%
        }
    %>

    <%
        ServletContext app = request.getServletContext();

        if (app.getAttribute("partidas") != null) {
            List<PartidaVO> partidas = (List<PartidaVO>) app.getAttribute("partidas");

            for (PartidaVO partidaVo : partidas) {
                if (partidaVo.getCodigo().equalsIgnoreCase(partidaVoSesion.getCodigo())
                        && partidaVo.getEstado().equalsIgnoreCase("Jugando")) {
    %>
    <script>
        location.href = "partida.jsp";
    </script>
    <%
                }
            }
        }

    %>
</html>
