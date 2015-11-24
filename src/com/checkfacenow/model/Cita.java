package com.checkfacenow.model;

import java.util.Date;

/**
 * 
 * @author john
 *
 */
public class Cita {
	private int accesoRequerido;
	private String estadoCita;
	private String fechaServicio;
	private Usuario personaPrestadora;//Usuario con cédula en la tabla cita
	private double tiempoEstimado;
	private String tipoSercicio;
	private String horaInicioServicio;
	private String horaCita;
	private String horafinalizacionServicio;	
	
	/**
	 * Método: calcularTiempoServicio
	 * @return double
	 * @param No
	 * Descripción: calcula el tiempo en minutos que tardó una persona en prestar el
	 * servicio en la empresa. 
	 */
	public double calcularTiempoServicio(){
		
		/*Se obtiene la cantidad de tiempo que tomó a la persona prestadora 
		en realizar el servicio*/
		return 0.0;
	}
	
	
	/**
	 * Getters and Setters
	 */

	public int getAccesoRequerido() {
		return accesoRequerido;
	}

	public void setAccesoRequerido(int accesoRequerido) {
		this.accesoRequerido = accesoRequerido;
	}

	public String getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}

	public String getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(String fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

	public Usuario getPersonaPrestadora() {
		return personaPrestadora;
	}

	public void setPersonaPrestadora(Usuario personaPrestadora) {
		this.personaPrestadora = personaPrestadora;
	}

	public double getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(double tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public String getTipoSercicio() {
		return tipoSercicio;
	}

	public void setTipoSercicio(String tipoSercicio) {
		this.tipoSercicio = tipoSercicio;
	}

	public String getHoraInicioServicio() {
		return horaInicioServicio;
	}

	public void setHoraInicioServicio(String horaInicioServicio) {
		this.horaInicioServicio = horaInicioServicio;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}

	public String getHorafinalizacionServicio() {
		return horafinalizacionServicio;
	}

	public void setHorafinalizacionServicio(String horafinalizacionServicio) {
		this.horafinalizacionServicio = horafinalizacionServicio;
	}
}
