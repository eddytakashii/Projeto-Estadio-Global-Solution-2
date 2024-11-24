package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.TransacaoB2B;


public class TransacaoB2BDAO extends BaseDAO{
	public TransacaoB2BDAO() {
		super();
	}
	public boolean criarTransacaoB2B(TransacaoB2B transacaoB2B) {
		String sql="INSERT INTO TRANSACAO_B2B(id_proposta,id_admin,valor,descricao) VALUES(?,?,?,?) ";
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			stmt.setInt(1, transacaoB2B.getIdProposta());
			stmt.setInt(2, transacaoB2B.getIdAdmin());
			stmt.setDouble(3,transacaoB2B.getValor());
			stmt.setString(4,transacaoB2B.getDescricao());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
	}
	public List <TransacaoB2B> listarTodos(){
		String sql= "SELECT * FROM TRANSACAO_B2B";
		List <TransacaoB2B> transacaoB2B = new ArrayList<>();
		try(PreparedStatement stmt=connection.prepareStatement(sql)) {
			ResultSet rs=stmt.executeQuery();
			
			while (rs.next()) {
				transacaoB2B.add(
						new TransacaoB2B(rs.getInt("id_transacao"),rs.getInt("id_proposta"),rs.getInt("id_admin"),rs.getDouble("valor"),rs.getString("descricao")
						));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return transacaoB2B;
		}
}
