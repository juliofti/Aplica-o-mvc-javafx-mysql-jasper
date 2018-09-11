package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.company.modelo.Cliente;
import com.company.modelo.Itens_servico_roupa;
import com.company.modelo.Pedido_servico;
import com.company.modelo.Roupa;
import com.company.modelo.Servico;
import com.company.persistencia.Conexao;


public class DaoItemPedido {
	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();
	
	
	public boolean inserir(Itens_servico_roupa  item_servico_roupa) {
        String sql = "INSERT INTO itens_servico(quantidade,id_pedido, id_servico,id_roupa,valor) VALUES(?,?,?,?,?)";
        try {
    		stmt = conn.prepareStatement(sql);
            stmt.setInt(1, item_servico_roupa.getIn_pv_quantidade());
            stmt.setDouble(2, item_servico_roupa.getObj_pv_pedido().getIn_pv_n_pedido());
            stmt.setInt(3, item_servico_roupa.getObj_pv_servico().getIn_pv_id_servico());
            stmt.setInt(4, item_servico_roupa.getObj_pv_roupa().getIn_pv_id_roupa());
            stmt.setDouble(5, item_servico_roupa.getDb_pv_valorUnt());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Itens_servico_roupa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	
	public void deletar(int id) throws SQLException {

		String sql = "delete from itens_servico where id_pedido = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}
	
	public ArrayList<Itens_servico_roupa> listaPorPedido(Pedido_servico pedido) throws SQLException{
		ArrayList<Itens_servico_roupa> listaItens = new ArrayList<Itens_servico_roupa>();
		
		 
		String sql = "SELECT * FROM itens_servico where id_pedido = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pedido.getIn_pv_n_pedido());
		ResultSet resultado = stmt.executeQuery();
        
        while(resultado.next()) {
        Itens_servico_roupa itens = new Itens_servico_roupa();
		Roupa roupa = new Roupa();
		Servico servico = new Servico();
		
        itens.setIn_pv_id_item_servico(resultado.getInt("id_itens_servico"));
        itens.setIn_pv_quantidade(resultado.getInt("quantidade"));
        itens.setDb_pv_valorUnt(resultado.getDouble("valor"));
        roupa.setIn_pv_id_roupa(resultado.getInt("id_roupa"));
        servico.setIn_pv_id_servico(resultado.getInt("id_servico"));
        
        DaoRoupa daoroupa = new DaoRoupa();
        DaoServico daoservico = new DaoServico();
        
       roupa = daoroupa.buscar(roupa);
       servico=daoservico.buscar(servico);
        
        itens.setObj_pv_roupa(roupa);
        itens.setObj_pv_servico(servico);
        listaItens.add(itens);	
        }
        return listaItens;
	}
	 
	
}
