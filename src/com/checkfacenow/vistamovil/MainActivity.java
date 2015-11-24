package com.checkfacenow.vistamovil;

import com.checkfacenow.logicamovil.SoapConection;
import com.trunk.checkfacenow.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String tag = "com.trunk.checkfacenow.MainActivity.ERRORES";
	public static final String MENSAJE_EXTRA = "com.trunk.checkfacenow.MainActivity.MensajeExtra";
	private static boolean error = false;
	private static boolean credenciales = false;
	private static String user;
	private static String pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		boolean tieneCamara = checkCameraHardware(this);
		try {
			if (tieneCamara == false){
				Toast.makeText(this, "dispositivo sin camara, saliendo...",Toast.LENGTH_LONG).show();
				Thread.sleep(2000);
				throw new Exception("Dispositivo sin camara");
			}
		} catch (Exception e) {
			Log.d(tag, e.getMessage());
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_autenticacion, menu);
		return true;
	}

	public void activarCamara(View vista){
		EditText txtUsuario = (EditText) findViewById(R.id.usuario);
		String usuario = txtUsuario.getText().toString();
		EditText txtPass = (EditText) findViewById(R.id.pass);
		String pass = txtPass.getText().toString();
		Intent intent = new Intent(this, CamaraActivity.class);
		if (usuario.isEmpty()){
			Toast.makeText(this, "Campo usuario en blanco",Toast.LENGTH_LONG).show();
		}else if (pass.isEmpty()){
			Toast.makeText(this, "password en blanco",Toast.LENGTH_LONG).show();
		}else{
			this.user =  usuario;
			this.pass = pass;
			Toast.makeText(this, "Autenticando: Señor "+usuario+" un momento por favor",Toast.LENGTH_LONG).show();
			networkThread.start();
			while(networkThread.isAlive()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!error){
				if (!credenciales){
					Toast.makeText(this, "credenciales no validas" ,Toast.LENGTH_LONG).show();
					Intent intentFalla = new Intent(this, MainActivity.class);
					startActivity(intentFalla);
					finish();
				}else{
					startActivity(intent);
					finish();	
				}
			}else{
				Toast.makeText(this, "error de conexión ",Toast.LENGTH_LONG).show();
				Intent intentFalla = new Intent(this, MainActivity.class);
				startActivity(intentFalla);
				finish();
			}
		}
	}

	//Mï¿½todo que detecta si la camara estï¿½ disponible
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)  {
			//PopUp desea Salir de la aplicación?
			//Salir de la aplicación
		}
		return super.onKeyUp(keyCode, event); 
	}
	//Conexion con SOAP
	Thread networkThread = new Thread() {
		@Override
		public void run() {
			try {
				SoapConection scAutentic = new SoapConection(user, pass);
				String autenticado = scAutentic.autenticar();

				//Verifico exito
				if (autenticado.compareTo("exito")==0){
					credenciales = true;
				}else{
					credenciales = false;
					user = "";
				}
			}
			catch (Exception e) {
				//Aviso al activity que hubo un error de conexion
				error = true;
				//Para efectos de depuración de código
				Log.d(tag, "ERROR DE CONECCION SOAP"+" "+e.getMessage() + " " + e.getLocalizedMessage());
			}
		}
	};
}
