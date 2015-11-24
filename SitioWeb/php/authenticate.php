<?php
//if($_POST){
include 'config.php';
// session_start();
// $_SESSION['timeout']=time();

// $user = $_REQUEST['user'];
// $password = $_REQUEST['password'];
 $nit = $_POST['nit'];
 $contrasena = $_POST['contrasena'];

   // $_SESSION['myRequest']=8;
    
	//empresa receptora
    $sql = "select * from empresa_receptora where Num_nit ='$nit'";
    
    $initial_query = mysql_query($sql) or die("SQL error");
    $num_sql = mysql_fetch_array($initial_query);

    //print_r($num_sql);

    $query = $num_sql['Str_contrasena'];

    if($query==$contrasena)
    {
        header('location: ../html/menureceptora/');
        
    }
    else{

		//empresa prestadora
    	$sql = "select * from empresa_prestadora_servicio where Num_nit ='$nit'";
    
    	$initial_query = mysql_query($sql) or die("SQL error");
		$num_sql = mysql_fetch_array($initial_query);
	
		//print_r($num_sql);
	
		$query = $num_sql['Str_contrasena'];
		
		    if($query==$contrasena)
    		{
        		header('location: ../html/menuprestadora/');
    		}
    		else{
			echo "error password";
			}
    }
// mysql_free_result($initial_query);

// if($query!=$password)
// {
//     header('location: LoginError.php');
// }
// if ($password == NULL)
// {
//     header('location: LoginError.php');
// }       
    
// }
// else{
//     echo "error";
// }
?>
