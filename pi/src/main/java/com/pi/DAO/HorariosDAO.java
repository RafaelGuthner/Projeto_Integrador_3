package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pi.model.Horarios;

public class HorariosDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
	
	
	public List<Horarios> listarHorarios(int id_jogador) throws SQLException {
		ResultSet resultset = null;
		List<Horarios> listaHorarios = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM horarios WHERE id_jogador=?";
			this.statement = this.connection.prepareStatement(sql);
			this.statement.setInt(1, id_jogador);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Horarios h = new Horarios();
				
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
				
				listaHorarios.add(h);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexão fechada");
			this.connection.close();
		}
		
		return listaHorarios;
	}


	public boolean inserirHorario(Horarios horario) throws SQLException {
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();

		try {
		sql = "insert into horarios (id_jogador,posicao,dia_livre,hora_inicio,hora_fim,valor) values(?,?,?,?,?,?)";
		this.statement = connection.prepareStatement(sql);

		this.statement.setInt(1, horario.getId_jogador());
		this.statement.setString(2, horario.getPosicao());
		this.statement.setString(3, horario.getDia_livre());
		this.statement.setString(4, horario.getHora_inicio());
		this.statement.setString(5, horario.getHora_fim());
		this.statement.setString(6, horario.getValor());
		
		this.estadoOperacao = statement.executeUpdate() > 0;
		this.connection.commit();
		this.statement.close();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("Fechou a conexão!");
			this.connection.close();
		}

		return estadoOperacao;
	}


	public boolean deletarHorario(int id_horario) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM horarios WHERE id_horario = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_horario);
			estadoOperacao = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
				
		}
		catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}
				
	return estadoOperacao;
	}


	public Horarios buscaHorario(int id_horario) throws SQLException {
		ResultSet resultset = null;
		Horarios h = new Horarios();

		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		try {
			sql = "SELECT * FROM horarios WHERE id_horario =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id_horario);
			
			resultset = statement.executeQuery();
			
			if(resultset.next()) {	
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
			}
			statement.close();
			resultset.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}

		return h;
	}


	public void alterarHorario(Horarios horario) throws SQLException {
		String sql = null;
		this.connection = obterConexao();
		
		try {
		this.connection.setAutoCommit(false);
		sql = "UPDATE horarios SET id_jogador = ?, posicao = ?, dia_livre = ?,hora_inicio = ?,hora_fim = ?,valor = ? WHERE id_horario = ?";
		this.statement = this.connection.prepareStatement(sql);
		
		this.statement.setInt(1,horario.getId_jogador());
		this.statement.setString(2,horario.getPosicao());
		this.statement.setString(3,horario.getDia_livre());
		this.statement.setString(4,horario.getHora_inicio());
		this.statement.setString(5,horario.getHora_fim());
		this.statement.setString(6,horario.getValor());
		this.statement.setInt(7, horario.getId_horario());
		
		this.statement.executeUpdate();
		this.connection.commit();
		this.statement.close();
		
		}catch(SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}finally {
			System.out.println("Alterado");
			this.connection.close();
		}
	}


	public List<Horarios> listarTodosHorarios() throws SQLException {
		ResultSet resultset = null;
		List<Horarios> listaHorarios = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM horarios";
			this.statement = this.connection.prepareStatement(sql);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Horarios h = new Horarios();
				
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
				
				listaHorarios.add(h);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexão fechada");
			this.connection.close();
		}
		
		return listaHorarios;
	}


	public List<Horarios> listarPosicaoHorarios(String posicao) throws SQLException {
		ResultSet resultset = null;
		List<Horarios> listaHorarios = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM horarios WHERE posicao = ?";
			this.statement = this.connection.prepareStatement(sql);
			this.statement.setString(1, posicao);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Horarios h = new Horarios();
				
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
				
				listaHorarios.add(h);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexão fechada");
			this.connection.close();
		}
		
		return listaHorarios;
	}


	public List<Horarios> listarDiaHorarios(String dia) throws SQLException {
		ResultSet resultset = null;
		List<Horarios> listaHorarios = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM horarios WHERE dia_livre  = ?";
			this.statement = this.connection.prepareStatement(sql);
			this.statement.setString(1, dia);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Horarios h = new Horarios();
				
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
				
				listaHorarios.add(h);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexão fechada");
			this.connection.close();
		}
		
		return listaHorarios;
	}


	public List<Horarios> listarPosicaoDiaHorarios(String posicao, String dia) throws SQLException {
		ResultSet resultset = null;
		List<Horarios> listaHorarios = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM horarios WHERE posicao  = ? AND dia_livre  = ?";
			this.statement = this.connection.prepareStatement(sql);
			this.statement.setString(1, posicao);
			this.statement.setString(2, dia);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Horarios h = new Horarios();
				
				h.setId_horario(resultset.getInt(1));
				h.setId_jogador(resultset.getInt(2));
				h.setPosicao(resultset.getString(3));
				h.setDia_livre(resultset.getString(4));
				h.setHora_inicio(resultset.getString(5));
				h.setHora_fim(resultset.getString(6));
				h.setValor(resultset.getString(7));
				
				listaHorarios.add(h);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexão fechada");
			this.connection.close();
		}
		
		return listaHorarios;
	}
}
