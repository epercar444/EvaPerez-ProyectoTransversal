package models;

public class Equipo {
	private static int contador = 0;
	private int id;
	private String nombre;
	private int anyo_creacion;
	public static int getContador() {
		return contador;
	}
	public static void setContador(int contador) {
		Equipo.contador = contador;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAnyo_creacion() {
		return anyo_creacion;
	}
	public void setAnyo_creacion(int anyo_creacion) {
		this.anyo_creacion = anyo_creacion;
	}
	public Equipo(int id, String nombre, int anyo_creacion) {
		super();
		this.contador++;
		this.id = id+contador;
		this.nombre = nombre;
		this.anyo_creacion = anyo_creacion;
	}
	
}
