<?php
	$conn = mysqli_connect('localhost', 'spatel3540', 'Serendipity.2', 'COMP3540W16_spatel')  // Connect to the your MySQL database
?>

<!DOCTYPE html>

<html>
<head>
</head>

<body>
<?php
    echo '<h3>User Authentication with Hashed Password</h3><br><br>';
    
	/*
    * Read the user input and display them.
	*/
    
    if (empty($_POST['username']) || empty($_POST['password'])) {
        echo 'Empty username or password.<br>';
        exit;
    }
    
 	$username = $_POST['username']];
	$password = $_POST['password'];
	
    echo 'Username: ' . $username . '<br>';
	echo 'Password: ' . $password . '<br>';
	echo '<br>';
	
	/*
    * Check if the user exists using the username and the hashed password.
    */
    
    $hashed_password = sha1($password);  // Obtain the hashed password using sha1() that uses the SHA1 hash function.
    $result = mysqli_query($conn, "SELECT username, password from user where username = '$uername' and password = '$password';");  // Select with the username and the hashed password from the table.
    
    /*
    * If the user does not exist, then insert the username and the hashed password into the table.
    */
    
    if (mysqli_num_rows($result)==0) {
        echo $username . ' does not exist.<br>';
        // Just in case that the user exist with a different password.
        $result = mysqli_query($conn, "delete from user where username ='$username'");
        // insert the username and the hashed password into the table
        mysqli_query($conn, "insert into user (username, password) values ('$username', '$hashed_password')");
        echo $username . ' is inserted.<br>';
    }
    
    /*
    * If the user exist, just display
    */
    
    else {
        echo "The user exist.<br>";
    }
?>
</body>
</html>
