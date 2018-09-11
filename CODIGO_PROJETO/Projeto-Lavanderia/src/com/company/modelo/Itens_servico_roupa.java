package com.company.modelo;

import java.util.Date;

public class Itens_servico_roupa {

private int in_pv_id_item_servico;
private int in_pv_quantidade;
private Double db_pv_valorUnt;
private Date  dt_pv_data;
private Roupa obj_pv_roupa;
private Servico obj_pv_servico;
private Pedido_servico obj_pv_pedido;


public Itens_servico_roupa(){

}

public int getIn_pv_id_item_servico() {
	return in_pv_id_item_servico;
}
public void setIn_pv_id_item_servico(int in_pv_id_item_servico) {
	this.in_pv_id_item_servico = in_pv_id_item_servico;
}
public int getIn_pv_quantidade() {
	return in_pv_quantidade;
}
public void setIn_pv_quantidade(int in_pv_quantidade) {
	this.in_pv_quantidade = in_pv_quantidade;
}
public Date getDt_pv_data() {
	return dt_pv_data;
}
public void setDt_pv_data(Date dt_pv_data) {
	this.dt_pv_data = dt_pv_data;
}
public Roupa getObj_pv_roupa() {
	return obj_pv_roupa;
}
public void setObj_pv_roupa(Roupa obj_pv_roupa) {
	this.obj_pv_roupa = obj_pv_roupa;
}
public Servico getObj_pv_servico() {
	return obj_pv_servico;
}
public void setObj_pv_servico(Servico obj_pv_servico) {
	this.obj_pv_servico = obj_pv_servico;
}
public Pedido_servico getObj_pv_pedido() {
	return obj_pv_pedido;
}
public void setObj_pv_pedido(Pedido_servico obj_pv_pedido) {
	this.obj_pv_pedido = obj_pv_pedido;
}
public Double getDb_pv_valorUnt() {
	return db_pv_valorUnt;
}
public void setDb_pv_valorUnt(Double db_pv_valorUnt) {
	this.db_pv_valorUnt = db_pv_valorUnt;
}






}
