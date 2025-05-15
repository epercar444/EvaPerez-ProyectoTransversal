package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoDebate extends JdbcDao{
	public JDBCDaoDebate() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	public void insertaDebate(int idequipo1,int idequipo2,FasesporEquipo fase) {
		String query = "INSERT INTO debate (idequipo1, idequipo2, fase) VALUES (" 
			    + idequipo1 + ", " 
			    + idequipo2 + ", '" 
			    + fase + "')";
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}

	public List<String> listarDebates (FasesporEquipo fase) {
		List<String> debates = new ArrayList<>();
		String query = "select iddebate from debate where fase=" + "'"+fase+"'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
			debates.add(rs.getString("iddebate")); // Agrega cada usuario a la lista
			}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		return debates;
	}
}
