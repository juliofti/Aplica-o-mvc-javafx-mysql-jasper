package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.company.modelo.Usuario;
import com.company.persistencia.Conexao;

public class DaoUsuario {

	Conexao c = new Conexao();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	Connection conn = c.getConexaoMySQL();

	public void cadastrar(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios(nome_usuario,senha_usuario,perfil_usuario)values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getSt_pv_nome_usuario());
			stmt.setString(2, usuario.getSt_pv_senha_usuario());
			stmt.setString(3, usuario.getSt_pv_perfil_usuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			com.company.util.Metodos.msgErro(null, "Falha no cadastro");
		}
	}

	public void deletar(int id) throws SQLException {

		String sql = "delete from usuarios where id_usuario = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public void atualizar(Usuario usuario) throws SQLException {
		String sql = "update usuarios set  nome_usuario = ?,senha_usuario = ?,perfil_usuario = ? where id_usuario = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, usuario.getSt_pv_nome_usuario());
		stmt.setString(2, usuario.getSt_pv_senha_usuario());
		stmt.setString(3, usuario.getSt_pv_perfil_usuario());
		stmt.setInt(4, usuario.getIn_pv_id_usuario());
		stmt.execute();
		stmt.close();
	}

	public boolean checkLogin(String login, String senha) {
		Connection conn = c.getConexaoMySQL();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {

			stmt = conn.prepareStatement("SELECT * FROM usuarios where nome_usuario = ? and senha_usuario = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);

			rs = stmt.executeQuery();
			if (rs.next()) {
				check = true;
			}

		} catch (SQLException e) {
			Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, e);
		}
		return check;
	}

	public ArrayList<Usuario> consultar(String nome) throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "Select * from usuarios where nome_usuario like'%" + nome + "%'";

		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setIn_pv_id_usuario(rs.getInt("id_usuario"));
			
			usuario.setSt_pv_nome_usuario(rs.getString("nome_usuario"));
			usuario.setSt_pv_senha_usuario(rs.getString("senha_usuario"));
			usuario.setSt_pv_perfil_usuario(rs.getString("perfil_usuario"));
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public String buscarUsuario(String nome) throws SQLException {
		String retorno = null;
		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM usuarios where nome_usuario ='"+nome+"'";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			usuario.setSt_pv_perfil_usuario(rs.getString("perfil_usuario"));
			retorno = usuario.getSt_pv_perfil_usuario();
		}
		
		return retorno;
	}

}
