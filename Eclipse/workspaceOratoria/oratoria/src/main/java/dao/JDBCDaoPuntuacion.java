package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class JDBCDaoPuntuacion extends JdbcDao{

	public JDBCDaoPuntuacion() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	public void insertaPuntuacion(int idequipo1, int idequipo2, int puntuacion1,int puntuacion2,String observacion1,String observacion2, String penalizacion1, String penalizacion2, FasesporEquipo fase,int idprueba) {
		int equipoGanador = validaEquipoGanador(puntuacion1,puntuacion2,idequipo1,idequipo2);
		boolean mejorOrador = devuelveSiMejorOrador(idprueba);
		List<String> listaParticipantesEquipo1 = listarParticipantes(idprueba,idequipo1);
		List<String> listaParticipantesEquipo2 = listarParticipantes(idprueba,idequipo2);
	    String lista1 = String.join(",", listaParticipantesEquipo1);
	    String lista2 = String.join(",", listaParticipantesEquipo2);
	    String query = "INSERT INTO puntuacion (idequipo1, idequipo2, puntuacion1, puntuacion2, observacion1, observacion2, penalizacion1, penalizacion2, fase, idprueba, equipoGanador, mejorOrador, listaParticipantes1, listaParticipantes2) VALUES ("+ idequipo1 + ", "+ idequipo2 + ", "+ puntuacion1 + ", "+ puntuacion2 + ", "+ "'" + observacion1 + "', "+ "'" + observacion2 + "', "+ "'" + penalizacion1 + "', "+ "'" + penalizacion2 + "', "+ "'" + fase.toString() + "', "+ idprueba + ", "+ equipoGanador + ", "+ mejorOrador + ", "+ "'" + lista1 + "', "+ "'" + lista2 + "')";
		Connection conn = null;
		Statement stmt = null;
		// si mejor orador: listar participantes
		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	public int validaEquipoGanador (int puntuacion1, int puntuacion2, int idequipo1, int idequipo2) {
		int equipoGanador = 0;
		if (puntuacion1 > puntuacion2) {
			equipoGanador = idequipo1;
		}
		else if (puntuacion1 == puntuacion2) {
			equipoGanador = 0;
		}
		else {
			equipoGanador = idequipo2;
		}
		return equipoGanador;
	}
	
	public boolean devuelveSiMejorOrador (int idprueba) {
	    boolean mejorOrador = false;
		String query = "select mejorOrador from prueba where idprueba =" + idprueba;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection(); // Método para obtener la conexión
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

	        if (rs.next()) {
	            int valor = rs.getInt("mejorOrador"); 
	            mejorOrador = (valor == 1);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return mejorOrador;
	}
	
	public List<String> listarParticipantes (int idprueba,int idequipo) {
		List<String> participantes = new ArrayList<>();
		if (devuelveSiMejorOrador(idprueba)) {
			String query = "select idparticipante from participante where idequipo =" + idequipo;
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = this.getConnection(); // Método para obtener la conexión
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					participantes.add(rs.getString("idparticipante")); // Agrega cada usuario a la lista
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		return participantes;
	}
}
