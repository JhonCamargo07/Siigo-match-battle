<%@page import="ModelVO.JugadorVO" %> <%@page import="java.util.List" %> <%@include file="cache.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inicio</title>
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="d-flex justify-content-center align-items-center my-lg-4 my-3">
                        <main class="p-5 my-5">
                            <h1 class="text-center display-1 py-3 text-white title">Siigo Match Battle</h1>
                            <div class="text-center">
                                <img src="img/cartas.gif" class="my-4 volver"/>
                            </div>
                            <div class="d-flex justify-content-around flex-wrap align-items-center pt-3 my-3">
                                <a href="crearPartida.jsp">
                                    <button type="submit" class="btn btn-light btn-lg w-100 my-1 py-4 px-5 shadow text-black p-3">
                                        <i class="fas fa-play-circle"></i>Crear Partida
                                    </button>
                                </a>
                                <a href="ingresarPartida.jsp">
                                    <button type="submit" class="btn btn-light btn-lg w-100 my-1 py-4 shadow text-black p-3">
                                        <i class="fas fa-gamepad"></i>Ingresar a Partida
                                    </button>
                                </a>
                            </div>
                            <div class="mt-3 text-white">
                                <h4 class="font_two">${titulo}</h4>
                                <p class="font_two">${descripcion}</p>
                            </div>
                        </main>
                    </div>
                </div>
            </div>
        </div>
        <!-- Contenedor Principal -->
    </body>
    <!-- Archivos js -->
    <jsp:include page="WEB-INF/paginas/comunes/files-js.jsp" />
</html>
