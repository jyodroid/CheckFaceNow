package com.checkfacenow.vistamovil;

import java.util.Calendar;

import com.checkfacenow.logicamovil.SoapConection;
import com.checkfacenow.model.Cita;
import com.checkfacenow.model.Usuario;
import com.trunk.checkfacenow.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CargadorActivity extends Activity {

	protected static final String tag = "com.checkfacenow.logicamovil.CargadorActivity.ERRORES";

	Cita cita = new Cita();
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	private static boolean error = false;
	private static byte[] foto;
	@Override
	protected void onCreate(Bundle ciclo) {
		super.onCreate(ciclo);
		setContentView(R.layout.activity_cargador); 
		mProgress = (ProgressBar) findViewById(R.id.pbarProgreso);
	    
		Intent viejoIntent = getIntent();
		foto = viejoIntent.getByteArrayExtra("foto");
		
		//Implemento un thread nuevo para evitar la pantalla negra mientras carga
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// llamo aqui la conexion al webserver
				//inicio la conexión
				networkThread.start();
				
				//Espero que termine el hilo de conexión
				while (networkThread.isAlive()) {
					mProgressStatus = mProgressStatus - 1;
					mProgress.setProgress(mProgressStatus);
				}
				
				if(error){
					Intent conError = new Intent(CargadorActivity.this, MainActivity.class);
					anuncieErrorConexion();
					startActivity(conError);
					error = false;
					finish();
					return;
				}
				
				/**
				 * 	private int accesoRequerido;
					private String estadoCita;
					private String fechaServicio;
					private Usuario personaPrestadora;//Usuario con cÃ©dula en la tabla cita
					private double tiempoEstimado;
					private String tipoSercicio;
					private String horaInicioServicio;
					private String horaCita;
					private String horafinalizacionServicio;
				 */
				Intent nuevoIntent = new Intent(CargadorActivity.this, DetallesActivity.class);
				//Datos del usuario
				nuevoIntent.putExtra("nombres", cita.getPersonaPrestadora().getNombres());
				nuevoIntent.putExtra("apellidos", cita.getPersonaPrestadora().getApellidos());
				nuevoIntent.putExtra("cargo", cita.getPersonaPrestadora().getCargo());
				nuevoIntent.putExtra("cedula", cita.getPersonaPrestadora().getCedula());
				
				//Datos de la cita
				nuevoIntent.putExtra("accesoRequerido", cita.getAccesoRequerido());
				nuevoIntent.putExtra("estadoCita", cita.getEstadoCita());
				nuevoIntent.putExtra("fechaServicio", cita.getFechaServicio());
				nuevoIntent.putExtra("tiempoEstimado", cita.getTiempoEstimado());
				nuevoIntent.putExtra("horaCita", cita.getHoraCita());
				
				//Datos Empresa
				nuevoIntent.putExtra("empresa", cita.getPersonaPrestadora().getEmpresa().getNombre());
				nuevoIntent.putExtra("nit", cita.getPersonaPrestadora().getEmpresa().getNit());
				nuevoIntent.putExtra("direccion", cita.getPersonaPrestadora().getEmpresa().getDireccion());
				nuevoIntent.putExtra("telefono", cita.getPersonaPrestadora().getEmpresa().getTelefono());
				startActivity(nuevoIntent);
				finish();	
			}
		}, 5000);
	}

	private void anuncieErrorConexion(){
		Toast.makeText(this, "ERROR DE CONECCION",Toast.LENGTH_LONG).show();
	}
	
	//Conexion con SOAP
	Thread networkThread = new Thread() {
		@Override
		public void run() {
			try {
				String fotoArriba;
				//Creo un objeto de conexión con el parámetro de servicio para llamar el webserve de subir fotografia
				SoapConection scUpload = new SoapConection("uploadimagenMovil");
				//Llamo el método de subida de imagen y almaceno el resultado de la transaccion
				fotoArriba = scUpload.enviaImagen(foto);
				
				//Verifico que halla montado la foto mas reciente
				if (fotoArriba.compareTo("exito")==0){
					
					//Creo otro objeto de conexion con un paramtro de web service diferente
					SoapConection scCallData = new SoapConection("callData");
					 
					
					//Llama el web service con la logica para el reconocimiento facial
					scCallData.callIdentificador();
					
					cita = scCallData.getCita();
					
				}
			}
			catch (Exception e) {
				//Aviso al activity que hubo un error de conexion
				error = true;
				//Para efectos de depuración de código
				Log.d(tag, "ERROR DE CONECCION SOAP"+" "+e.getMessage() + " " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
	};
}
