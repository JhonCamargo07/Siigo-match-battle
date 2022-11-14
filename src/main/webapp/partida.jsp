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
%>

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
%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Partida</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
        <link rel="stylesheet" href="css/stylecard.css" />
        <link rel="stylesheet" href="css/sass.css" />
    </head>

    <body>

        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="players">
                        <%
                            for (int i = 0; i < jugadoresEnLaMismaPartida.size(); i++) {
                                JugadorVO player = jugadoresEnLaMismaPartida.get(i);
                                List<CartaVO> baraja = player.getBajara();
                                CartaVO primeraCarta = baraja.get(0);
                        %>
                        <div class="container__jugador">
                            <div class="container__jugador-card bg-light p-3">
                                <h5 class="card-title text-center titulo3"><%= primeraCarta.getIdentificador()%></h5>
                                <img src="img/computers/<%= primeraCarta.getImgComputador()%>" class="card-img-top" alt="">
                                <div class=" px-0 card-body">
                                    <h6 class="card-title titulo3 text-center"><%= primeraCarta.getTitulo()%></h6>
                                    <div class="card-parrafos">
                                        <div class="">
                                            <p>Pantalla:</p>
                                            <p>Procesador:</p>
                                            <p>RAM:</p>
                                            <p>Disco duro:</p>
                                            <p>MotherBoard:</p>
                                        </div>
                                        <div class="">
                                            <p><%= primeraCarta.getPantalla()%> pul</p>
                                            <p><%= primeraCarta.getProcesador()%></p>
                                            <p><%= primeraCarta.getRam()%> GB</p>
                                            <p><%= primeraCarta.getDiscoDuro()%> GB</p>
                                            <p><%= primeraCarta.getMotherBoard()%></p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="avatar">
                                <img src="img/avatars/<%= player.getImagen()%>" width="250px" class="img-fluid avatar__img" />
                                <p class="text-center text-white mt-1"><%= player.getNombre()%></p>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>

        <div class="text-roboto d-flex justify-content-center align-items-center">
            <div class="container cuadro w-75  bg  overflow-hidden shadow-lg p-2  rounded">
                <div class="row">
                    <div class="col-12 ">
                        <div class="d-flex align-items-center  justify-content-around mt-5">
                            <div class="bnts">
                                <div class="text-center col-12 mt-5">
                                    <button
                                        class="btn btn-white btn-login text-black p-1 parrafo2  w-10 fw-bold rounded-pill bg-light box">Pantalla
                                    </button>
                                </div>

                                <div class="text-center col-12 mt-5">
                                    <button
                                        class="btn btn-white btn-login text-black p-1 parrafo2  w-10 fw-bold rounded-pill bg-light box">Procesador
                                    </button>
                                </div>


                                <div class="text-center col-12 mt-5">
                                    <button
                                        class="btn btn-white btn-login text-black p-1 parrafo2  w-10 fw-bold rounded-pill bg-light box">RAM
                                    </button>
                                </div>

                                <div class="text-center col-12 mt-5">
                                    <button
                                        class="btn btn-white btn-login text-black p-1 parrafo2  w-10 fw-bold rounded-pill bg-light box">Disco
                                        Duro
                                    </button>
                                </div>
                                <div class="text-center col-12 mt-5">
                                    <button
                                        class="btn btn-white btn-login text-black p-1 parrafo2  w-10 fw-bold rounded-pill bg-light box">MotherBoard
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>

</html>