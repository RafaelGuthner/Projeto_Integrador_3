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
		
	<meta charset="UTF-8">
	<title>Criar um jogador</title>
	<script type="text/javascript">
		  $(document).ready(function(){
		    $('select').formSelect();
		  });
	</script>

</head>
<body>
<div class="container">
	<h1>Criar um jogador</h1>
	<form id="form_jogador" action="jogador" method="post">
		<input type="hidden" name="opcao" value="guardarjogador">
		<table border="1" class="highlight">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">email</i>
						<input id="nome_prefix" type="text" class="validate"  name="nome">
						<label for="nome_prefix">Nome<font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">email</i>
						<input id="email_prefix" type="email" class="validate" name="email">
						<label for="email_prefix">Email <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="senha_prefix" type="text" class="validate" name="senha">
						<label for="senha_prefix">Senha <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="telefone_prefix" type="text" class="validate" name="telefone">
						<label for="telefone_prefix">Telefone <font color="red">(Obrigatório)</font></label>
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
		  <button class="btn-large waves-effect waves-light" type="submit" value="GuardarJogador">Guardar
		    <i class="material-icons right">add_circle_outline</i>
		  </button>
		  <c:if test="${!empty sessionScope['jogador']}">
				<a class="waves-effect waves-light btn-large" href= "jogador?opcao=voltar&view=principal.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
		  </c:if>
		  <c:if test="${empty sessionScope['jogador']}">
				<a class="waves-effect waves-light btn-large" href= "jogador?opcao=voltar"><i class="material-icons right">arrow_back</i>Voltar</a>
		  </c:if>
		</div>
		<br>
	</form>
</div>
</body>
</html>