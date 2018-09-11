package com.company.util;

import com.company.modelo.Pedido_servico;
public class PassarObjeto {
	
	public static Pedido_servico pedido;
	public static String usuario;
	
	
	
	

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		PassarObjeto.usuario = usuario;
	}

	public static Pedido_servico getPedido() {
		return pedido;
	}

	public static void setPedido(Pedido_servico pedido) {
		PassarObjeto.pedido = pedido;
	}
	
	
	
}
