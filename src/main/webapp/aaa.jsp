<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- <link rel="stylesheet" href="" /> -->
		<title></title>
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
			crossorigin="anonymous" />
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
			crossorigin="anonymous"></script>
	</head>
	<body>
		<div class="container">
			<div class="border d-flex justify-content-center align-items-center" style="height: 100vh">
				<div class="bg-danger" style="min-width: 100px; min-height: 100px">
					<h2 class="tituloprincipal text-center pt-5 px-5 pb-2 mb-4">Siigo Match Battle</h2>
					<div class="text-center">
						<img src="img/cartas.gif" class="volver" />
					</div>
					<div class="text-center d-flex justify-content-around align-items-center pt-3">
						<div class="boton1">
							<form action="${pageContext.request.contextPath}/Partida" method="POST">
								<input type="hidden" name="opcion" value="5" />
								<button
									type="submit"
									class="btn btn-light btn-login text-black titulo2 p-3 w-80 fw-bold btn-form text-size box">
									<i class="fas fa-play-circle"></i>Crear Partida
								</button>
							</form>
						</div>
						<div class="boton2">
							<a href="ingresarPartida.jsp">
								<button
									type="submit"
									class="btn btn-light btn-login text-black titulo2 p-3 w-0 fw-bold btn-form box">
									<i class="fas fa-gamepad"></i>Ingresar a Partida
								</button>
							</a>
						</div>
						<!--<a href="${pageContext.request.contextPath}/Partida?cod=1234567">Ingresar</a>-->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
