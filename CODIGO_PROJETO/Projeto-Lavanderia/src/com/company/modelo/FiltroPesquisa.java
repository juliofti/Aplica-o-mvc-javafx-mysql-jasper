package com.company.modelo;

public class FiltroPesquisa {

	
	private String st_filtro;
	private String nome;

	public FiltroPesquisa() {
		
	}
	public FiltroPesquisa(String st_filtro, String nome) {
		this.st_filtro = st_filtro;
		this.nome = nome;
	}
	public FiltroPesquisa(String string) {
		this.st_filtro = string;
	}
	public String getSt_filtro() {
		return st_filtro;
	}
	public void setSt_filtro(String st_filtro) {
		this.st_filtro = st_filtro;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return getSt_filtro();
	}
	

	
	
	
	
	
}
