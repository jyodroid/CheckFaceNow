-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 25-05-2013 a las 19:58:44
-- Versión del servidor: 5.5.20
-- Versión de PHP: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `checkfacenow`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE IF NOT EXISTS `cita` (
  `Num_Id_Cita` bigint(20) NOT NULL,
  `Num_Cedula_Persona_Prestadora` bigint(20) NOT NULL,
  `Dat_hora_Programacion` datetime NOT NULL,
  `Dat_fecha_programacion` datetime NOT NULL,
  `Str_estado_cita` varchar(50) NOT NULL,
  `Str_Descripcio_Servicio` varchar(50) NOT NULL,
  `Num_nivel_acceso_requerido` bigint(20) NOT NULL,
  `Num_tiempo_estimado_servicio` bigint(20) NOT NULL,
  `Dat_hora_inicio_servicio` datetime NOT NULL,
  `Dat_hora_finalizacion_servicio` datetime NOT NULL,
  PRIMARY KEY (`Num_Id_Cita`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `Num_nit` bigint(20) NOT NULL,
  `Str_nombre` varchar(50) NOT NULL,
  `Str_direccion` varchar(50) NOT NULL,
  `Num_telefono` bigint(20) NOT NULL,
  `Str_Tipo_Empresa` varchar(50) NOT NULL,
  PRIMARY KEY (`Num_nit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa_prestadora_servicio`
--

CREATE TABLE IF NOT EXISTS `empresa_prestadora_servicio` (
  `Num_nit` bigint(20) NOT NULL,
  `Str_estado_empresa` char(1) NOT NULL,
  `Str_servicios_prestados` bigint(20) NOT NULL,
  `Str_contrasena` varchar(20) NOT NULL,
  PRIMARY KEY (`Num_nit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa_receptora`
--

CREATE TABLE IF NOT EXISTS `empresa_receptora` (
  `Num_nit` int(20) NOT NULL,
  `Str_contrasena` varchar(20) NOT NULL,
  PRIMARY KEY (`Num_nit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametrosfaciales`
--

CREATE TABLE IF NOT EXISTS `parametrosfaciales` (
  `Num_cedula_usuario` bigint(20) NOT NULL,
  `Str_ruta_registros` varchar(50) NOT NULL,
  `Str_id` varchar(50) NOT NULL,
  PRIMARY KEY (`Num_cedula_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `Num_cedula` bigint(20) NOT NULL,
  `Str_nombres` varchar(50) NOT NULL,
  `Str_apellidos` varchar(50) NOT NULL,
  `Str_cargo` varchar(50) NOT NULL,
  `Str_tipo_usuario` varchar(50) NOT NULL,
  `Num_nit_empresa` bigint(20) NOT NULL,
  PRIMARY KEY (`Num_cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_identificador`
--

CREATE TABLE IF NOT EXISTS `usuario_identificador` (
  `Str_id_interna` varchar(50) NOT NULL,
  `Num_cedula` bigint(20) NOT NULL,
  `Str_seccion_a_cargo` bigint(20) NOT NULL,
  `Str_contrasena` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Str_id_interna`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
