CREATE TABLE IF NOT EXISTS `Usuario_Identificador` (
  `Str_id_interna` varchar(50) NOT NULL,
  `Num_cedula` bigint(20) NOT NULL,
  `Num_nivel_autorizacion` bigint(20) NOT NULL,
  `Str_seccion_a_cargo` bigint(20) NOT NULL,
  `Password` varchar(8) NOT NULL,
  PRIMARY KEY (`Str_id_interna`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

