<?php
session_start();
                        
                $nombre = $_POST["nombre"];
                $nit  = $_POST["nit"];
                $direccion = $_POST["direccion"];
                $telefono  = $_POST["telefono"];
                $tipo  = $_POST["tipo"];
                $estado  = $_POST["estado"];
                $serviciosprestados  = $_POST["serviciosprestados"];
                include 'config.php';

                $sql = "select count(Num_nit) from Empresa where Num_nit = '$nit'";
                $initial_query = mysql_query($sql) or die("SQL error");
                $num_sql = mysql_fetch_array($initial_query);
                $numrows = $num_sql[0];
                mysql_free_result($initial_query);

                
                if ($numrows!= 0)
                {
                    header('location: alreadyexists.php');
                }
                else
                {
                    $sql = "INSERT INTO Empresa (Num_nit, Str_nombre, Str_direccion, Num_telefono, Str_Tipo_Empresa) VALUES ('" . $nit . "',     '" . $nombre . "', '" . $direccion . "', '" . $telefono . "', '" . $tipo . "');";
                    mysql_query($sql);
                    $sql = "INSERT INTO Empresa_prestadora_servicio (Num_nit, Str_estado_empresa, Str_servicios_prestados) VALUES ('" . $nit . "',     '" . $estado . "', '" . $serviciosprestados . "');";
                    mysql_query($sql);                    
                    header('location: registrationsuccess.php');
                    
                }
                //mysql_close($connection);
?>
