package models;

public class Cruce {
	private Equipo idequipo1;
	private Equipo idequipo2;
	private String observaciones;
	private int mejorEquipo;
	public Equipo getIdequipo1() {
		return idequipo1;
	}
	public void setIdequipo1(Equipo idequipo1) {
		this.idequipo1 = idequipo1;
	}
	public Equipo getIdequipo2() {
		return idequipo2;
	}
	public void setIdequipo2(Equipo idequipo2) {
		this.idequipo2 = idequipo2;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getMejorEquipo() {
		return mejorEquipo;
	}
	public void setMejorEquipo(int mejorEquipo) {
		this.mejorEquipo = mejorEquipo;
	}
	public Cruce(Equipo idequipo1, Equipo idequipo2, String observaciones, int mejorEquipo) {
		super();
		this.idequipo1 = idequipo1;
		this.idequipo2 = idequipo2;
		this.observaciones = observaciones;
		this.mejorEquipo = mejorEquipo;
	}
}
