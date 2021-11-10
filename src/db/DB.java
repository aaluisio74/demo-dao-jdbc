package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection; //Para o Connection
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	// Variável de referência à classe de CONEXÃO. Estará no método para conectar
	// com o Banco de Dados.
	private static Connection conn = null;

	// Método para conectar com o banco de dados.
	public static Connection getConnection() {
		if (conn == null) {
			try {
				// Pega as propriedades do método loadProperties
				Properties props = loadProperties();
				// URL do banco de dados. DBURL foi definido no arquivo db.properties
				String url = props.getProperty("dburl");
				// Para obter uma conexão com o banco de dados com a URL do banco e as
				// propriedades de conexão.
				// DriverManager precisa de tratamento para uma possível exceção: SQLException.
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// O SQLException é derivado da classe Exception que obriga o tratamento da
				// exceção.
				/*
				 * Como o DbException é derivada da RuntimeException, o programa não precisará
				 * colocar toda hora o try-catch. Pode colocar try-catch somente quando achar
				 * necessário.
				 */
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	// Método para fechar a conexão.
	public static void closeConnection() {
		if (conn != null) {
			try {
				// Essa operação precisa de tratamento para uma possível exceção: SQLException.
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	// Método para carregar as propriedades que estão definidas no arquivo
	// db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	// Método para fechar o Statement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				/*
				 * Lança a exceção personalizada passando a mensagem da exceção SQLException.
				 * Lança uma exceção RuntimeException. Volte ao arquivo Program.java
				 */
				throw new DbException(e.getMessage());
			}
		}
	}

	// Método para fechar o Statement
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				/*
				 * Lança a exceção personalizada passando a mensagem da exceção SQLException.
				 * Lança uma exceção RuntimeException. Volte ao arquivo Program.java
				 */
				throw new DbException(e.getMessage());
			}
		}
	}
}
