package dao;

import java.sql.Connection;

import connection.ConnectionFactory;

public class BaseDAO {
	protected Connection connection;
	
	public BaseDAO() {
		this.connection=ConnectionFactory.getConnection();
	}
}
