package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.model.Jogador;


public class JogadorDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
	
	public Jogador login(Jogador jogador) throws SQLException {
		ResultSet resultSet = null;
		
		Jogador joga = new Jogador();

		String sql = null;

		this.connection = obterConexao();

		try {

			sql = "SELECT * FROM jogador WHERE email = ? AND senha=?";
			this.statement = connection.prepareStatement(sql);
			this.statement.setString(1, jogador.getEmail());
			this.statement.setString(2, jogador.getSenha());

			resultSet = this.statement.executeQuery();

			if (resultSet.next()) {
				joga.setId_jogador(resultSet.getInt(1));
				joga.setNome(resultSet.getString(2));
				joga.setEmail(resultSet.getString(3));
				joga.setSenha(resultSet.getString(4));
				joga.setTelefone(resultSet.getString(5)); 
			}
			this.statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Ok fechou a conexao!");
			this.connection.close();
		}

		return joga;
	}

	public boolean inserirJogador(Jogador jogador) throws SQLException {
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();

		try {
		sql = "INSERT INTO jogador (nome,email,senha,telefone) VALUES (?,?,?,?)";
		this.statement = connection.prepareStatement(sql);

		this.statement.setString(1, jogador.getNome());
		this.statement.setString(2, jogador.getEmail());
		this.statement.setString(3, jogador.getSenha());
		this.statement.setString(4, jogador.getTelefone());

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

	public void alterarJogador(Jogador jogador) throws SQLException {
		String sql = null;
		this.connection = obterConexao();
		
		try {
		this.connection.setAutoCommit(false);
		sql = "UPDATE jogador SET nome = ?, email = ?, senha = ?,telefone = ? WHERE id_jogador = ?";
		this.statement = this.connection.prepareStatement(sql);
		
		this.statement.setString(1, jogador.getNome());
		this.statement.setString(2, jogador.getEmail());
		this.statement.setString(3, jogador.getSenha());
		this.statement.setString(4, jogador.getTelefone());
		this.statement.setInt(5, jogador.getId_jogador());
		
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

	public Jogador listaJogador(int id) throws SQLException {
		ResultSet resultSet = null;
		
		Jogador joga = new Jogador();

		String sql = null;

		this.connection = obterConexao();

		try {

			sql = "SELECT * FROM jogador WHERE id_jogador = ?";
			this.statement = connection.prepareStatement(sql);
			this.statement.setInt(1, id);

			resultSet = this.statement.executeQuery();

			if (resultSet.next()) {
				joga.setId_jogador(resultSet.getInt(1));
				joga.setNome(resultSet.getString(2));
				joga.setEmail(resultSet.getString(3));
				joga.setSenha(resultSet.getString(4));
				joga.setTelefone(resultSet.getString(5)); 
			}
			this.statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Ok fechou a conexao!");
			this.connection.close();
		}

		return joga;
	}

}
