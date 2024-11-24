package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoChao;
import model.Usuario;

public class TipoChaoDAO extends BaseDAO{
	public TipoChaoDAO() {
		super();
		
	}
	
	public List <TipoChao> listarTodos(){
		String sql= "SELECT * FROM TIPO_CHAO";
		List <TipoChao> tipos = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				tipos.add(
						new TipoChao(rs.getInt("id_tipo_chao"),rs.getString("nome_chao"),rs.getDouble("geracao_estimativa_kw"),rs.getDouble("custo_por_metro"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return tipos;
		}
}
