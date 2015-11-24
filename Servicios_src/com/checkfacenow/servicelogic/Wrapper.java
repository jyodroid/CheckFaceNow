package com.checkfacenow.servicelogic;

import com.checkfacenow.model.Cita;
import com.checkfacenow.model.Empresa;
import com.checkfacenow.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Wrapper {
	public static Cita myCita(){
		Identificador id = new Identificador();
                VerificadorDeCitas vc = new VerificadorDeCitas();
                IdentificadorEmpresas vem = new IdentificadorEmpresas();
		Cita cita = new Cita();
                Usuario usuario = new Usuario();
                Empresa empresa = new Empresa();
		try {
                    //Llenar los datos del usuario que se identificó en el atributo usuario de la cita
                    usuario = id.IdentificarPorRegistro();

                    //Obtener datos de la cita de este dia
                    Calendar c = Calendar.getInstance(); 
                    int year = c.get(Calendar.YEAR);
                    int mes = c.get(Calendar.MONTH)+1;//Enero = 0
                    int dia = c.get(Calendar.DATE);
                    String fecha = String.valueOf(year)+"-"+String.valueOf(mes)+"-"+String.valueOf(dia);
                    cita = vc.verificarCita(fecha, usuario.getCedula().toString());
                    
                    //Obtener los datos de la empresa a la que pertenece el usuario
                    empresa = vem.datosEmpresa(usuario.getEmpresa().getNit());
                    
                    usuario.setEmpresa(empresa);
                    cita.setPersonaPrestadora(usuario);
                        
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return cita; 
	} 
}
