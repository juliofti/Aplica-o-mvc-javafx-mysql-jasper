package com.company.modelo;

import java.sql.Date;
import java.util.ArrayList;


public class Pedido_servico {

private int in_pv_n_pedido;
private Date dt_pv_data;
private double db_pv_valor_total;
private int in_pv_pago;
private Cliente obj_cliente;
private Roupa obj_roupa;
private Servico obj_servico;
private ArrayList<Itens_servico_roupa> lt_pv_listaItens;

public Pedido_servico() {
	
}

public int getIn_pv_n_pedido() {
	return in_pv_n_pedido;
}
public void setIn_pv_n_pedido(int in_pv_n_pedido) {
	this.in_pv_n_pedido = in_pv_n_pedido;
}
public Date getDt_pv_data() {
	return dt_pv_data;
}
public void setDt_pv_data(Date dt_pv_data) {
	this.dt_pv_data = dt_pv_data;
}
public double getDb_pv_valor_total() {
	return db_pv_valor_total;
}
public void setDb_pv_valor_total(double db_pv_valor_total) {
	this.db_pv_valor_total = db_pv_valor_total;
}
public Cliente getObj_cliente() {
	return obj_cliente;
}
public void setObj_cliente(Cliente obj_cliente) {
	this.obj_cliente = obj_cliente;
}
public Roupa getObj_roupa() {
	return obj_roupa;
}
public void setObj_roupa(Roupa obj_roupa) {
	this.obj_roupa = obj_roupa;
}
public Servico getObj_servico() {
	return obj_servico;
}
public void setObj_servico(Servico obj_servico) {
	this.obj_servico = obj_servico;
}
public ArrayList<Itens_servico_roupa> getLt_pv_listaItens() {
	return lt_pv_listaItens;
}
public void setLt_pv_listaItens(ArrayList<Itens_servico_roupa> lt_pv_listaItens) {
	this.lt_pv_listaItens = lt_pv_listaItens;
}
public int getIn_pv_pago() {
	return in_pv_pago;
}
public void setIn_pv_pago(int in_pv_pago) {
	this.in_pv_pago = in_pv_pago;



}



	
}
