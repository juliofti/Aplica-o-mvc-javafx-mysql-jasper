package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.company.modelo.Roupa;
import com.company.modelo.Servico;
import com.company.persistencia.Conexao;

public class DaoServico {
	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();

	public void cadastrar(Servico servico) {
		try {
			String sql = "insert into servico(descricao_servico,valor_servico)values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, servico.getSt_pv_descricao());
			stmt.setDouble(2, servico.getDb_pv_valor_servico());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro(null, "Falha no cadastro");
		}
	}

	public void deletar(int id) throws SQLException {

		String sql = "delete from servico where id_servico = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public void atualizar(Servico servico) throws SQLException {
		String sql = "update servico set descricao_servico = ?,valor_servico=? where id_servico = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, (servico.getSt_pv_descricao()));
		stmt.setDouble(2, servico.getDb_pv_valor_servico());
		stmt.setInt(3, servico.getIn_pv_id_servico());
		stmt.execute();
		stmt.close();
	}

	public ArrayList<Servico> consultar(String descricao) throws Exception {
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		String sql = "Select * from servico where descricao_servico like'%" + descricao + "%'";

		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Servico servico = new Servico();
			servico.setIn_pv_id_servico(rs.getInt("id_servico"));
			servico.setSt_pv_descricao(rs.getString("descricao_servico"));
			servico.setDb_pv_valor_servico(rs.getDouble("valor_servico"));
			servicos.add(servico);
		}
		return servicos;

	}


public Servico buscar(Servico servico) {
    String sql = "SELECT * FROM servico WHERE id_servico=?";
    Servico retorno = new Servico();
    
    try {
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, servico.getIn_pv_id_servico());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        	retorno.setIn_pv_id_servico(rs.getInt("id_servico"));
			retorno.setSt_pv_descricao(rs.getString("descricao_servico"));
			retorno.setDb_pv_valor_servico(rs.getDouble("valor_servico"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(DaoServico.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}


}
