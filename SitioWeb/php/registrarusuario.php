<?php
require_once('lib/nusoap.php');
//session_start();
                        
                // $nombres  = $_POST["nombres"];
                // $apellidos  = $_POST["apellidos"];
                //$cedula = $_POST["cedula"];
                // $cargo  = $_POST["cargo"];
                // $nit  = $_POST["nit"];
                // $tipo  = $_POST["tipo"];

                // $tmp_name=$_FILES["file"]["tmp_name"];
                // $name=$_FILES["file"]["name"];
                // $dir='uploads/'.$name;

                // if(move_uploaded_file($tmp_name,$dir))echo "subido";

               /* include 'config.php';
                $sql = "select count(Num_cedula) from Usuario where Num_cedula = '$cedula'";
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
                    $sql = "INSERT INTO Usuario (Num_cedula, Str_nombres, Str_apellidos, Str_cargo, Str_tipo_usuario, Num_nit_empresa) VALUES ('" . $cedula . "',     '" . $nombres . "', '" . $apellidos . "', '" . $cargo . "', '" . $tipo . "', '" . $nit . "');";
                    mysql_query($sql);                  
                    //header('location: registrationsuccess.php');
                    echo "registrado";
                    
                }
*/
                //uso webservice
                echo "aqui<br>";
                echo filesize("/var/www/html/sitio/php/".$dir);
                $dir = "/var/www/html/sitio/php/".$dir;
                $dir="/home/dubancano/Pictures/panda-wave.png";
                echo $dir;

                $handle = fopen($dir, "r");                  // Open the temp file
                $contents = fread($handle, filesize($dir));  // Read the temp file
                fclose($handle);                                 // Close the temp file

                $decodeContent   = base64_encode($contents);     

                $client = new SoapClient('http://fedora:8080/serviceuploadimg/uploadimg?wsdl',true);
                //$client = new SoapClient('http://10.0.45.132:8084/CheckFaceNowServer/DataCaller?wsdl',true);
                $result = $client->call("uploadimagen",array('foto'=>$decodeContent,'id'=>$cedula));

                $palabra = (bool) $result['return'];
                if($palabra)echo "bn";
                else echo "mal";
                
                //mysql_close($connection);
?>
