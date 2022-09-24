<%@page import="ModelVO.PartidaVO"%> <%@page import="ModelDAO.JugadorDAO"%> <% String codigoPartida = ""; if
(request.getAttribute("codigoPartida") != null) { codigoPartida = (String) request.getAttribute("codigoPartida"); } %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Crear partida</title>
		<jsp:include page="WEB-INF/paginas/comunes/head.jsp" />
	</head>

	<body>
		<div class="container d-flex justify-content-center align-items-center" style="height: 100vh">
			<div class="row my-5">
				<div class="col-md-12">
					<main class="p-5">
						<div class="">
							<a href="index.jsp">
								<i class="fas fa-undo"></i>
							</a>
							<h1 class="text-center display-1 text-white title">Siigo Match Battle</h1>
							<div class="text-center d-flex justify-content-around align-items-center pt-3">
								<div class="text-center">
									<img src="img/avatar1.png" class="img d-flex" />
								</div>
								<div class="form-group text-center col-4">
									<form action="${pageContext.request.contextPath}/Partida" method="POST">
										<label class="parrafo2">nombre o apodo</label>
										<input
											type="text"
											name="nombreJugador"
											class="form-control input"
											placeholder="Adriana2580" />
										<input type="hidden" name="idJugador" value="<%= JugadorDAO.generarCodigoJugador()%>" />
										<input
											type="hidden"
											name="codigoPartida"
											value="<%= PartidaVO.generarCodigoPartida()%>" />
										<!--<input type="hidden" name="nombreJugador">-->
										<input type="hidden" name="opcion" value="3" />
										<button
											type="submit"
											class="btn d-flex justify-content-start btn-light btn-login text-black titulo2 p-3 w-80 fw-bold btn-form text-size box">
											<i class="fas fa-play-circle"></i>Crear Partida
										</button>
									</form>
								</div>
							</div>
							<div class="text-center d-flex justify-content-around align-items-center pt-3">
								<div class="boton1"></div>
							</div>
							<div class="">
								<h4>${titulo}</h4>
								<p>${descripcion}</p>
							</div>
						</div>
					</main>
				</div>
			</div>
		</div>
	</body>
	<!-- Archivos js -->
	<jsp:include page="WEB-INF/paginas/comunes/files-js.jsp" />
</html>
