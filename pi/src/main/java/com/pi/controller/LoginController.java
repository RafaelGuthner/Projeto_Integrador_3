package com.pi.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pi.DAO.JogadorDAO;
import com.pi.model.Jogador;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Controle do login", urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		if (opcao.equals("login")) {
			Jogador jogador = new Jogador();
			JogadorDAO jogadorDAO = new JogadorDAO();

			try {
				jogador.setEmail(request.getParameter("email"));
				jogador.setSenha(request.getParameter("senha"));

				jogador = jogadorDAO.login(jogador);
				HttpSession session = request.getSession();
				
				if(jogador.getId_jogador() != 0) {
					//conseguiu logar
					session.setAttribute("jogador", jogador);
					session.setAttribute("msgAviso", "Login realizado com sucesso!");
					session.setAttribute("msgAvisoCor", "green");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
					requestDispatcher.forward(request, response);
				}else {
					//não logou
					session.setAttribute("msgAviso", "E-mail e/ou senha inválido!");
					session.setAttribute("msgAvisoCor", "red");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

