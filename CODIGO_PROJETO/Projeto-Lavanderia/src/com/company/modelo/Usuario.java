package com.company.modelo;

public class Usuario {

	private int in_pv_id_usuario;
	private String st_pv_nome_usuario;
	private String st_pv_senha_usuario;
	private String st_pv_perfil_usuario;

	
	public Usuario() {
		
	}
	public Usuario(int i, String string) {
	this.in_pv_id_usuario = i;
	this.st_pv_perfil_usuario = string;
	}

	public int getIn_pv_id_usuario() {
		return in_pv_id_usuario;
	}

	public void setIn_pv_id_usuario(int in_pv_id_usuario) {
		this.in_pv_id_usuario = in_pv_id_usuario;
	}

	public String getSt_pv_nome_usuario() {
		return st_pv_nome_usuario;
	}

	public void setSt_pv_nome_usuario(String st_pv_nome_usuario) {
		this.st_pv_nome_usuario = st_pv_nome_usuario;
	}

	public String getSt_pv_senha_usuario() {
		return st_pv_senha_usuario;
	}

	public void setSt_pv_senha_usuario(String st_pv_senha_usuario) {
		this.st_pv_senha_usuario = st_pv_senha_usuario;
	}

	public String getSt_pv_perfil_usuario() {
		return st_pv_perfil_usuario;
	}

	public void setSt_pv_perfil_usuario(String st_pv_perfil_usuario) {
		this.st_pv_perfil_usuario = st_pv_perfil_usuario;
	}
	@Override
	public String toString() {
		return  getSt_pv_perfil_usuario();
	}
	
	

}
