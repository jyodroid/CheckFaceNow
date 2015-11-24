CREATE TABLE Usuario
(
	Num_cedula BIGINT NOT NULL,
	Str_nombres VARCHAR(50) NOT NULL,
	Str_apellidos VARCHAR(50) NOT NULL,
	Str_cargo VARCHAR(50) NOT NULL,
	Str_tipo_usuario VARCHAR(50) NOT NULL,
	Num_nit_empresa BIGINT NOT NULL,
	PRIMARY KEY (Num_cedula)

) 
;


ALTER TABLE Usuario ADD CONSTRAINT FK_Usuario_ParametrosFaciales 
	FOREIGN KEY (Num_cedula) REFERENCES ParametrosFaciales (Num_cedula_usuario)
;

