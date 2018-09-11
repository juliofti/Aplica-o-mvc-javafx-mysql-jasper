package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.company.modelo.Roupa;
import com.company.persistencia.Conexao;


public class DaoRoupa {
	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();

	public void cadastrar(Roupa roupa) {
		try {
			String sql = "insert into tipo_roupa(descricao,tecido,cor)values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, roupa.getSt_pv_descricao());
			stmt.setString(2, roupa.getSt_pv_tecido());
			stmt.setString(3, roupa.getSt_pv_cor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro(null, "Falha no cadastro");
		}
	}

	public void deletar(int id) throws SQLException {

		String sql = "delete from tipo_roupa where id_roupa = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public void atualizar(Roupa roupa) throws SQLException {
		String sql = "update tipo_roupa set descricao = ?,tecido=?,cor = ? where id_roupa = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, roupa.getSt_pv_descricao());
		stmt.setString(2, roupa.getSt_pv_tecido());
		stmt.setString(3, roupa.getSt_pv_cor());
		stmt.setInt(4, roupa.getIn_pv_id_roupa());
		stmt.execute();
		stmt.close();
	}

	public ArrayList<Roupa> consultar(String descricao) throws Exception {
		ArrayList<Roupa> roupas = new ArrayList<Roupa>();
		String sql = "Select * from tipo_roupa where descricao like'%" + descricao + "%'";

		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Roupa roupa = new Roupa();
			roupa.setIn_pv_id_roupa(rs.getInt("id_roupa"));
			roupa.setSt_pv_descricao(rs.getString("descricao"));
			roupa.setSt_pv_tecido(rs.getString("tecido"));
			roupa.setSt_pv_cor(rs.getString("cor"));
			roupas.add(roupa);
		}
		return roupas;
	}
	
	 public Roupa buscar(Roupa roupa) {
	        String sql = "SELECT * FROM tipo_roupa WHERE id_roupa=?";
	        Roupa retorno = new Roupa();
	        
	        try {
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, roupa.getIn_pv_id_roupa());
	            rs = stmt.executeQuery();
	            if (rs.next()) {
	            	retorno.setIn_pv_id_roupa(rs.getInt("id_roupa"));
	    			retorno.setSt_pv_descricao(rs.getString("descricao"));
	    			retorno.setSt_pv_tecido(rs.getString("tecido"));
	    			retorno.setSt_pv_cor(rs.getString("cor"));
	    		
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(DaoRoupa.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return retorno;
	    }
}
