package com.pi.model;

public class Horarios {
	private int id_horario;
	private int id_jogador;
	private String posicao;
	private String dia_livre;
	private String hora_inicio;
	private String hora_fim;
	private String valor;
	
	public int getId_horario() {
		return id_horario;
	}
	public void setId_horario(int id_horario) {
		this.id_horario = id_horario;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getId_jogador() {
		return id_jogador;
	}
	public void setId_jogador(int id_jogador) {
		this.id_jogador = id_jogador;
	}
	public String getPosicao() {
		return posicao;
	}
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}
	public String getDia_livre() {
		return dia_livre;
	}
	public void setDia_livre(String dia_livre) {
		this.dia_livre = dia_livre;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}
}

