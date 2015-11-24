function validarlogin(){

		/*jQuery.validator.addMethod("fecha", function(value, element) { 
		  return this.optional(element) || /^\d\d?\/\d\d?\/\d\d\d\d/.test(value);
		}, "Ingrese el formato indicado");*/

		$(function(){
       $('#frmlogin').validate({
           rules: {    
		   'nit':'required',
		   'contrasena':'required',
             },
       messages: {
       		'nit':'<br>Debe ingresar nit de empresa',
           'contrasena':'<br>Debe indicar su contraseña',		   

         		   },
       debug: true,
       /*errorElement: 'div',*/
       //errorContainer: $('#errores'),
       submitHandler: function(form){
           document.forms["frmlogin"].submit();
       }
    });
});

}

function validarregistrarusuario(){
      $(function(){
       $('#frmregusuario').validate({
           rules: {    
       'nombres':'required',
       'apellidos':'required',
       'cedula':'required',
       'cargo':'required',
       'tipo':'required',
       'nit':'required',
       'file':'required',
             },
       messages: {
          'nombres':'<br>Debe ingresar nombre',
          'apellidos': '<br>Debe ingresar apellidos',
          'cedula': '<br>Debe ingresar apellidos',
          'cargo': '<br>Debe ingresar e cargo' ,    
          'tipo': '<br>Debe ingresar tipo',
          'nit': '<br>Debe ingresar el nit',
          'file':'<br>Debe seleccionar un archivo',
               },
       debug: true,
       /*errorElement: 'div',*/
       //errorContainer: $('#errores'),
       submitHandler: function(form){
           alert('Espere la confirmacion de acceso');
           document.forms["frmregusuario"].submit();
       }
    });
});
}

function validarregistrarempresa(){
      $(function(){
       $('#frmregempresa').validate({
           rules: {    
       'razonsocial':'required',
             },
       messages: {
          'razonsocial':'<br>Debe ingresar la razon social',      

               },
       debug: true,
       /*errorElement: 'div',*/
       //errorContainer: $('#errores'),
       submitHandler: function(form){
           alert('Espere la confirmacion de acceso');
           document.forms["frmregempresa"].submit();
       }
    });
});
}

function validarrgeistrarcita(){
      $(function(){
       $('#frmregcita').validate({
           rules: {    
       'cedula':'required',
	   'horaprogramacion':'required',
	   'fechaprogramacion':'required',
	   'estadocita':'required',
	   'descripcionservicio':'required',
	   'nivelacceso':'required',
	   'tiempoestimado':'required',
	   'horainicio':'required',
	   'horafinalizacion':'required',
             },
       messages: {
           'cedula':'<br>Debe ingresar cedula',
		   'horaprogramacion':'<br>Debe ingresar hora de programacion',
		   'fechaprogramacion':'<br>Debe ingresar fecha de programacion',
		   'estadocita':'<br>Debe ingresar estado cita',
		   'descripcionservicio':'<br>Debe ingresar la descripcion de servicio',
		   'nivelacceso':'<br>Debe ingresar nivel de acceso',
		   'tiempoestimado':'<br>Debe ingresar tiempo estimado',
		   'horainicio':'<br>Debe ingresar hora de inicio',
		   'horafinalizacion':'<br>Debe ingresar de finalizacion'

               },
       debug: true,
       /*errorElement: 'div',*/
       //errorContainer: $('#errores'),
       submitHandler: function(form){
           alert('Espere la confirmacion de acceso');
           document.forms["frmregcita"].submit();
       }
    });
});
}

function validarregusuaidentificador(){
	      $(function(){
       $('#frmregusuaidentificador').validate({
           rules: {    
       'identificador':'required',
             },
       messages: {
          'identificador':'<br>Debe ingresar el identificador',      

               },
       debug: true,
       /*errorElement: 'div',*/
       //errorContainer: $('#errores'),
       submitHandler: function(form){
           alert('Espere la confirmacion de acceso');
           document.forms["frmregempresa"].submit();
       }
    });
});
}

