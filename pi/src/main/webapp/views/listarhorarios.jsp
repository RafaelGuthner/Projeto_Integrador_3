
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	<c:if test="${empty sessionScope['jogador']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="index.jsp" />
	</c:if>
	
	<meta charset="UTF-8">
	<title>Lista de Horarios</title>
</head>
<body>
<div class="container">
	
	<h1>Lista de Horarios</h1>
	
	<table border="1" class="striped">
		<tr>
			<td>ID, ação:</td>
			<td>Posição:</td>
			<td>Dia: </td>
			<td>Valor: </td>
			<td>Horario: </td>
			<td>Fim: </td>
			<td>Ação: </td>
		</tr>
		
		<c:forEach var="arrayHorarios" items="${arrayHorarios}" varStatus="loopHorario">
			<tr>
				<td> <a class="waves-effect waves-light btn" href="jogador?opcao=editarhorario&id_horario=<c:out value="${arrayHorarios.id_horario}"></c:out>"> <c:out value="${arrayHorarios.id_horario}"></c:out>, Editar </a> </td>
				<td><c:out value="${arrayHorarios.posicao}"></c:out></td>
				<td><c:out value="${arrayHorarios.dia_livre}"></c:out></td>
				<td><c:out value="${arrayHorarios.valor}"></c:out></td>
				<td><c:out value="${arrayHorarios.hora_inicio}"></c:out></td>
				<td><c:out value="${arrayHorarios.hora_fim}"></c:out></td>
				<td> <a class="waves-effect waves-light btn" href="jogador?opcao=deletarhorario&id_horario=<c:out value="${arrayHorarios.id_horario}"></c:out>"><i class="material-icons right">backspace</i> Deletar </a> </td>
			</tr>
		</c:forEach>
		<tr>
			<td class="left-align">
				<a class="waves-effect waves-light btn" href= "jogador?opcao=criarhorario"><i class="material-icons right">add_circle_outline</i>Adicionar</a>
			</td>
			<td></td><td></td><td></td><td></td><td></td>
			<td class="left-align">
				<a class="waves-effect waves-light btn" href= "jogador?opcao=voltar&view=principal.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
			</td>
		</tr>
		
	</table>
</div>
</body>
</html>