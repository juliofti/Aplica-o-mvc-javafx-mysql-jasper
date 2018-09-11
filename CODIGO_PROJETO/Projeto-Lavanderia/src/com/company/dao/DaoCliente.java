package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.modelo.Cliente;
import com.company.persistencia.Conexao;



public class DaoCliente {

	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();

	public void cadastrar(Cliente cliente) {

		try {
			String sql = "insert into cliente(nome,cpf,datanasc,email,telefone,endereco,cidade,cep,estado)values(?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cliente.getSt_pv_nome());
			stmt.setString(2, cliente.getSt_pv_cpf());
			stmt.setDate(3, cliente.getDt_pv_nascimento());
			stmt.setString(4, cliente.getSt_pv_email());
			stmt.setString(5, cliente.getSt_pv_telefone());
			stmt.setString(6, cliente.getSt_pv_endereco());
			stmt.setString(7, cliente.getSt_pv_cidade());
			stmt.setString(8, cliente.getSt_pv_cep());
			stmt.setString(9, cliente.getSt_pv_estado());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro(null, "Falha no cadastro");
		}

	}

	public void excluir(int id) throws SQLException {
		String sql = "delete from cliente where id_cliente= ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public void alterar(Cliente cliente) throws SQLException {
		String sql = "update cliente set nome = ?,cpf = ?,datanasc = ?,email = ?,telefone = ?,endereco = ?,cidade = ?,cep = ?, estado = ? where id_cliente = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, cliente.getSt_pv_nome());
		stmt.setString(2, cliente.getSt_pv_cpf());
		stmt.setDate(3, cliente.getDt_pv_nascimento());
		stmt.setString(4, cliente.getSt_pv_email());
		stmt.setString(5, cliente.getSt_pv_telefone());
		stmt.setString(6, cliente.getSt_pv_endereco());
		stmt.setString(7, cliente.getSt_pv_cidade());
		stmt.setString(8, cliente.getSt_pv_cep());
		stmt.setString(9, cliente.getSt_pv_estado());
		stmt.setInt(10, cliente.getIn_pv_idcliente());
		stmt.execute();
		stmt.close();
	}

	public ArrayList<Cliente> consultar(String nome) throws SQLException {

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "select * from cliente where nome like'%" + nome + "%'";
		stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setIn_pv_idcliente(rs.getInt("id_cliente"));
			cliente.setSt_pv_nome(rs.getString("nome"));
			cliente.setSt_pv_cpf(rs.getString("cpf"));
			cliente.setDt_pv_nascimento(rs.getDate("datanasc"));
			cliente.setSt_pv_email(rs.getString("email"));
			cliente.setSt_pv_telefone(rs.getString("telefone"));
			cliente.setSt_pv_endereco(rs.getString("endereco"));
			cliente.setSt_pv_cidade(rs.getString("cidade"));
			cliente.setSt_pv_estado(rs.getString("estado"));
			cliente.setSt_pv_cep(rs.getString("cep"));

			clientes.add(cliente);
		}
		return clientes;
	}
	
	
	
	  public ArrayList<Cliente> listar() {
	        String sql = "SELECT * FROM cliente";
	        ArrayList<Cliente> retorno = new ArrayList<>();
	        try {
	             stmt = conn.prepareStatement(sql);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                Cliente cliente = new Cliente();
	            	cliente.setIn_pv_idcliente(rs.getInt("id_cliente"));
	    			cliente.setSt_pv_nome(rs.getString("nome"));
	    			cliente.setSt_pv_cpf(rs.getString("cpf"));
	    			cliente.setDt_pv_nascimento(rs.getDate("datanasc"));
	    			cliente.setSt_pv_email(rs.getString("email"));
	    			cliente.setSt_pv_telefone(rs.getString("telefone"));
	    			cliente.setSt_pv_endereco(rs.getString("endereco"));
	    			cliente.setSt_pv_cidade(rs.getString("cidade"));
	    			cliente.setSt_pv_estado(rs.getString("estado"));
	    			cliente.setSt_pv_cep(rs.getString("cep"));
	                retorno.add(cliente);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return retorno;
	    }
	public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente=?";
        Cliente retorno = new Cliente();
        try {
          stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cliente.getIn_pv_idcliente());
            rs = stmt.executeQuery();
            if (rs.next()) {
            	cliente.setIn_pv_idcliente(rs.getInt("id_cliente"));
    			cliente.setSt_pv_nome(rs.getString("nome"));
    			cliente.setSt_pv_cpf(rs.getString("cpf"));
    			cliente.setDt_pv_nascimento(rs.getDate("datanasc"));
    			cliente.setSt_pv_email(rs.getString("email"));
    			cliente.setSt_pv_telefone(rs.getString("telefone"));
    			cliente.setSt_pv_endereco(rs.getString("endereco"));
    			cliente.setSt_pv_cidade(rs.getString("cidade"));
    			cliente.setSt_pv_estado(rs.getString("estado"));
    			cliente.setSt_pv_cep(rs.getString("cep"));
                retorno = cliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
