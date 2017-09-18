<?php 
include_once('dbConfig.php');
if(isset($_POST["Import"]))
{
    //First we need to make a connection with the database
    /*$host='localhost'; // Host Name.
    $db_user= 'root'; //User Name
    $db_password= '';
    $db= 'product_record'; // Database Name.
    $conn=mysql_connect($host,$db_user,$db_password) or die (mysql_error());
    mysql_select_db($db) or die (mysql_error());*/
    $filename=$_FILES["file"]["tmp_name"];
    if($_FILES["file"]["size"] > 0)
    {
        $file = fopen($filename, "r");
        //$sql_data = "SELECT * FROM prod_list_1 ";
        while (($emapData = fgetcsv($file, 10000, ",")) !== FALSE)
        {
            if($emapData[0] == -1){
				
				$emapData = fgetcsv($file, 10000, ",");
				$sql = "UPDATE `bins` SET `full`= '$emapData[0]' WHERE id = '2'";
				if(mysqli_query($con, $sql))echo "Success";
                                else "Fail";
				printf($emapData[0]);
				exit();
				
				
			}
            //exit();
            /*$sql = "INSERT into prod_list_1(p_bench,p_name,p_price,p_reason) values ('$emapData[0]','$emapData[1]','$emapData[2]','$emapData[3]')";
            mysql_query($sql);*/
        }
        fclose($file);
        echo 'CSV File has been successfully Imported';
        //header('Location: index.php');
    }
    else
        echo 'Invalid File:Please Upload CSV File';
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
</head>

<body>
<form enctype="multipart/form-data" method="post" role="form">
    <div class="form-group">
        <label for="exampleInputFile">File Upload</label>
        <input type="file" name="file" id="file" size="150">
        <p class="help-block">Only Excel/CSV File Import.</p>
    </div>
    <button type="submit" class="btn btn-default" name="Import" value="Import">Upload</button>
</form>
</body>
</html>