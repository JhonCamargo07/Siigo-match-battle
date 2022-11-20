<%@page import="Controllers.JugadorOnlineController"%>
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

    for (JugadorVO jugadorVo : jugadores) {
        if (jugadorVo.getCodigoPartida().equalsIgnoreCase(partidaVoSesion.getCodigo())) {
            jugadoresEnLaMismaPartida.add(jugadorVo);
        }
    }
    int turno = partidaVoSesion.getTurno();

    List<JugadorVO> jugadoresEliminados = new ArrayList();

    for (JugadorVO jugador : jugadoresEliminados) {
        if (jugador.getIdjugador().equalsIgnoreCase(jugadorVoSession.getIdjugador())) {
            String mensaje = "Cuidado, solo te queda una carta";
            if (jugador.getBajara().size() <= 0) {
                mensaje = "Lo siento, ya no tienes mas cartas";
            }


%>
<script>
    Toast.fire({
        icon: 'error',
        title: '<%= mensaje%>'
    })
</script>
<%        }
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
                    <%                        int contador = 0;
                        for (JugadorVO player : jugadoresEnLaMismaPartida) {
                            List<CartaVO> baraja = player.getBajara();
                            if (baraja.size() <= 0) {
                                player.setSuTurno(false);
                                turno++;
                                contador++;
                                jugadoresEliminados.add(player);
                                continue;
                            } else if (baraja.size() == 1) {
                                jugadoresEliminados.add(player);
                            }
                            if (contador == turno) {
                                player.setSuTurno(true);
                            } else {
                                player.setSuTurno(false);
                            }
                            if (contador > turno) {
                                break;
                            } else if (contador < turno) {
                                contador++;
                                continue;
                            }
                            contador++;
                            CartaVO primeraCarta = baraja.get(0);
                    %>

                    <%                        if (player.getIdjugador().equalsIgnoreCase(jugadorVoSession.getIdjugador()) && player.isSuTurno()) {

                    %>
                    <script>
                        setInterval(() => {
                            document.body.classList.add('carta-activa');
                        }, 10);
                    </script>
                    <%                        }
                    %>

                    <h5 class="card-title text-center titulo3"><%= primeraCarta.getIdentificador()%></h5>
                    <img src="img/computers/<%= primeraCarta.getImgComputador()%>" width="150px" class="" alt="" />
                    <div class="px-0 card-body">
                        <h5 class="card-title titulo3 text-center"><%= primeraCarta.getTitulo()%></h5>
                        <div class="card-parrafos">
                            <div class="">
                                <p>Procesador: <%= primeraCarta.getProcesador().charAt(0)%> <%= primeraCarta.getProcesador().substring(1, primeraCarta.getProcesador().length())%></p>
                                <p>Pantalla: <%= primeraCarta.getPantalla()%> pul</p>
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
            <div class="row mx-1">
                <div class="col-md-4"></div>
                <div class="col-md-4 marcador-tiempo shadow-lg">
                    <div class="p-0 py-2">
                        <h1 class="display-5 mt-1 fw-bold text-center text-white">0:50:35</h1>
                        <h5 class="text-center pt-2 pb-3 text-white m-0"><%= jugadoresEnLaMismaPartida.get(turno).getNombre().equalsIgnoreCase(jugadorVoSession.getNombre()) ? "Ahora es tu turno" : "Turno de " + jugadoresEnLaMismaPartida.get(turno).getNombre()%> </h5>
                    </div>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12 cuadro shadow-lg p-3 my-5 rounded">
                    <div class="players">
                        <%
                            for (JugadorVO player : jugadoresEnLaMismaPartida) {
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

    <script>
        setInterval(() => {
            location.href = "partida.jsp";
        }, 10000);

        const Toast = Swal.mixin({
            toast: false,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
    </script>

    <%
        if (request.getAttribute("nombreJugador") != null) {
            String jugadorGanador = (String) request.getAttribute("nombreJugador");
            String icono = "success";
            String frase = "El ganador eres t\u00fa";
            if (!jugadorGanador.equalsIgnoreCase(jugadorVoSession.getNombre())) {
                icono = "error";
                frase = "Perdiste, el ganador es " + jugadorGanador;
            }
    %>
    <script>
        Toast.fire({
            icon: '<%= icono%>',
            title: '<%= frase%>'
        })
    </script>
    <%
        }
    %>
</html>