/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkfacenow.ws;

import com.checkfacenow.model.Cita;
import com.checkfacenow.servicelogic.Autenticador;
import com.checkfacenow.servicelogic.UploadImage;
import com.checkfacenow.servicelogic.Wrapper;
import java.awt.Image;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author John
 */
@WebService(serviceName = "DataCaller")
public class DataCaller {

    @WebMethod(operationName = "uploadimagen")
    public boolean uploadimagen(@WebParam(name = "foto") Image foto,
    @WebParam(name = "id")String id) {
        
        UploadImage ui = new UploadImage();
        return ui.subirImagen(id, foto);
    }
    
    @WebMethod(operationName = "uploadimagenMovil")
    public String uploadimagenMovil(@WebParam(name = "foto") Image foto) {
        
        UploadImage ui = new UploadImage();
        boolean op = ui.subirImagen("identificado", foto);
        String respuesta = "falla";
        if (op){
            respuesta = "exito";
        }
        return respuesta;
    }
    
    @WebMethod(operationName = "callData")
    public Cita callData() {
        return Wrapper.myCita();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "autenticar")
    public String autenticar(@WebParam(name = "name") String name, @WebParam(name = "pass") String pass) {
        Autenticador aut = new Autenticador();
        return aut.autenticar(name, pass);
    }
}
