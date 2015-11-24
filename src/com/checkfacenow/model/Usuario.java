package com.checkfacenow.model;

/**
 * 
 * @author john
 *
 */
public class Usuario {
	private String nombres;
	private String apellidos;
	private String cargo;
	private Integer cedula;
	private Empresa empresa;
	private ParametroBiometrico parametrosBiometricos;
	private String tipoUsuario;
	
	/**
	 *Getters and setters
	 */
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Integer getCedula() {
		return cedula;
	}
	public void setCedula(Integer ced) {
		this.cedula = ced;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public ParametroBiometrico getParametrosBiometricos() {
		return parametrosBiometricos;
	}
	public void setParametrosBiometricos(ParametroBiometrico parametrosBiometricos) {
		this.parametrosBiometricos = parametrosBiometricos;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
