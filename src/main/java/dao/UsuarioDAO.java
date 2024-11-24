package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO extends BaseDAO {
	public UsuarioDAO() {
		super();
	}
	
	public Usuario login(String email, String senha) {
		String sql="SELECT * FROM USUARIO WHERE EMAIL=? AND SENHA =?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				return new Usuario(rs.getInt("id"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"),rs.getString("numero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public boolean isUsuarioAdmin(int id) {
		String sql="SELECT IS_ADMIN FROM USUARIO WHERE ID=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				return rs.getBoolean("is_admin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return false;
	}
}
