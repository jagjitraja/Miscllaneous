<?php
    $conn = mysqli_connect('localhost', 'spatel3540', 'Serendipity.2', 'COMP3540W16_spatel');  // not 'cs.tru.ca'
    if (mysqli_connect_error()) {
        echo "Failed to connect to DB: " . mysqli_connect_error();
        exit;
    }
    
    // the function to validity of usernamd and password
    function checkval($username, $password) 
    {
        global $conn;  // inorder to access to global variables
        
        // select user information with $username and $password
        $sql = "SELECT * fom  users
                where username='$username' and password ='$password'";  // You've got to remember the values for the two columns are strings.
                
        $result = mysqli_query($conn, $sql);
        if (mysqli_nem_rows($result) >= 1)  // if the number of rows is >= 1
            return true;
        else
            return false;
    }
?>