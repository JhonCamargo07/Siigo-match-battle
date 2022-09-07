<%@include file="cache.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>Start Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
    <h1>
        <form action="${pageContext.request.contextPath}/Partida" method="POST">
            <input type="text" name="nombreJugador1" value="Jacc">
            <input type="text" name="nombreJugador2" value="Martha">
            <input type="hidden" name="cantidadJugadores" value="2">
            <input type="hidden" name="opcion" value="1">
            <input type="submit" value="Enviar">
        </form>
        
        ${jugadores}
        <p></p>
        <a href="${pageContext.request.contextPath}/Partida?cod=1234567">Ingresar</a>
        <p>${titulo}</p>
        <p>${descripcion}</p>
        
        
    </h1>
</body>

</html>