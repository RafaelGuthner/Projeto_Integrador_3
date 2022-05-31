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
	<title>Editar Conta</title>
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
	<h1>Editar Conta</h1>
	<form id="form_jogador" action="jogador" method="post">
	<c:set var="jogador" value="${jogador}"></c:set>
		<input type="hidden" name="opcao" value="atualizarjogador">
		<input type="hidden" name="id_pedido" value="${jogador.id_jogador}">
		<table border="1">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_box</i>
						<input id="nome_prefix" type="text" class="validate"size="40" name="nome" value="${jogador.nome}">
						<label for="nome_prefix">Nome<font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">email</i>
						<input id="email_prefix" type="text" class="validate"  name="email" value="${jogador.email}">
						<label for="email_prefix">Endereco de entrega <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="senha_prefix" type="text" class="validate" name="senha" value="${jogador.senha}">
						<label for="senha_prefix">Senha <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="telefone_prefix" type="number" class="validate" name="telefone" value="${jogador.telefone}">
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
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar">Editar
		    <i class="material-icons right">edit</i>
		  </button>
		</div>
		<br>
		</form>
	</div>
</body>
</html>
