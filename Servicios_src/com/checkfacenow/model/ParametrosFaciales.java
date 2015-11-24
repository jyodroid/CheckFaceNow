package com.checkfacenow.model;

/**
 * 
 * @author john
 *
 */

public class ParametrosFaciales extends ParametroBiometrico{

	private Integer cedula;
	private int registro;
	
	//Getters and setters
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	public int getRegistro() {
		return registro;
	}
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	
}

