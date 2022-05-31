<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


	<c:if test="${empty sessionScope['jogador']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="/index.jsp" />
	</c:if>
	
	<meta charset="UTF-8">
	<title>Principal</title>
</head>
<body>
<div class="container">
	
	<h1>Pagina principal </h1>
	<font size="4">
		Bem vindo <c:out value="${sessionScope.jogador.nome}"/>
	</font>
	<table border="1">
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "jogador?opcao=listarhorarios">Ver meus horarios</a></td>
		</tr>
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "jogador?opcao=listarjogadores">Listar jogadores</a></td>
		</tr>
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "jogador?opcao=editarjogador">Editar dados da conta</a></td>
		</tr>
		<tr>
			<td> <a class="waves-effect waves-light btn" href= "logout">Sair</a></td>
	</table>
	<c:if test="${!empty sessionScope.msgAviso }">
		<tr>
			<td class="center-align">
				<font size="5" color="<c:out value="${sessionScope.msgAvisoCor}"/>">
					<c:out value="${sessionScope.msgAviso}"/>
				</font>
			</td>
		</tr>
	</c:if>
</div>
<c:set var="msgAviso" value="" scope="session" />	


</body>
</html>