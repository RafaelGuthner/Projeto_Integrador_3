package com.pi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pi.DAO.HorariosDAO;
import com.pi.DAO.JogadorDAO;
import com.pi.model.Horarios;
import com.pi.model.Jogador;

/**
 * Servlet implementation class JogadorController
 */
@WebServlet(description = "Controle das informacoes da tabela jogador", urlPatterns = "/jogador")
public class JogadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JogadorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");

		if (opcao.equals("criar")) {
			System.out.println("Pressionou a opção de criar Jogador");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/criarjogador.jsp");
			requestDispatcher.forward(request, response);

		}else if (opcao.equals("voltar")) {
			String url = "";
			HttpSession session = request.getSession();
			if (session.getAttribute("jogador") == null || session.getAttribute("jogador") == "") {
				url = "index.jsp";
			} else {
				String view = request.getParameter("view");

				url = "views/" + view;
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}else if (opcao.equals("listarhorarios")) {
			HorariosDAO horariosDAO = new HorariosDAO();
			List<Horarios> arrayHorarios = new ArrayList<>();
			HttpSession session = request.getSession();
			
			Jogador jogador = (Jogador) session.getAttribute("jogador");
			try {
				arrayHorarios = horariosDAO.listarHorarios(jogador.getId_jogador());
				request.setAttribute("arrayHorarios", arrayHorarios);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarhorarios.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Pressionou a opção de listar");
			
		}else if(opcao.equals("criarhorario")) {
			
			System.out.println("Pressionou a opção de criar Horario");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/criarhorario.jsp");
			requestDispatcher.forward(request, response);
			
			
		}else if (opcao.equals("deletarhorario")) {
			HorariosDAO horarioDAO = new HorariosDAO();
			HttpSession session = request.getSession();
			
			int id_horario = Integer.parseInt(request.getParameter("id_horario"));
			try {
				
				if(horarioDAO.deletarHorario(id_horario)) {
					System.out.println("Exclusão do horario " + request.getParameter("id_horario")+ " realizado com sucesso!");
					session.setAttribute("msgAviso", "Exclusão realizada com sucesso!");
					session.setAttribute("msgAvisoCor", "green");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
					requestDispatcher.forward(request, response);
				}else {
					new SQLException("Nao foi feita a exclusao"); 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opcao.equals("editarjogador")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editarjogador.jsp");
			requestDispatcher.forward(request, response);
		}else if (opcao.equals("editarhorario")) {
			int id_horario = Integer.parseInt(request.getParameter("id_horario"));
			System.out.println("Editar id: " + id_horario);
			Horarios horario = new Horarios();
			HorariosDAO horarioDAO = new HorariosDAO();


			try {
				horario = horarioDAO.buscaHorario(id_horario);
				request.setAttribute("horario", horario);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editarhorario.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (opcao.equals("listarjogadores")) {
			HorariosDAO horariosDAO = new HorariosDAO();
			List<Horarios> arrayHorarios = new ArrayList<>();
			
			try {
				arrayHorarios = horariosDAO.listarTodosHorarios();
				request.setAttribute("arrayHorarios", arrayHorarios);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarjogadores.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Pressionou a opção de listar");
			
		}else if (opcao.equals("verjogador")) {
			HorariosDAO horariosDAO = new HorariosDAO();
			List<Horarios> arrayHorarios = new ArrayList<>();
			Jogador jogador = new Jogador();
			JogadorDAO jogadorDAO = new JogadorDAO();
			
			int id = Integer.parseInt(request.getParameter("id_jogador"));
			try {
				arrayHorarios = horariosDAO.listarHorarios(id);
				request.setAttribute("arrayHorarios", arrayHorarios);
				jogador = jogadorDAO.listaJogador(id);
				request.setAttribute("jogadorbuscado", jogador);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/paginajogador.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Pressionou a opção de listar Jogadores");
		}else if (opcao.equals("buscahorario")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/buscar.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		if (opcao.equals("guardarjogador")) {
			Jogador jogador = new Jogador();
			JogadorDAO jogadorDAO = new JogadorDAO();
			
			jogador.setNome(request.getParameter("nome"));
			jogador.setEmail(request.getParameter("email"));
			jogador.setSenha(request.getParameter("senha"));
			jogador.setTelefone(request.getParameter("telefone"));

			try {
				if(jogadorDAO.inserirJogador(jogador)) {
					new SQLException("Nao criou o jogador");
				}

				System.out.println("Cadastro realizado com sucesso!");
				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Cadastro realizado com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else if(opcao.equals("guardarhorario")) {
			Horarios horario = new Horarios();
			HorariosDAO horarioDAO = new HorariosDAO();
			HttpSession session = request.getSession();
			Jogador joga = (Jogador) session.getAttribute("jogador");
			
			horario.setId_jogador(joga.getId_jogador());
			horario.setPosicao(request.getParameter("posicao"));
			horario.setDia_livre(request.getParameter("dia"));
			horario.setHora_inicio(request.getParameter("horainicio"));
			horario.setHora_fim(request.getParameter("horafim"));
			horario.setValor(request.getParameter("valor"));
			
			try {
				if(horarioDAO.inserirHorario(horario)) {
					new SQLException("Nao criou o horario");
				}
				
				System.out.println("Horario adicionado com sucesso!");
				session.setAttribute("msgAviso", "Cadastro realizado com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/principal.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (opcao.equals("atualizarjogador")) {
			Jogador jogador = new Jogador();
			JogadorDAO jogadorDAO = new JogadorDAO();

			jogador.setId_jogador(Integer.parseInt(request.getParameter("id_pedido")));
			jogador.setNome(request.getParameter("nome"));
			jogador.setEmail(request.getParameter("email"));
			jogador.setSenha(request.getParameter("senha"));
			jogador.setTelefone(request.getParameter("telefone"));

			
			try {
				jogadorDAO.alterarJogador(jogador);
				System.out.println("Edição do usuario id " + request.getParameter("id_usuario") + " realizado com sucesso!");
				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Edição realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(opcao.equals("atualizarhorario")) {
			Horarios horario = new Horarios();
			HorariosDAO horarioDAO = new HorariosDAO();
			
			horario.setId_horario(Integer.parseInt(request.getParameter("id_horario")));
			horario.setId_jogador(Integer.parseInt(request.getParameter("id_jogador")));
			horario.setPosicao(request.getParameter("posicao"));
			horario.setHora_fim(request.getParameter("horafim"));
			horario.setHora_inicio(request.getParameter("horainicio"));
			horario.setValor(request.getParameter("valor"));
			horario.setDia_livre(request.getParameter("dia"));
			
			try {
				horarioDAO.alterarHorario(horario);
				
				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Edição realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(opcao.equals("buscar")) {
			Horarios horario = new Horarios();
			HorariosDAO horarioDAO = new HorariosDAO();
			String posicao = request.getParameter("posicao");
			String dia = request.getParameter("dia");
			HorariosDAO horariosDAO = new HorariosDAO();
			List<Horarios> arrayHorarios = new ArrayList<>();
			
			try {
				if((posicao.equals("Qualquer"))&&(dia.equals("Qualquer"))) {
					arrayHorarios = horariosDAO.listarTodosHorarios();
				}
				if((!posicao.equals("Qualquer"))&&(dia.equals("Qualquer"))) {
					arrayHorarios = horariosDAO.listarPosicaoHorarios(posicao);
				}
				if((posicao.equals("Qualquer"))&&(!dia.equals("Qualquer"))) {
					arrayHorarios = horariosDAO.listarDiaHorarios(dia);
				}
				if((!posicao.equals("Qualquer"))&&(!dia.equals("Qualquer"))) {
					arrayHorarios = horariosDAO.listarPosicaoDiaHorarios(posicao,dia);
				}
				request.setAttribute("arrayHorarios", arrayHorarios);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarjogadores.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
