package com.checkfacenow.vistamovil;

import com.trunk.checkfacenow.R;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

public class DetallesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles);

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		//Se recive el intent
		Intent intent = getIntent();

		//Se muestra el mensaje con un text view como vista principal de la actividad
		TextView visualiza = new TextView(this);

		/**
		 * Contenido del intent
		 * 		"accesoRequerido"
				"estadoCita"
				"fechaServicio"
				"tiempoEstimado"
				"horaCita"
		 */
		
		visualiza.setBackgroundColor(Color.LTGRAY);
		visualiza.setTextSize(15);
		visualiza.setGravity(50);
		
		//Orgnizo el contenido del intent
		
		//Datos de la persona
		String nombres = intent.getStringExtra("nombres");
		String apellidos = intent.getStringExtra("apellidos");
		String cargo = intent.getStringExtra("cargo");
		int cedula = intent.getIntExtra("cedula",0);
		
		//Datos de la cita
		int acceso = intent.getIntExtra("accesoRequerido", 0);
		String estadoCita = intent.getStringExtra("estadoCita");
		String fecha = intent.getStringExtra("fechaServicio");
		String tiempo = intent.getStringExtra("tiempoEstimado");
		String hora = intent.getStringExtra("horaCita");
		
		//Datos de la empresa
		String empresa = intent.getStringExtra("empresa");
		int nit = intent.getIntExtra("nit", 0);
		String direccion = intent.getStringExtra("direccion");
		String telefono = intent.getStringExtra("telefono");
		
		if (estadoCita.compareTo("Inexistente")==0){
			visualiza.setTextSize(40);
			visualiza.setText("No tiene citas para el dia de hoy");
			
			//set the view as activity layout
			setContentView(visualiza);
		}else{
			visualiza.setText(
					"Información de la persona:\n"+
					"-----------------------------\n"+
					"Nombres: "+nombres+"\n"+
					"Apellidos: "+apellidos+"\n"+
					"CC: "+cedula+"\n"+
					"Cargo: "+cargo+"\n\n"+
					"Información de la cita:\n"+
					"-----------------------------\n"+
					"Estado de la cita: "+ estadoCita + "\n" +
					"Acceso Requerido: "+ acceso + "\n" +
					"Fecha de la cita: "+ fecha + "\n" +
					"Hora de la cita: " + hora + "\n" +
					"Tiempo estimado: " + tiempo+"\n\n"+
					"Información de la Empresa:\n"+
					"-----------------------------\n"+
					"Empresa: "+ empresa + "\n" +
					"Nit: "+ nit + "\n" +
					"Dirección: "+ direccion + "\n" +
					"Telefono: " + telefono);
			
			//set the view as activity layout
			setContentView(visualiza);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detalles, menu);
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)  {
			Intent intentViejo = getIntent();
			String sesion = intentViejo.getStringExtra("sesion");
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("sesion", sesion);
			startActivity(intent);
			finish();
		}
		return super.onKeyUp(keyCode, event); 
	}
}