package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Relatorio;

public class RelatorioDAO extends BaseDAO{
	public RelatorioDAO() {
		super();
		
	}
	public List <Relatorio> listarTodos(){
		String sql= "SELECT * FROM RELATORIO";
		List <Relatorio> relatorios = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				relatorios.add(
						new Relatorio(rs.getInt("id_relatorio"),rs.getInt("id_estadio"),rs.getString("descricao"),rs.getString("conclusao"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return relatorios;
		}
	
	public List <Relatorio> listarRelatoriosPorEstadio(int idEstadio){
		String sql= "SELECT * FROM RELATORIO WHERE ID_ESTADIO=?";
		List <Relatorio> relatorios = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, idEstadio);
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				relatorios.add(
						new Relatorio(rs.getInt("id_relatorio"),rs.getInt("id_estadio"),rs.getString("descricao"),rs.getString("conclusao"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return relatorios;
		}
	
	public void criarRelatorio(Relatorio relatorio) {
		String sql="INSERT INTO RELATORIO(id_estadio,descricao,conclusao) VALUES (?,?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, relatorio.getIdEstadio());
			stmt.setString(2, relatorio.getDescricao());
			stmt.setString(3,relatorio.getDescricao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void removerRelatorio(int idRelatorio) {
		String sql="DELETE FROM RELATORIO WHERE id_relatorio=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, idRelatorio);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
