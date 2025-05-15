package repository;

import dao.JDBCDaoEquipo;
import models.Equipo;
  
public class Repositorioequipo { 
	public void altaEquipo() throws Exception {
		   JDBCDaoEquipo p = new JDBCDaoEquipo();
		   p.insertaEquipo();
	}
	public void eliminaEquipo(Equipo e) throws Exception {
		   JDBCDaoEquipo p = new JDBCDaoEquipo();
		   p.eliminaEquipo(e);
	}
	public void consultaEquipo() throws Exception {
		   JDBCDaoEquipo p = new JDBCDaoEquipo();
		   System.out.println(p.consultaEquipo());
	}
	public void actualizaDatos(Equipo e) throws Exception {
		   JDBCDaoEquipo p = new JDBCDaoEquipo();
		   p.actualizaDatos(e);
	}
}
 