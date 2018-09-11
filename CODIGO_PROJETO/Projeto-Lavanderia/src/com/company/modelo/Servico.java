package com.company.modelo;

public class Servico {

private int in_pv_id_servico;
private String st_pv_descricao;
private double db_pv_valor_servico;


public int getIn_pv_id_servico() {
	return in_pv_id_servico;
}
public void setIn_pv_id_servico(int in_pv_id_servico) {
	this.in_pv_id_servico = in_pv_id_servico;
}
public String getSt_pv_descricao() {
	return st_pv_descricao;
}
public void setSt_pv_descricao(String st_pv_descricao) {
	this.st_pv_descricao = st_pv_descricao;
}
public double getDb_pv_valor_servico() {
	return db_pv_valor_servico;
}
public void setDb_pv_valor_servico(double db_pv_valor_servico) {
	this.db_pv_valor_servico = db_pv_valor_servico;
}
@Override
public String toString() {
	return getSt_pv_descricao();
}




}
