<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script> 
	<script src="js/validarFormEditarUsuario.js" type="text/javascript"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


	
	<c:if test="${empty sessionScope['jogador']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="index.jsp" />
	</c:if>
	
	<meta charset="UTF-8">
	<title>Editar horario</title>
	<script type="text/javascript">
		  $(document).ready(function(){
		    $('select').formSelect();
		  });
		  $(document).ready(function() {
		    $('select').material_select();
		  });
  </script>
	
</head>
<body>
<div class="container">
	<h1>Editar horario</h1>
	<form id="form_horario" action="jogador" method="post">
	<c:set var="horario" value="${horario}"></c:set>
		<input type="hidden" name="opcao" value="atualizarhorario">
		<input type="hidden" name="id_horario" value="${horario.id_horario}">
		<input type="hidden" name="id_jogador" value="${horario.id_jogador}">
		<table border="1">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<select name="posicao" id="posicao_prefix">
 								<option value="Goleiro"selected>Goleiro</option>
								<option value="Defesa">Defesa</option>
								<option value="Meio-campo">Meio-campo</option>
								<option value="Ataque">Ataque</option>
							</select>
						<label for="posicao_prefix">Posição:<font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
							<select name="dia" id="dia_prefix">
								<option value="Qualquer"selected>-Qualquer-</option>
 								<option value="Segunda-feira">Segunda-feira</option>
								<option value="Terca-feira">Terça-feira</option>
								<option value="Quarta-feira">Quarta-feira</option>
								<option value="Quinta-feira">Quinta-feira</option>
								<option value="Sexta-feira">Sexta-feira</option>
								<option value="Sabado-feira">Sabado</option>
								<option value="Domingo-feira">Domingo</option>
							</select>
						<label for="dia_prefix">Dia:</label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="horainicio_prefix" type="text" class="validate" name="horainicio" value="${horario.hora_inicio}">
						<label for="horainicio_prefix">Inicio horario:</label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="horafim_prefix" type="text" class="validate" name="horafim" value="${horario.hora_fim}">
						<label for="horafim_prefix">Fim:</label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="valor_prefix" type="text" class="validate" name="valor" value="${horario.valor}">
						<label for="valor_prefix">Valor (por um jogo de 1 hora):</label>
			        </div>
				</td>
			</tr>	
		</table>
		<table>
			<tr>
				<td class="center-align">
					<font size="5">
						Tudo que estiver em
						<font color="red">
							 vermelho 
						</font>
					é obrigatório.
					</font>
				</td>
			</tr>
		</table>
		<div class="center-align">
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar">Editar
		    <i class="material-icons right">edit</i>
		  </button>
		</div>
		<br>
		</form>
	</div>
</body>
</html>
