<?php
    $conn = ???(???, ???, ???, ???);  // not 'cs.tru.ca'
    if (???()) {
        echo "Failed to connect to DB: " . mysqli_connect_error();
        exit;
    }
    
    // the function to validity of usernamd and password
    function ???($username, $password) 
    {
        ??? $conn;  // inorder to access to global variables
        
        // select user information with $username and $password
        $sql = "??? ??? ??? users
                ??? ??? = ??? ??? ??? = ???";  // You've got to remember the values for the two columns are strings.
                
        $result = ???($conn, $sql);
        if (???($result) >= 1)  // if the number of rows is >= 1
            return true;
        else
            return false;
    }
?>