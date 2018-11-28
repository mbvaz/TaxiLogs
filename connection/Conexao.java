package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private Connection conexao;
	
	private final String URL = "jdbc:mysql://localhost:3306/logsTaxi";	
	private final String USER = "root";
	private final String PASSWORD = "";
	private final String TP_CONEXAO = "com.mysql.jdbc.Driver";
	
	public Connection abrirConexao() {
		try {
			Class.forName(TP_CONEXAO);
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectei ao bd");
		}catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		
		return conexao;
	}
	
	public void fechaConexao() {
		if(conexao !=null) {
			try {
				this.conexao.close();
				System.out.println("feshow");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
