package com.checkfacenow.vistamovil;

import com.checkfacenow.logicamovil.DetectorFacial;
import com.checkfacenow.logicamovil.DibujeEncima;
import com.checkfacenow.logicamovil.PrevisualiceCamara;
import com.trunk.checkfacenow.R;
import com.trunk.checkfacenow.R.id;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.AudioManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CamaraActivity extends Activity {

	//Constantes que sirven de identificador para la hashlist Extra en donde se le pasan las actividades al siguiente parámetro

	public static final String tag = "com.trunk.checkfacenow.camaraactivity.ERRORES";
	private Camera camara;
	private PrevisualiceCamara vPrevia;
	private byte[] dataPicture;

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			AudioManager mgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			mgr.playSoundEffect(AudioManager.FLAG_PLAY_SOUND);
		}
	};

	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] _data, Camera _camera) {
			setDataPicture(_data);
			mostrarRasgos();
		}
	};

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_camara);

		Button clickButton = (Button) findViewById(id.boton_identifique);
		clickButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (revisarRostro()){
					camara.takePicture(shutterCallback, null, jpegCallback);
				}
			}
		});

		camara = getCameraInstance();
		DetectorFacial faceDetect = new DetectorFacial();
		camara.setFaceDetectionListener(faceDetect);
		camara.setDisplayOrientation(90);

		// Create our Preview view and set it as the content of our activity.
		vPrevia = new PrevisualiceCamara(this, camara);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camara_vistaprevia);
		preview.addView(vPrevia);

		//Dibujar guias encima
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int screenCenterX = (size.x /2);
		int screenCenterY = (size.y/2) ;
		DibujeEncima encima = new DibujeEncima(this,screenCenterX,screenCenterY);
		addContentView(encima, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance(){
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance

		}
		catch (Exception e){
			System.out.println(e.getMessage());
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	public boolean revisarRostro(){
		//Revisa que almenos haya un rostro
		if (DetectorFacial.getFaces() == null){
			Toast.makeText(this, "No se detectó ningún rostro",
					Toast.LENGTH_LONG).show();
			return false;
		}else{
			return true;
		}
	}
	public void mostrarRasgos(){
		/**
		 *Intent es un objeto que sirve para unir dos actividades separadas.
		 *argumentos: contexto (Activity es una subclase de contexto.)
		 *la clase que iniciarďż˝ la actividad
		 *Un intent tambiďż˝n lleva un paquete de datos (EXTRAS) a la actividad
		 *It's generally a good practice to define keys for intent extras using
		 *your app's package name as a prefix. This ensures they are unique, in
		 * case your app interacts with other apps.
		 ***/
		Intent intent = new Intent(this, CargadorActivity.class);

		//Envío la fotografía la la actividad detalle
		intent.putExtra("foto", getDataPicture());

		startActivity(intent);
		
		//Libero la camara
		liberarCamara();
		
		finish();

		/**Para comenzar una nueva actividad, se llama el método startActivity()
		 *se le pasa como Parametro el intent creado.
		 **/
	}

	private void liberarCamara(){
		if (this.camara != null){
			this.camara.stopPreview();
			this.camara.release();
			this.camara = null;
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)  {
			Intent intentViejo = getIntent();
			String sesion = intentViejo.getStringExtra("sesion");
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("sesion", sesion);
			liberarCamara();
			startActivity(intent);
		}
		return super.onKeyUp(keyCode, event); 
	}

	public byte[] getDataPicture() {
		return dataPicture;
	}

	public void setDataPicture(byte[] dataPicture) {
		this.dataPicture = dataPicture;
	}
}