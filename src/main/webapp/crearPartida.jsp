<%@page import="ModelVO.PartidaVO"%> <%@page import="ModelDAO.JugadorDAO"%> <% String codigoPartida = ""; if
(request.getAttribute("codigoPartida") != null) { codigoPartida = (String) request.getAttribute("codigoPartida"); } %>
<!DOCTYPE html>
<html lang="es">
	<head>
		<title>Crear partida</title>
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
							<div class="text-center d-flex flex-wrap justify-content-evenly align-items-center pt-3">
								<div class="text-center">
									<img src="img/avatar1.png" width="170px" class="img-fluid" />
								</div>
								<div class="mx-2">
									<form action="${pageContext.request.contextPath}/Partida" method="POST">
										<div class="my-3">
											<label class="form-label text-white my-3">Nombre o apodo</label>
											<input
												type="text"
												name="nombreJugador"
												class="form-control w-100"
												placeholder="Adriana_Velez"
												style="min-height: 55px" />
											<input
												type="hidden"
												name="idJugador"
												value="<%= JugadorDAO.generarCodigoJugador()%>" />
											<input
												type="hidden"
												name="codigoPartida"
												value="<%= PartidaVO.generarCodigoPartida()%>" />
											<input type="hidden" name="opcion" value="3" />
										</div>
										<div class="">
											<button
												type="submit"
												class="btn btn-light btn-lg w-100 my-1 px-5 shadow text-black p-3 w-100">
												<i class="fas fa-play-circle"></i>Crear Partida
											</button>
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
	</body>
	<!-- Archivos js -->
	<jsp:include page="WEB-INF/paginas/comunes/files-js.jsp" />
</html>
