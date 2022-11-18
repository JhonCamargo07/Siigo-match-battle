<%@page import="ModelVO.CartaVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ModelVO.PartidaVO"%>
<%@page import="ModelVO.JugadorVO"%>
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

    int turno = 0;
    if (request.getAttribute("turno") != null) {
        turno = Integer.parseInt(String.valueOf(request.getAttribute("turno")));
    }
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Partida</title>
        <!--<link rel="stylesheet" href="css/stylecard.css" />-->
        <link rel="stylesheet" href="css/sass.css" />
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>

    <body class="">

        <div class="carta">
            <div class="carta-div shadow-lg">
                <div class="container__jugador-card bg-light p-3">
                    <%
                        for (int i = 0; i < jugadoresEnLaMismaPartida.size(); i++) {
                            JugadorVO player = jugadoresEnLaMismaPartida.get(i);
                            List<CartaVO> baraja = player.getBajara();
                            CartaVO primeraCarta = baraja.get(0);
                            if (i == turno) {
                                player.setSuTurno(true);
                            } else if (i > turno) {
                                break;
                            } else if (i < turno) {
                                continue;
                            }
                    %>

                    <%                        if (player.getIdjugador().equalsIgnoreCase(jugadorVoSession.getIdjugador()) && player.isSuTurno()) {

                    %>
                    <script>
                        document.body.classList.add('carta-activa');
                    </script>
                    <%                        }
                    %>

                    <h5 class="card-title text-center titulo3"><%= primeraCarta.getIdentificador()%></h5>
                    <img src="img/computers/<%= primeraCarta.getImgComputador()%>" width="150px" class="" alt="" />
                    <div class="px-0 card-body">
                        <h5 class="card-title titulo3 text-center"><%= primeraCarta.getTitulo()%></h5>
                        <div class="card-parrafos">
                            <div class="">
                                <p>Pantalla: <%= primeraCarta.getPantalla()%> pul</p>
                                <p>Procesador: <%= primeraCarta.getProcesador().charAt(0)%> <%= primeraCarta.getProcesador().substring(1, primeraCarta.getProcesador().length())%></p>
                                <p>RAM: <%= primeraCarta.getRam()%> GB</p>
                                <p>Disco duro: <%= primeraCarta.getDiscoDuro()%> GB</p>
                                <p>MotherBoard: <%= primeraCarta.getMotherBoard()%></p>
                            </div>
                        </div>
                    </div>
                    <%
                        }

                    %>
                </div>
                <div class="carta-buttoms">
                    <div><h3>&#191;Cu&#225;l crees que es el atributo ganador?</h3></div>
                    <form action="${pageContext.request.contextPath}/JugadorOnline" method="POST">
                        <input type="hidden" name="opcion" value="1" />
                        <input type="hidden" name="turno" value="<%= turno%>" />
                        <input type="hidden" name="atributo" value="procesador" />
                        <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                        <button class="btn btn-danger btn-atribute px-3 py-2">Procesador</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/JugadorOnline" method="POST">
                        <input type="hidden" name="opcion" value="1" />
                        <input type="hidden" name="atributo" value="pantalla" />
                        <input type="hidden" name="turno" value="<%= turno%>" />
                        <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                        <button class="btn btn-danger btn-atribute px-3 py-2">Pantalla</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/JugadorOnline" method="POST">
                        <input type="hidden" name="opcion" value="1" />
                        <input type="hidden" name="atributo" value="ram" />
                        <input type="hidden" name="turno" value="<%= turno%>" />
                        <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                        <button class="btn btn-danger btn-atribute px-3 py-2">RAM</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/JugadorOnline" method="POST">
                        <input type="hidden" name="opcion" value="1" />
                        <input type="hidden" name="atributo" value="disco" />
                        <input type="hidden" name="turno" value="<%= turno%>" />
                        <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                        <button class="btn btn-danger btn-atribute px-3 py-2">Disco Duro</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/JugadorOnline" method="POST">
                        <input type="hidden" name="opcion" value="1" />
                        <input type="hidden" name="atributo" value="board" />
                        <input type="hidden" name="turno" value="<%= turno%>" />
                        <input type="hidden" name="codigoPartida" value="<%= partidaVoSesion.getCodigo()%>" />
                        <button class="btn btn-danger btn-atribute px-3 py-2">Mother Board</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12 cuadro shadow-lg p-3 my-5 rounded">
                    <div class="players">
                        <%                            for (int i = 0;
                                    i < jugadoresEnLaMismaPartida.size();
                                    i++) {
                                JugadorVO player = jugadoresEnLaMismaPartida.get(i);
                                List<CartaVO> baraja = player.getBajara();
                                CartaVO primeraCarta = baraja.get(0);
                        %>
                        <div class="container__jugador">
                            <div class="avatar">
                                <img src="img/avatars/<%= player.getImagen()%>" width="250px" class="img-fluid avatar__img" />
                                <p class="text-center text-white mt-1"><%= player.getNombre()%></p>
                                <div class="num_elements"><%= player.getBajara().size()%></div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>