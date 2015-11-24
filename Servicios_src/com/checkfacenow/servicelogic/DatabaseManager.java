package com.checkfacenow.servicelogic;

import com.checkfacenow.model.Cita;
import com.checkfacenow.model.Empresa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.checkfacenow.model.ParametrosFaciales;
import com.checkfacenow.model.Usuario;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseManager {

	public Connection conection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/checkfacenow", "movil", "secure"); 
		return conexion;
	}

	public Statement statement() throws SQLException, ClassNotFoundException {
		Statement st = conection().createStatement();
		return st;
	}

	public Usuario buscarDatosUsuario(Integer cedula) throws ClassNotFoundException, SQLException{
		ResultSet rs = statement().executeQuery("SELECT * FROM usuario where Num_cedula = "+cedula); 
		Usuario usuario = new Usuario(); 
                Empresa empresa = new Empresa();
		usuario.setCedula(Integer.valueOf(cedula));
		while (rs.next()){
			usuario.setNombres((String) rs.getObject("Str_nombres"));
			usuario.setApellidos((String) rs.getObject("Str_apellidos"));
			usuario.setCargo((String) rs.getObject("Str_cargo"));
			usuario.setTipoUsuario((String) rs.getObject("Str_tipo_usuario"));
			empresa.setNit((Integer) rs.getObject("Num_nit_empresa"));
                        usuario.setEmpresa(empresa);
		}
		rs.close();
		return usuario;
	}
	public ParametrosFaciales buscarRegistro(int numeroRegistro) throws ClassNotFoundException, SQLException{

		ResultSet rs = statement().executeQuery("SELECT * FROM ParametrosFaciales where Str_id = "+numeroRegistro); 
		ParametrosFaciales parametro = new ParametrosFaciales(); 

		parametro.setRegistro(numeroRegistro);
		while (rs.next()) {
			parametro.setCedula((Integer) rs.getObject("Num_cedula_usuario"));
		}
		rs.close();

		return parametro;
	}
        
        public String autenticar(String name, String pass) throws ClassNotFoundException, SQLException{
            ResultSet rs = statement().executeQuery(
                    "SELECT * FROM usuario_identificador where "
                    + "Str_id_interna = '"+name+"' and Str_contrasena ='"+pass+"'");
                while(rs.next()){
                    System.out.println(rs.getObject("Str_id_interna"));
                    return "exito";
                }
            return "falla";
        }
        
        public Cita buscarCita(String fechaActual, String cedulaUsuario) throws ClassNotFoundException, SQLException{
            ResultSet rs = statement().executeQuery("SELECT * FROM cita WHERE "+
                    "Num_Cedula_Persona_Prestadora ="+cedulaUsuario+
                    " AND Dat_fecha_programacion =  '"+fechaActual+"'");
            ArrayList<Cita> citas = new ArrayList<Cita>();
            while (rs.next()){
                    Cita cita = new Cita();
                    cita.setEstadoCita((String) rs.getObject("Str_estado_cita"));
                    cita.setAccesoRequerido((Integer) rs.getObject("Num_nivel_acceso_requerido"));
                    cita.setFechaServicio(fechaActual);
                    cita.setHoraCita(rs.getObject("Dat_hora_programacion").toString());
                    cita.setTiempoEstimado(Double.valueOf(rs.getObject("Num_tiempo_estimado_servicio").toString()));
                    cita.setTipoSercicio((String) rs.getObject("Str_descripcio_Servicio"));
                    citas.add(cita);
		}
            if (citas.size()==0){
                Cita cita = new Cita();
                cita.setEstadoCita("Inexistente");
                citas.add(cita);
            }
            return citas.get(0);
        }
        
        public Empresa buscarEmpresa(Integer nit) throws ClassNotFoundException, SQLException{
        
            Empresa empresa = new Empresa();
            Integer stNit = Integer.valueOf(nit);
            empresa.setNit(nit);
            ResultSet rs = statement().executeQuery("SELECT * FROM empresa where Num_nit ="+stNit);
            while (rs.next()){
                empresa.setDireccion((String) rs.getObject("Str_direccion"));
                empresa.setNombre((String) rs.getObject("Str_nombre"));
                empresa.setTelefono(rs.getObject("Num_telefono").toString());
            }
            
            return empresa;
        } 
        public static void main (String[] args){
            DatabaseManager dm = new DatabaseManager();
            try {
                Empresa em =
                        dm.buscarEmpresa(1017131190);
                System.out.println(em.getNombre());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
