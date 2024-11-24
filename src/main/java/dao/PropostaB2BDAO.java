package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PropostaB2B;

public class PropostaB2BDAO extends BaseDAO{
	public PropostaB2BDAO() {
		super();
	}
	public boolean criarPropostaB2B(PropostaB2B propostaB2B) {
		String sql="INSERT INTO PROPOSTA_B2B(id_orcamento,id_tipo_chao,valor,descricao,reducao_kw) VALUES(?,?,?,?,?) ";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, propostaB2B.getIdOrcamento());
			stmt.setInt(2, propostaB2B.getIdTipoChao());
			stmt.setDouble(3,propostaB2B.getValor());
			stmt.setString(4,propostaB2B.getDescricao());
			stmt.setDouble(5,propostaB2B.getReducaoKw());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	public List <PropostaB2B> listarTodos(){
		String sql= "SELECT * FROM PROPOSTA_B2B";
		List <PropostaB2B> propostaB2B = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				propostaB2B.add(
						new PropostaB2B(rs.getInt("id_proposta"),rs.getInt("id_orcamento"),rs.getInt("id_tipo_chao"),rs.getDouble("valor"),rs.getString("descricao"),rs.getDouble("reducao_kw"))
						);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return propostaB2B;
		}
}
