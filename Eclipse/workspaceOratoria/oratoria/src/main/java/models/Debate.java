package models;

import dao.FasesporEquipo;

public class Debate {
	private int iddebate;
	private Equipo idequipo1;
	private Equipo idequipo2;
	private FasesporEquipo fase;
	public int getIddebate() {
		return iddebate;
	}
	public void setIddebate(int iddebate) {
		this.iddebate = iddebate;
	}
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
	public FasesporEquipo getFase() {
		return fase;
	}
	public void setFase(FasesporEquipo fase) {
		this.fase = fase;
	}
	public Debate(int iddebate, Equipo idequipo1, Equipo idequipo2, FasesporEquipo fase) {
		super();
		this.iddebate = iddebate;
		this.idequipo1 = idequipo1;
		this.idequipo2 = idequipo2;
		this.fase = fase;
	}
}
