package com.checkfacenow.logicamovil;

//A basic Camera preview class
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PrevisualiceCamara extends SurfaceView implements SurfaceHolder.Callback{
	
	private static final String tagR = "com.trunk.checkfacenow.PrevisualiceCamara.ROSTROS";
	private static final String tagE = "com.trunk.checkfacenow.PrevisualiceCamara.ERRORES";
	private SurfaceHolder holder;
	private Camera camara;
	
	public PrevisualiceCamara(Context contexto, Camera camara) {
		super(contexto);
		this.camara = camara;
		holder = getHolder();
		holder.addCallback(this); 
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
		int height) {
		if (this.holder.getSurface()==null){
			Log.d(tagE, "holder.getSurface() == null");
			return;
		}
		
		try {
			this.camara.stopPreview();
		} catch (Exception e) {
			Log.d(tagE, "Error stopping camera preview: " + e.getMessage());
		}
		
		try {
			this.camara.setPreviewDisplay(this.holder);
			this.camara.startPreview();
		} catch (Exception e) {
			Log.d(tagE, "Error setting camera preview: "+ e.getMessage());
		}
		
		//Detectar rostro y dibujar un circulo en el centro
		double x;
		double y;
		try {
			x = DetectorFacial.getFaces()[0].rect.exactCenterX();
			y = DetectorFacial.getFaces()[0].rect.exactCenterY();
		} catch (Exception e) {
			x = 0;
			y = 0;
		}
		DibujeEncima.setCentroRostro(x,y);
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
        try {
            this.camara.setPreviewDisplay(holder);
            this.camara.startPreview();
            startFaceDetection(); // start face detection feature
        } catch (IOException e) {
            Log.d(tagE, "Error setting camera preview: " + e.getMessage());
        }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	@SuppressLint("NewApi")
	public void startFaceDetection(){
	    // Try starting Face Detection
	    Camera.Parameters params = camara.getParameters();

	    // start face detection only *after* preview has started
	    if (params.getMaxNumDetectedFaces() > 0){
	        // camera supports face detection, so can start it:
	    	Log.d(tagR, "rostro detectado");
	    	camara.startFaceDetection();
	    }
	}

}
