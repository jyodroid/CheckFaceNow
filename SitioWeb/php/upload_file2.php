<?php
session_start();
$user=$_SESSION['User'];
?>
<html>
<head>
<?php
$allowedExts = array("jpg", "jpeg", "gif", "png", "JPG","JPEG", "GIF" );
$extension = end(explode(".", $_FILES["file"]["name"]));
if ((($_FILES["file"]["type"] == "image/gif")
|| ($_FILES["file"]["type"] == "image/jpeg")
|| ($_FILES["file"]["type"] == "image/png")
|| ($_FILES["file"]["type"] == "image/jpg"))
&& ($_FILES["file"]["size"] < 200000000)
&& in_array($extension, $allowedExts))
{
    if ($_FILES["file"]["error"] > 0)
    {
        echo "Return Code: " . $_FILES["file"]["error"] . "<br>";
    }
    else
    {
        $shafilename = sha1_file($_FILES["file"]["tmp_name"])."." . $extension;
	$path = "pics/";
	$imagename = $_FILES["file"]["name"];
        echo "Upload: " . $_FILES["file"]["name"] . "<br>";
        echo "Type: " . $_FILES["file"]["type"] . "<br>";
        echo "Size: " . ($_FILES["file"]["size"] / 1024) . " kB<br>";
        echo "Temp file: " . $shafilename . "<br>";

        if (file_exists("pics/" .$shafilename))
        {
            echo $shafilename. " already exists. ";
        }
        else
        {      
                include 'config.php';
                $sql = "select Num_Cedula from Usuario where Num_Cedula  = 666";
                $initial_query = mysql_query($sql) or die("SQL error");
                $num_sql = mysql_fetch_array($initial_query);
                $query = $num_sql[0];
                $sql = "INSERT INTO Imagenes (ID, Num_Cedula, Nombre_Imagen,FS_location)
                VALUES (NULL, '".$query."' , '".$imagename."','".$path.$shafilename."');";
                mysql_query($sql);
                //mysql_close($connection);
                move_uploaded_file($_FILES["file"]["tmp_name"], $path .$shafilename);
                echo "Stored in: " . "pics/" .$shafilename;
        }
    }
}
else
{
    echo "Invalid file";
}
?>
</head>
<body>
    <FORM METHOD="LINK" ACTION="registrousuarios.php">
    <INPUT TYPE="submit" VALUE="OK">
    </FORM>
</body>
</html>