<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script> 
	<script src="js/validarFormCadastro.js" type="text/javascript"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		
	<c:if test="${empty sessionScope['jogador']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="/index.jsp" />
	</c:if>	
		
	<meta charset="UTF-8">
	<title>Criar um Horario</title>
	<script type="text/javascript">
		  $(document).ready(function(){
		    $('select').formSelect();
		  });
	</script>

</head>
<body>
<div class="container">
	<h1>Criar um Horario</h1>
	<form id="form_jogador" action="jogador" method="post">
		<input type="hidden" name="opcao" value="guardarhorario">
		<table border="1" class="highlight">
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
						<label for="posicao_prefix">Posição<font color="red">(Obrigatório)</font></label>
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
						<label for="dia_prefix">Dia</label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="horainicio_prefix" type="text" class="validate" name="horainicio">
						<label for="horainicio_prefix">Inicio horario</label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="horafim_prefix" type="text" class="validate" name="horafim">
						<label for="horafim_prefix">Fim</label>
			        </div>
				</td>
			</tr>	
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="valor_prefix" type="text" class="validate" name="valor">
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
		  <button class="btn-large waves-effect waves-light" type="submit" value="GuardarHorario">Criar
		    <i class="material-icons right">add_circle_outline</i>
		  </button>
		  <c:if test="${!empty sessionScope['jogador']}">
				<a class="waves-effect waves-light btn-large" href= "jogador?opcao=voltar&view=principal.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
		  </c:if>
		</div>
		<br>
	</form>
</div>
</body>
</html>