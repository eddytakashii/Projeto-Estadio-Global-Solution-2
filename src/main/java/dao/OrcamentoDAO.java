package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Orcamento;

public class OrcamentoDAO extends BaseDAO{
	public OrcamentoDAO() {
		super();
	}
	public List <Orcamento> listarTodosPendentes(){
		String sql= "SELECT * FROM ORCAMENTO WHERE APROVADO=FALSE";
		List <Orcamento> orcamentos = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				orcamentos.add(
						new Orcamento(rs.getInt("id_orcamento"),rs.getInt("id_estadio"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gasto_mensal_kw"),rs.getDouble("teto_gasto"),rs.getBoolean("aprovado"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return orcamentos;
		}
	
	public boolean criarOrcamento(Orcamento orcamento) {
		String sql="INSERT INTO ORCAMENTO(id_estadio,tamanho,area_campo,gasto_mensal_kw,teto_gasto,aprovado) VALUES (?,?,?,?,?,?)";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, orcamento.getIdEstadio());
			stmt.setDouble(2, orcamento.getTamanho());
			stmt.setDouble(3,orcamento.getAreaCampo());
			stmt.setDouble(4, orcamento.getGastosMensalKw());
			stmt.setDouble(5,orcamento.getTetoGasto());
			stmt.setBoolean(6, orcamento.isAprovado());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	public Orcamento buscarOrcamentoPorId(int idOrcamento){
		String sql= "SELECT * FROM ORCAMENTO WHERE ID_ORCAMENTO=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, idOrcamento);
			ResultSet rs=stmt.executeQuery();
			
			if (rs.next()) {
				
						return new Orcamento(rs.getInt("id_orcamento"),rs.getInt("id_estadio"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gasto_mensal_kw"),rs.getDouble("teto_gasto"),rs.getBoolean("aprovado"))
						;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
		}
	public List <Orcamento> listarTodos(){
		String sql= "SELECT * FROM ORCAMENTO";
		List <Orcamento> orcamentos = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				orcamentos.add(
						new Orcamento(rs.getInt("id_orcamento"),rs.getInt("id_estadio"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gasto_mensal_kw"),rs.getDouble("teto_gasto"),rs.getBoolean("aprovado"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return orcamentos;
		}
	public boolean atualizarOrcamento(Orcamento orcamento) {
		String sql="UPDATE ORCAMENTO SET ID_ESTADIO=?,TAMANHO=?,AREA_CAMPO=?,GASTO_MENSAL_KW=?,TETO_GASTO=?,APROVADO=? WHERE ID_ORCAMENTO=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, orcamento.getIdEstadio());
			stmt.setDouble(2, orcamento.getTamanho());
			stmt.setDouble(3,orcamento.getAreaCampo());
			stmt.setDouble(4, orcamento.getGastosMensalKw());
			stmt.setDouble(5,orcamento.getTetoGasto());
			stmt.setBoolean(6, orcamento.isAprovado());
			stmt.setInt(7, orcamento.getIdOrcamento());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
}
