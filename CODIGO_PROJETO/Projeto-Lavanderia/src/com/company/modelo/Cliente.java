package com.company.modelo;

import java.sql.Date;

public class Cliente {

private int in_pv_idcliente;
private String st_pv_nome;
private String st_pv_cpf;
private Date dt_pv_nascimento;
private String st_pv_email;
private String st_pv_telefone;
private String st_pv_endereco;
private String st_pv_cidade;
private String st_pv_cep;
private String st_pv_estado;

public Cliente() {
	
}

public int getIn_pv_idcliente() {
	return in_pv_idcliente;
}
public void setIn_pv_idcliente(int in_pv_idcliente) {
	this.in_pv_idcliente = in_pv_idcliente;
}
public String getSt_pv_nome() {
	return st_pv_nome;
}
public void setSt_pv_nome(String st_pv_nome) {
	this.st_pv_nome = st_pv_nome;
}
public String getSt_pv_cpf() {
	return st_pv_cpf;
}
public void setSt_pv_cpf(String st_pv_cpf) {
	this.st_pv_cpf = st_pv_cpf;
}
public Date getDt_pv_nascimento() {
	return dt_pv_nascimento;
}
public void setDt_pv_nascimento(Date dt_pv_nascimento) {
	this.dt_pv_nascimento = dt_pv_nascimento;
}
public String getSt_pv_email() {
	return st_pv_email;
}
public void setSt_pv_email(String st_pv_email) {
	this.st_pv_email = st_pv_email;
}
public String getSt_pv_telefone() {
	return st_pv_telefone;
}
public void setSt_pv_telefone(String st_pv_telefone) {
	this.st_pv_telefone = st_pv_telefone;
}
public String getSt_pv_endereco() {
	return st_pv_endereco;
}
public void setSt_pv_endereco(String st_pv_endereco) {
	this.st_pv_endereco = st_pv_endereco;
}
public String getSt_pv_cidade() {
	return st_pv_cidade;
}
public void setSt_pv_cidade(String st_pv_cidade) {
	this.st_pv_cidade = st_pv_cidade;
}
public String getSt_pv_cep() {
	return st_pv_cep;
}
public void setSt_pv_cep(String st_pv_cep) {
	this.st_pv_cep = st_pv_cep;
}
public String getSt_pv_estado() {
	return st_pv_estado;
}
public void setSt_pv_estado(String st_pv_estado) {
	this.st_pv_estado = st_pv_estado;
}
@Override
public String toString() {
	return getSt_pv_nome();
}





}
