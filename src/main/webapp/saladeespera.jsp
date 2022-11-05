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
                    <div class="d-flex justify-content-center align-items-center" style="height: 100vh">
                        <main class="p-5 my-5">
                            <h1 class="text-center display-1 py-3 text-white title">Siigo Match Battle</h1>
                            <div class="text-center d-flex flex-wrap justify-content-evenly align-items-center pt-3">
                                <div class="text-center">
                                    <img src="img/reloj1.png" width="350px" class="img-fluid" />
                                </div>
                                <input type="hidden" id="codigoPartida" value="<%= codigoPartida%>" />
                            </div>
                            <div class="mt-3 text-white">
                                <h4>${titulo}</h4>
                                <p>${descripcion}</p>
                            </div>
                            <div id="msg">
                            </div>
                                <%
                                    ServletContext aplicacion = request.getServletContext();

                                    List<JugadorVO> jugadores = (List<JugadorVO>) aplicacion.getAttribute("jugadoresEnLaMismaPartida");

                                    List<JugadorVO> jugadoresEnLaMismaPartida = new ArrayList();

                                    for (int i = 0; i < jugadores.size(); i++) {
                                        JugadorVO jugadorVo = jugadores.get(i);
                                        if (jugadorVo.getCodigoPartida().equalsIgnoreCase(codigoPartida)) {
                                            jugadoresEnLaMismaPartida.add(jugadorVo);
                                        }
                                    }
                                %>

                            <%
                                if (jugadoresEnLaMismaPartida.size() > 1) {
                            %>
                            <form action="${pageContext.request.contextPath}/Partida" method="POST">
                                <input type="hidden" name="opcion" value="1" />
                                <button type="submit" class="btn btn-success">Iniciar partida</button><br><br>
                            </form>
                            <%
                                }
                                out.print(jugadoresEnLaMismaPartida.size());
                                out.print(jugadores);
                            %>

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
            var parametro = {
                "codigoPartida": document.getElementById('codigoPartida').value,
                "opcion": 6
            };

            $.ajax({
                data: parametro,
                url: 'Partida',
                type: 'POST',
                success: function (retur) {
                    location.href = "saladeespera.jsp"
                }
            });
        }
        ;
    </script>
    <%
        }
    %>
</html>
