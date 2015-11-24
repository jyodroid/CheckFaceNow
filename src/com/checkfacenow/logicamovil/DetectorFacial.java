package com.checkfacenow.logicamovil;

import android.annotation.SuppressLint;
import android.hardware.Camera; 
import android.hardware.Camera.Face;

public class DetectorFacial implements Camera.FaceDetectionListener{

	private static Face[] faces;
	
	public DetectorFacial(){
		faces = null;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void onFaceDetection(Face[] faces, Camera camera) {
		if (faces.length > 0){
           this.setFaces(faces);
        }
	}

	public static Face[] getFaces() {
		return faces;
	}
	public void setFaces(Face[] faces) {
		DetectorFacial.faces = faces;
	}
}