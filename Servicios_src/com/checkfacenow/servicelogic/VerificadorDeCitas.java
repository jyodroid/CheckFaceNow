/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkfacenow.servicelogic;

import com.checkfacenow.model.Cita;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class VerificadorDeCitas {
    public Cita verificarCita(String fechaActual, String cedula) throws ClassNotFoundException, SQLException{
        DatabaseManager dbm = new DatabaseManager();
        return dbm.buscarCita(fechaActual, cedula);
    }
}
