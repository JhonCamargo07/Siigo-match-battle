<%@page import="ModelVO.JugadorVO" %>
    <%@page import="java.util.List" %>
        <%@include file="cache.jsp" %>

            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="UTF-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <!-- Iconos -->
                <script src="https://kit.fontawesome.com/dca352768f.js" crossorigin="anonymous"></script>
                <link rel="shortcut icon" type="image/x-icon" href="img/icono.png" />
                <!-- Estilos -->
                <link rel="stylesheet" href="css/bootstrap.css" />
                <link rel="stylesheet" href="css/style.css" />
                <link rel="stylesheet" href="css/sass.css" />
                <title>Index</title>
            </head>

            <body>
                <!-- Contenedor Principal -->
                <div class="text-roboto d-flex justify-content-center align-items-center">
                    <div class="container cuadro w-50  bg  overflow-hidden shadow-lg p-3  rounded">
                        <div class="row">
                            <h2 class="tituloprincipal text-center pt-5 mb-4">Siigo Match Battle</h2>
                            <div class="text-center">
                                <img src="img/cartas.gif" class="volver">
                            </div>

                            <div class="text-center d-flex justify-content-around align-items-center pt-3">
                                <div class="boton1">
                                    <a href="crearPartida.jsp"><button
                                            class="btn btn-light btn-login text-black titulo2 p-3  w-80 fw-bold  btn-form text-size box">
                                            <i class="fas fa-play-circle"></i>Crear Partida</button>
                                    </a>
                                </div>
                                <div class="boton2">

                                    <a href="ingresarPartida.jsp"><button type="submit"
                                            class="btn btn-light btn-login text-black titulo2 p-3  w-0 fw-bold  btn-form  box">
                                            <i class="fas fa-gamepad"></i>Ingresar a Partida</button>
                                    </a>

                                </div>


                            </div>
                        </div>
                    </div>
                </div>
                <!-- Archivos js -->
                <script src="js/popper.min.js"></script>
                <script src="js/jquery.min.js"></script>
                <script src="js/bootstrap.min.js"></script>


            </body>

            </html>