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
