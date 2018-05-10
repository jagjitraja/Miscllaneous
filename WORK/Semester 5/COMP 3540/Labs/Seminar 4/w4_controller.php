<?php
require('w4_model.php');  // This file includes some routines to use DB.
               // E.g., is_valid() to check the validity of username and password.
//...
echo 'aaaa';
// When commands come from StartPage
if ($_POST['page'] == 'StartPage')  // Check the page value
{
    $command = $_POST['command'];
    switch($command) {  // When a command is sent from the client
        case 'SignIn':  // With username and password
//          if (there is an error in username and password) {
            if (!is_valid($_POST['username'], $_POST['password'])) {  // This function is defined in Model.
                include('./w4_view_startpage.php');  // Include the start page
            } else
                include('./w4_view_mainpage.php');  // Include the main page
            exit();
            
        case 'Join':  // With username, password, email, some other information
            //...
            exit();
        //...
    }
}

// When commands come from 'MainPage'
//   Not implemented yet
else {
    //...
}
?>   