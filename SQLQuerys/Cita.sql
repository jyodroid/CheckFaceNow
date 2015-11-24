CREATE TABLE Cita
(
	Num_Id_Cita BIGINT NOT NULL DEFAULT 1,
	Num_Cedula_Persona_Prestadora BIGINT NOT NULL,
	Dat_hora_Programacion DATETIME NOT NULL,
	Dat_fecha_programacion DATETIME NOT NULL,
	Str_estado_cita VARCHAR(50) NOT NULL,
	Str_Descripcion_Servicio VARCHAR(50) NOT NULL,
	Num_nivel_acceso_requerido BIGINT NOT NULL,
	Num_tiempo_estimado_servicio BIGINT NOT NULL,
	Dat_hora_inicio_servicio DATETIME NOT NULL,
	Dat_hora_finalizacion_servicio DATETIME NOT NULL,
	PRIMARY KEY (Num_Id_Cita)

) 
;


