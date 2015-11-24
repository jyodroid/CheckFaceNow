CREATE TABLE Imagenes
 (
  ID int(20) NOT NULL AUTO_INCREMENT,
  Num_Cedula bigint(20) NOT NULL,
  Nombre_Imagen varchar(50) NOT NULL,
  FS_Location varchar(50) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;
