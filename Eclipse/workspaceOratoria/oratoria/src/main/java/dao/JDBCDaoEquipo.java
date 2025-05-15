package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Equipo;

public class JDBCDaoEquipo extends JdbcDao{

	public JDBCDaoEquipo() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void insertaEquipo() {
		String nombreEquipo = "Sevilla";
		int anioCreacion = 1936;
		String query = "INSERT INTO equipo (Nombre, anyo_creacion) VALUES ('" + nombreEquipo + "', " + anioCreacion + ")";
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
	
	public void eliminaEquipo(Equipo e1) {
		String query = "delete from equipo where idEquipo = 20";
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
	
	public List<String> consultaEquipo() {
		String query = "select nombre from equipo";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<String> equipos = new ArrayList<>();

		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				equipos.add(rs.getString("nombre")); // Agrega cada usuario a la lista
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return equipos;
	}
	
	public void actualizaDatos(Equipo e1)  {
		String query = "update equipo set nombre = \"Deportivo\" where idEquipo = 15;";
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
  