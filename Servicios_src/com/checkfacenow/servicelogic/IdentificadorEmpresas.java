/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkfacenow.servicelogic;

import com.checkfacenow.model.Cita;
import com.checkfacenow.model.Empresa;
import java.sql.SQLException;

/**
 *
 * @author John
 */
public class IdentificadorEmpresas {
 
    public Empresa datosEmpresa(Integer nit) throws ClassNotFoundException, SQLException{
        DatabaseManager dbm = new DatabaseManager();
        return dbm.buscarEmpresa(nit);
    }
}
