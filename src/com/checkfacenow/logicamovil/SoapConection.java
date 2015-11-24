package com.checkfacenow.logicamovil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.checkfacenow.model.Cita;
import com.checkfacenow.model.Empresa;
import com.checkfacenow.model.Usuario;
/**
 * 
 * @author John
 *
 */
public class SoapConection{

	private final String NAMESPACE="http://ws.checkfacenow.com/";//Nombre del paquete donde está el servicio
	private final String URL="http://192.168.0.107:8084/CheckFaceNowServer/DataCaller?wsdl";//Dirección del xml
	private String METHOD_NAME;//Nombre de método del servicio
	private String SOAP_ACTION;//Nombre del paquete y accion a realizar
	private String name;
	private String pass;

	public SoapConection(String methodName){
		METHOD_NAME = methodName;
		SOAP_ACTION = NAMESPACE+METHOD_NAME;
	}

	public SoapConection(String name, String pass){
		METHOD_NAME = "autenticar";
		SOAP_ACTION = NAMESPACE+METHOD_NAME;
		this.name = name;
		this.pass = pass;
	}

	private Cita cita = new Cita();

	//Metodo para autenticar al usuario identificador
	public String autenticar() throws Exception{
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("name", name);
		request.addProperty("pass", pass);
		SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		sobre.setOutputSoapObject(request);
		HttpTransportSE transporte = new HttpTransportSE(URL);
		transporte.call(SOAP_ACTION, sobre);
		//Traer y manjar la respuesta
		SoapPrimitive respuesta = (SoapPrimitive) sobre.getResponse();
		return respuesta.toString();
	}

	//Método para ejecutar el reconocimiento facial en el servidor
	public void callIdentificador() throws Exception {

		//Se crea una peticion al método
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		//Se crea un contenedor SOAP "envelope" o sobre con la version XML utilizada
		SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		//Configurar los parametros de request
		sobre.setOutputSoapObject(request);

		//Comunicación http
		HttpTransportSE transporte = new HttpTransportSE(URL);

		//Traer y manjar la respuesta		
		transporte.call(SOAP_ACTION, sobre);
		//
		//Para traer objetos con el formato json

		/*//Puede servir para colecciones como array list
		Vector respuesta =  (Vector) sobre.getResponse();
        if (respuesta != null) {
            String resultado = respuesta.get(0).toString();
            System.out.println(resultado);
        } else {
        	System.out.println("Respuesta en blanco");
        }*/

		SoapObject respuesta = (SoapObject) sobre.getResponse();

		/**Atributos de la clase cita:
		 * 
		 * 	private int accesoRequerido;
			private String estadoCita;
			private String fechaServicio;
			private Usuario personaPrestadora;//Usuario con cÃ©dula en la tabla cita
			private double tiempoEstimado;
			private String tipoSercicio;
			private String horaInicioServicio;
			private String horaCita;
			private String horafinalizacionServicio;	
		 */
		getCita().setEstadoCita(respuesta.getProperty("estadoCita").toString());
		Usuario usuario = new Usuario();
		Empresa empresa = new Empresa();
		
		if(respuesta.getProperty("estadoCita").toString().compareTo("Inexistente")!=0){

			getCita().setAccesoRequerido(Integer.valueOf(respuesta.getProperty("accesoRequerido").toString()));
			getCita().setEstadoCita(respuesta.getProperty("estadoCita").toString());
			getCita().setFechaServicio(respuesta.getProperty("fechaServicio").toString());
			getCita().setHoraCita(respuesta.getProperty("horaCita").toString());
			getCita().setTiempoEstimado(Double.valueOf(respuesta.getProperty("tiempoEstimado").toString()));

			//El usuario que tiene la cita
			SoapObject usuarioRespuesta = (SoapObject) respuesta.getProperty("personaPrestadora");
			usuario.setApellidos(usuarioRespuesta.getProperty("apellidos").toString());
			usuario.setNombres(usuarioRespuesta.getProperty("nombres").toString());
			usuario.setCedula(Integer.valueOf(usuarioRespuesta.getProperty("cedula").toString()));
			usuario.setCargo(usuarioRespuesta.getProperty("cargo").toString());
			
			//Empresa de donde viene el usuario
			SoapObject empresaUsuario = (SoapObject) usuarioRespuesta.getProperty("empresa");
			empresa.setNit(Integer.valueOf(empresaUsuario.getProperty("nit").toString()));
			empresa.setDireccion(empresaUsuario.getProperty("direccion").toString());
			empresa.setTelefono(empresaUsuario.getProperty("telefono").toString());
			empresa.setNombre(empresaUsuario.getProperty("nombre").toString());
		}
		usuario.setEmpresa(empresa);
		cita.setPersonaPrestadora(usuario);
	}

	//Método para subir la imágen de la persona que va a ser reconocida
	public String enviaImagen(byte[] foto) throws Exception{
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("foto", foto);
		SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		new MarshalBase64().register(sobre); // serialization
		sobre.setOutputSoapObject(request);
		HttpTransportSE transporte = new HttpTransportSE(URL);
		transporte.call(SOAP_ACTION, sobre);
		//Traer y manjar la respuesta
		SoapPrimitive respuesta = (SoapPrimitive) sobre.getResponse();
		return respuesta.toString();
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}
}
