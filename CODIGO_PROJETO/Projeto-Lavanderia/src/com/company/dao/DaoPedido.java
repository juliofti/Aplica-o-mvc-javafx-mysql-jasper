package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.modelo.Cliente;
import com.company.modelo.Pedido_servico;
import com.company.modelo.Roupa;
import com.company.modelo.Servico;
import com.company.persistencia.Conexao;




public class DaoPedido {

	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();
	
	 public boolean inserir(Pedido_servico pedido_servico) {
	        String sql = "INSERT INTO pedido(data_pedido, valorTotal, id_cliente,pago) VALUES(?,?,?,?)";
	        try {
	        	stmt = conn.prepareStatement(sql);
	            stmt.setDate(1, pedido_servico.getDt_pv_data());
	            stmt.setDouble(2, pedido_servico.getDb_pv_valor_total());
	            stmt.setInt(3, pedido_servico.getObj_cliente().getIn_pv_idcliente());
	            stmt.setInt(4, pedido_servico.getIn_pv_pago());
	            stmt.execute();
	            return true;
	        } catch (SQLException ex) {
	           com.company.util.Metodos.msgErro(null, "Falha no sql-pedido");
	            return false;
	        }
	    }
	 

	 
	 
	    public Pedido_servico buscar(Pedido_servico pedido) {
	        String sql = "SELECT * FROM pedido WHERE id_pedido=?";
	        Pedido_servico  retorno = new Pedido_servico();
	        try {
	           stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, retorno.getIn_pv_n_pedido());
	            ResultSet resultado = stmt.executeQuery();
	            if (resultado.next()) {
	                Cliente cliente = new Cliente();
	              pedido.setIn_pv_n_pedido(resultado.getInt("id_pedido"));
	              pedido.setDt_pv_data(resultado.getDate("data_pedido"));
	              pedido.setDb_pv_valor_total(resultado.getDouble("valorTotal"));
	             cliente.setIn_pv_idcliente(resultado.getInt("id_cliente"));
	             pedido.setObj_cliente(cliente);
	                retorno = pedido;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return retorno;
	    }
	    
	    
	    
	    public Pedido_servico buscarUltimoPedido() {
	        String sql = "SELECT max(id_pedido) FROM pedido";
	        Pedido_servico retorno = new Pedido_servico();
	        try {
	             stmt = conn.prepareStatement(sql);
	            ResultSet resultado = stmt.executeQuery();

	            if (resultado.next()) {
	                retorno.setIn_pv_n_pedido(resultado.getInt("max(id_pedido)"));
	                return retorno;
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(DaoPedido.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return retorno;
	    }

	    
	    
	    public void deletar(int id) throws SQLException {

			String sql = "delete from pedido where id_pedido = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		}
	    public ArrayList<Pedido_servico> listaFiltro(String filtro,String nome) throws Exception {
	    
	    	  String sql = "select pedido.id_pedido, pedido.data_pedido, pedido.valorTotal, pedido.pago,cliente.id_cliente,servico.id_servico, tipo_roupa.id_roupa from pedido " 
	    	  		+"inner join cliente  on pedido.id_cliente = cliente.id_cliente \n"
	    	  		+"inner join itens_servico on pedido.id_pedido = itens_servico.id_pedido \n" 
	    	  		+"inner join tipo_roupa on tipo_roupa.id_roupa = itens_servico.id_roupa \n" 
	    	  		+"inner join servico on servico.id_servico = itens_servico.id_servico \n"
	    	  		+"where "+filtro+" like '%"+nome+"%'";
	    	  
		       ArrayList<Pedido_servico> retorno = new ArrayList<>();
		        
		            stmt = conn.prepareStatement(sql);
		            ResultSet resultado = stmt.executeQuery();
		            while(resultado.next()) {
		                Pedido_servico pedido = new Pedido_servico();
		                Cliente cliente = new Cliente();
		                Servico servico = new Servico();
		                Roupa roupa = new Roupa();
		                
		                
		                pedido.setIn_pv_n_pedido(resultado.getInt("id_pedido"));
		                pedido.setDt_pv_data(resultado.getDate("data_pedido"));
		                pedido.setDb_pv_valor_total(resultado.getDouble("valorTotal"));
		                pedido.setIn_pv_pago(resultado.getInt("pago"));
		                cliente.setIn_pv_idcliente(resultado.getInt("id_cliente"));
		                servico.setIn_pv_id_servico(resultado.getInt("id_servico"));
		                roupa.setIn_pv_id_roupa(resultado.getInt("id_roupa"));
		                
		                DaoCliente clientedao = new DaoCliente();
		                cliente = clientedao.buscar(cliente);
		                pedido.setObj_cliente(cliente);
		                
		                DaoServico daoservico = new DaoServico();
		                servico = daoservico.buscar(servico);
		                pedido.setObj_servico(servico);
		                
		                DaoRoupa daoroupa = new DaoRoupa();
		                roupa = daoroupa.buscar(roupa);
		                pedido.setObj_roupa(roupa);
		              
		                retorno.add(pedido);
		            }
		            	return retorno;
		            
		            }
	    
	    public ArrayList<Pedido_servico> listaCompleta() throws Exception {
		    
	    	  String sql = "select pedido.id_pedido, pedido.data_pedido, pedido.valorTotal,pedido.pago,cliente.id_cliente,servico.id_servico, tipo_roupa.id_roupa from pedido " 
	    	  		+"inner join cliente  on pedido.id_cliente = cliente.id_cliente \n"
	    	  		+"inner join itens_servico on pedido.id_pedido = itens_servico.id_pedido \n" 
	    	  		+"inner join tipo_roupa on tipo_roupa.id_roupa = itens_servico.id_roupa \n" 
	    	  		+"inner join servico on servico.id_servico = itens_servico.id_servico";
	    	  
		       ArrayList<Pedido_servico> retorno = new ArrayList<>();
		        
		            stmt = conn.prepareStatement(sql);
		            ResultSet resultado = stmt.executeQuery();
		            while(resultado.next()) {
		                Pedido_servico pedido = new Pedido_servico();
		                Cliente cliente = new Cliente();
		                Servico servico = new Servico();
		                Roupa roupa = new Roupa();
		                
		                pedido.setIn_pv_n_pedido(resultado.getInt("id_pedido"));
		                pedido.setDt_pv_data(resultado.getDate("data_pedido"));
		                pedido.setDb_pv_valor_total(resultado.getDouble("valorTotal"));
		                pedido.setIn_pv_pago(resultado.getInt("pago"));
		                cliente.setIn_pv_idcliente(resultado.getInt("id_cliente"));
		                servico.setIn_pv_id_servico(resultado.getInt("id_servico"));
		                roupa.setIn_pv_id_roupa(resultado.getInt("id_roupa"));
		                
		                DaoCliente clientedao = new DaoCliente();
		                cliente = clientedao.buscar(cliente);
		                pedido.setObj_cliente(cliente);
		                
		                DaoServico daoservico = new DaoServico();
		                servico = daoservico.buscar(servico);
		                pedido.setObj_servico(servico);
		                
		                DaoRoupa daoroupa = new DaoRoupa();
		                roupa = daoroupa.buscar(roupa);
		                pedido.setObj_roupa(roupa);
		              
		                retorno.add(pedido);
		            }
		            	return retorno;
		            
		            }
}
