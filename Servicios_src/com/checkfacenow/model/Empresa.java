package com.checkfacenow.model;
/**
 * 
 * @author john
 *
 */
public class Empresa {
	private String direccion;
	private Integer nit;
	private String nombre;
	private String telefono;
	
	//Getters and setters
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getNit() {
		return nit;
	}
	public void setNit(Integer nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}