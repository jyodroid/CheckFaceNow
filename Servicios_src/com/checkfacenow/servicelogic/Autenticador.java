/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.checkfacenow.servicelogic;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class Autenticador {
    public String autenticar(String name, String pass){
            DatabaseManager dbm = new DatabaseManager();
        try {
            return dbm.autenticar(name, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "falla";
    }
}
