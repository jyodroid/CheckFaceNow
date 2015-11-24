package com.checkfacenow.servicelogic;

import java.sql.SQLException;

import com.checkfacenow.model.ParametrosFaciales;
import com.checkfacenow.model.Usuario;

public class Identificador {
	public Usuario IdentificarPorRegistro() throws ClassNotFoundException, SQLException{
		FaceRecognition fr = new FaceRecognition();
		Usuario us = new Usuario();
		DatabaseManager dbm = new DatabaseManager();
		ParametrosFaciales pf = new ParametrosFaciales();
		
	    fr.learn("C:\\Users\\John\\Desktop\\data\\faces.txt");
	    int id = Integer.valueOf(fr.recognizeFileList("C:\\Users\\John\\Desktop\\data\\foto.txt"));
	    pf = dbm.buscarRegistro(id);
	    us = dbm.buscarDatosUsuario(pf.getCedula());
		
		return us;
	}
}
