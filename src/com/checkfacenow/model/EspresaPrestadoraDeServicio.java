package com.checkfacenow.model;
/**
 * 
 * @author john
 *
 */
public class EspresaPrestadoraDeServicio extends Empresa{
	private String estadoEmpresa;
	private String serviciosPrestados;
	
	/**
	 * getters and setters
	 */
	public String getEstadoEmpresa() {
		return estadoEmpresa;
	}
	public void setEstadoEmpresa(String estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}
	public String getServiciosPrestados() {
		return serviciosPrestados;
	}
	public void setServiciosPrestados(String serviciosPrestados) {
		this.serviciosPrestados = serviciosPrestados;
	}
}