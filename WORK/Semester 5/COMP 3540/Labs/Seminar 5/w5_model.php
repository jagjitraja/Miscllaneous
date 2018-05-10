<?php

$conn = mysqli_connect('localhost', 'jbilkhuw7', 'jbilkhuw7424', 'COMP3540_jbilkhu');  // connect to your DB

/*
*   username value is passed to this function.
*   This function is used to check if the user exists.
*/

function does_exist($username) 
{
    GLOBAL $conn;  // inorderto use a global variable
    
    $sql = "select * from Users where (userName = '$username')";
    $result = mysqli_query($conn, $sql);
    if (mysqli_num_rows($result) > 0)  // check the number of selected rows
        return true;
    else
        return false;
}

/*
*   username, password, email are passed to this function.
*   This function tries to insert of new row with those passed values.
*/

function insert_new_user($username, $password, $email)
{
    GLOBAL $conn;
    
    if (does_exist($username))  // if the user exist
                         // You can use a function defined in the above
        return false;
    else {
        $current_date = date('Ymd');
        $sql = "INSERT into Users VALUES (NULL, '$username','$password','$email',$current_date)";  // An example is in Seminar 5.docx.
        $result = mysqli_query($conn, $sql);
        return $result;
    }
}

/*
*   username and password values are passed to this function.
*   This function is used to check if the user is valid.
*/
 
function is_valid($username, $password) 
{
    GLOBAL $conn;
    
    $sql = "SELECT * FROM Users WHERE userName = '$username' AND password = '$password'";  // select
    $result = mysqli_query($conn, $sql);
    if (mysqli_num_rows($result) > 0)  // check the number of selected rows
        return true;
    else
        return false;
}

?>   