package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Equipo;

public class JDBCDaoCruce extends JdbcDao{
	public JDBCDaoCruce() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<String> consultaCruce(String datoAconsultar, int idcruce) {
		String query = "select " + datoAconsultar+ " from cruce where idcruce= " + idcruce;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> consulta = new ArrayList<>();

		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				consulta.add(rs.getString(datoAconsultar)); // Agrega cada usuario a la lista
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return consulta;
	}
	
	public void actualizaDatos(String datoAconsultar, int idcruce, Object nuevoDato)  {
		String query = "UPDATE cruce SET " + datoAconsultar + " = '" + nuevoDato + "' WHERE idcruce = " + idcruce;
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
}
