package com.checkfacenow.model;
/**
 * 
 * @author john
 *
 */
public class UsuarioIdentificador extends Usuario{
	private int niverAutorizacion;
	private String seccionACargo;
	
	/**
	 * 
	 * getters and setters
	 */
	public int getNiverAutorizacion() {
		return niverAutorizacion;
	}
	public void setNiverAutorizacion(int niverAutorizacion) {
		this.niverAutorizacion = niverAutorizacion;
	}
	public String getSeccionACargo() {
		return seccionACargo;
	}
	public void setSeccionACargo(String seccionACargo) {
		this.seccionACargo = seccionACargo;
	}	
}
