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

	// Vari�vel de refer�ncia � classe de CONEX�O. Estar� no m�todo para conectar
	// com o Banco de Dados.
	private static Connection conn = null;

	// M�todo para conectar com o banco de dados.
	public static Connection getConnection() {
		if (conn == null) {
			try {
				// Pega as propriedades do m�todo loadProperties
				Properties props = loadProperties();
				// URL do banco de dados. DBURL foi definido no arquivo db.properties
				String url = props.getProperty("dburl");
				// Para obter uma conex�o com o banco de dados com a URL do banco e as
				// propriedades de conex�o.
				// DriverManager precisa de tratamento para uma poss�vel exce��o: SQLException.
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				// O SQLException � derivado da classe Exception que obriga o tratamento da
				// exce��o.
				/*
				 * Como o DbException � derivada da RuntimeException, o programa n�o precisar�
				 * colocar toda hora o try-catch. Pode colocar try-catch somente quando achar
				 * necess�rio.
				 */
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	// M�todo para fechar a conex�o.
	public static void closeConnection() {
		if (conn != null) {
			try {
				// Essa opera��o precisa de tratamento para uma poss�vel exce��o: SQLException.
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	// M�todo para carregar as propriedades que est�o definidas no arquivo
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

	// M�todo para fechar o Statement
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				/*
				 * Lan�a a exce��o personalizada passando a mensagem da exce��o SQLException.
				 * Lan�a uma exce��o RuntimeException. Volte ao arquivo Program.java
				 */
				throw new DbException(e.getMessage());
			}
		}
	}

	// M�todo para fechar o Statement
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				/*
				 * Lan�a a exce��o personalizada passando a mensagem da exce��o SQLException.
				 * Lan�a uma exce��o RuntimeException. Volte ao arquivo Program.java
				 */
				throw new DbException(e.getMessage());
			}
		}
	}
}
