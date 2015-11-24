package com.checkfacenow.model;

/**
 * 
 * @author john
 *
 */

public class ParametrosFaciales extends ParametroBiometrico{

	private Long cedula;
	private int registro;
	
	//Getters and setters
	public Long getCedula() {
		return cedula;
	}
	public void setCedula(Long long1) {
		this.cedula = long1;
	}
	public int getRegistro() {
		return registro;
	}
	public void setRegistro(int registro) {
		this.registro = registro;
	}
	
}

