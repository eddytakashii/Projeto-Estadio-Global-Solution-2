package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Estadio;
import model.Usuario;

public class EstadioDAO extends BaseDAO{
	public EstadioDAO() {
		super();
	}
	public List <Estadio> listarTodos(){
	    String sql ="SELECT e.id_estadio, u.nome, u.email, u.senha, u.numero,  e.tamanho, e.area_campo, e.gastos_mensais_kw, e.gastos_mensais_reais FROM usuario u JOIN estadio e ON u.id = e.id_usuario";
		List <Estadio> estadios = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				estadios.add(
						new Estadio(rs.getInt("id_estadio"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"),rs.getString("numero"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gastos_mensais_kw"),rs.getDouble("gastos_mensais_reais"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return estadios;
}
	
	public boolean criarEstadio(Estadio estadio) {
		String sqlUsuario="INSERT INTO USUARIO(nome, email,senha,numero) VALUES(?,?,?,?)";
		String sqlEstadio="INSERT INTO ESTADIO (id_usuario,tamanho,area_campo,gastos_mensais_kw,gastos_mensais_reais) VALUES (?, ?,?,?,?)";
		String sqlSelecionarId="SELECT MAX(id) FROM USUARIO";
		try(PreparedStatement stmtUs=connection.prepareStatement(sqlUsuario);
				PreparedStatement stmtEs=connection.prepareStatement(sqlEstadio);
				PreparedStatement stmtSl=connection.prepareStatement(sqlSelecionarId)){
			stmtUs.setString(1,estadio.getNome());
			stmtUs.setString(2,estadio.getEmail());
			stmtUs.setString(3,estadio.getSenha());
			stmtUs.setString(4,estadio.getNumero());
			stmtUs.executeUpdate();
			
			ResultSet rsId = stmtSl.executeQuery();
			
			if (rsId.next()) {
				int idUsuario = rsId.getInt(1);
				if (idUsuario>0) {
					stmtEs.setInt(1, idUsuario);
					stmtEs.setDouble(2, estadio.getTamanho());
					stmtEs.setDouble(3, estadio.getAreaCampo());
					stmtEs.setDouble(4, estadio.getGastosMensalKw());
					stmtEs.setDouble(5, estadio.getGastosMensalReais());
					stmtEs.executeUpdate();
					
					return true;
				}else {
					System.out.println("Erro: id do usuario não encontrado");
				}
			}else {
				System.out.println("Erro:não foi possível buscar ultimo id criado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	
	public boolean criarEstadioParaUsuario(Estadio estadio, Usuario usuario) {
		String sqlEstadio="INSERT INTO ESTADIO (id_usuario,tamanho,area_campo,gastos_mensais_kw,gastos_mensais_reais) VALUES (?, ?,?,?,?)";
		try(
				PreparedStatement stmtEs=connection.prepareStatement(sqlEstadio))
				{
					stmtEs.setInt(1, usuario.getId());
					stmtEs.setDouble(2, estadio.getTamanho());
					stmtEs.setDouble(3, estadio.getAreaCampo());
					stmtEs.setDouble(4, estadio.getGastosMensalKw());
					stmtEs.setDouble(5, estadio.getGastosMensalReais());
					stmtEs.executeUpdate();
					
					return true;
				}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	
	public boolean atualizarEstadio(Estadio estadio) {
		String sqlUsuario="UPDATE USUARIO SET NOME=?,SENHA=?,NUMERO=? WHERE ID=?";
		String sqlEstadio="UPDATE ESTADIO SET TAMANHO=?,AREA_CAMPO=?,GASTOS_MENSAIS_KW=?,GASTOS_MENSAIS_REAIS=? WHERE ID_USUARIO=?";
		try(PreparedStatement stmtUs=connection.prepareStatement(sqlUsuario);
				PreparedStatement stmtEs=connection.prepareStatement(sqlEstadio);){
			stmtUs.setString(1,estadio.getNome());
			stmtUs.setString(2,estadio.getSenha());
			stmtUs.setString(3,estadio.getNumero());
			stmtUs.setInt(4,estadio.getId());
			stmtUs.executeUpdate();
		
			int rUsuario =stmtUs.executeUpdate();
			
					stmtEs.setDouble(1, estadio.getTamanho());
					stmtEs.setDouble(2, estadio.getAreaCampo());
					stmtEs.setDouble(3, estadio.getGastosMensalKw());
					stmtEs.setDouble(4, estadio.getGastosMensalReais());
					stmtEs.setInt(5,estadio.getId());
					int rEstadio=stmtEs.executeUpdate();
					
					return rEstadio>0 && rUsuario>0;
				
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	
	public Estadio buscarPorUsuarioId(int idUsuario){
	    String sql ="SELECT u.id, u.nome, u.email, u.senha, u.numero,  e.tamanho, e.area_campo, e.gastos_mensais_kw, e.gastos_mensais_reais FROM usuario u JOIN estadio e ON u.id = e.id_usuario WHERE u.id=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, idUsuario);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				
						return new Estadio(rs.getInt("id"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"),rs.getString("numero"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gastos_mensais_kw"),rs.getDouble("gastos_mensais_reais"))
						;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public Estadio buscarIdEstadioPorEmail(String email){
	    String sql ="SELECT e.id_estadio, u.nome, u.email, u.senha, u.numero,  e.tamanho, e.area_campo, e.gastos_mensais_kw, e.gastos_mensais_reais FROM usuario u JOIN estadio e ON u.id = e.id_usuario WHERE u.email=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs=stmt.executeQuery();
			
			if(rs.next()) {
				
						return new Estadio(rs.getInt("id_estadio"),rs.getString("nome"),rs.getString("senha"),rs.getString("email"),rs.getString("numero"),rs.getDouble("tamanho"),rs.getDouble("area_campo"),rs.getDouble("gastos_mensais_kw"),rs.getDouble("gastos_mensais_reais"))
						;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	public boolean removerEstadio(int idEstadio) {
		String sql = "DELETE FROM ESTADIO WHERE ID_ESTADIO=?";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, idEstadio);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	
	
}