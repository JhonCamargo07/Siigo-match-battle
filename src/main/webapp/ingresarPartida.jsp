<%@page import="ModelDAO.JugadorDAO"%> <%@page import="ModelVO.PartidaVO"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Ingresar a partida</title>
        <!-- <link rel="stylesheet" href="css/styleingresar.css" /> -->
        <jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
    </head>

    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="d-flex justify-content-center align-items-center" style="height: 100vh">
                        <main class="p-5 my-5">
                            <a href="index.jsp" class="text-white">
                                <i class="fas fa-undo"></i>
                            </a>
                            <h1 class="text-center display-1 py-3 text-white title">Siigo Match Battle</h1>
                            <div class="text-center d-flex flex-wrap justify-content-around align-items-center pt-3">
                                <div class="text-center">
                                    <img src="img/avatar2.png" width="170px" class="img-fluid" />
                                </div>
                                <div class="mx-2">
                                    <form action="${pageContext.request.contextPath}/Partida" method="POST">
                                        <div class="my-3">
                                            <label for="codigo" class="form-label text-white my-3">Codigo partida</label>
                                            <input
                                                type="text"
                                                name="codigoPartida"
                                                id="codigo"
                                                class="form-control w-100"
                                                style="min-height: 55px" />
                                            <input type="hidden" name="idJugador" value="<%= JugadorDAO.generarCodigoJugador()%>" />
                                            <input
                                                type="hidden"
                                                name="codigoPartida"
                                                value="<%= PartidaVO.generarCodigoPartida()%>" />
                                            <input type="hidden" name="opcion" value="5" />
                                        </div>
                                        <div class="">
                                            <!--<a href="partida.jsp">-->
                                                <button
                                                    type="submit"
                                                    class="btn btn-light btn-lg w-100 my-1 px-4 shadow text-black p-3 w-100">
                                                    <i class="fas fa-gamepad"></i>Ingresar a Partida
                                                </button>
                                            <!--</a>-->
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="mt-3 text-white">
                                <h4>${titulo}</h4>
                                <p>${descripcion}</p>
                            </div>
                        </main>
                    </div>
                </div>
            </div>
        </div>
        <!-- Archivos js -->
        <jsp:include page="WEB-INF/paginas/comunes/files-js.jsp" />
    </body>
</html>
