package repository;

import dao.JDBCDaoPuntuacion;
public class repositorioPuntuacion {
	public void altaPuntuacion() throws Exception {
		   JDBCDaoPuntuacion p = new JDBCDaoPuntuacion();
		   p.insertaPuntuacion(0, 0, 0, 0, null, null, null, null, null, 0);
	}
}
