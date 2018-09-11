package com.company.modelo;

public class Roupa {

private int in_pv_id_roupa;
private String st_pv_descricao;
private String st_pv_cor;
private String st_pv_tecido;


public int getIn_pv_id_roupa() {
	return in_pv_id_roupa;
}
public void setIn_pv_id_roupa(int in_pv_id_roupa) {
	this.in_pv_id_roupa = in_pv_id_roupa;
}
public String getSt_pv_descricao() {
	return st_pv_descricao;
}
public void setSt_pv_descricao(String st_pv_descricao) {
	this.st_pv_descricao = st_pv_descricao;
}
public String getSt_pv_cor() {
	return st_pv_cor;
}
public void setSt_pv_cor(String st_pv_cor) {
	this.st_pv_cor = st_pv_cor;
}
public String getSt_pv_tecido() {
	return st_pv_tecido;
}
public void setSt_pv_tecido(String st_pv_tecido) {
	this.st_pv_tecido = st_pv_tecido;
}

@Override
public String toString() {
	return getSt_pv_descricao();
}


	
}
