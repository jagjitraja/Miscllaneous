<?php

// When the controller is accessed without a command
if (empty($_POST['page'])) {  // When no page is sent from the client; The initial display
                              // You may use if (!isset($_POST['page'])) instead of empty(...).
    $display_type = 'no-signin';  // This variable will be used in 'view_startpage.php'.
    include ('./w6_view_startpage.php');
    exit();
}


require('./w6_model.php');  // This file includes some routines to use DB.


// When commands come from StartPage
if ($_POST['page'] == 'StartPage')
{
    $command = $_POST['command'];
    switch($command) {  // When a command is sent from the client
        case 'SignIn':  // With username and password
            $username = $_POST['username'];
            $password = $_POST['password'];
            // if (there is an error in username and password) {
            if (!is_valid($username, $password)) {
                $error_msg_username = '* Wrong username, or';
                $error_msg_password = '* Wrong password'; // Set an error message into a variable.
                                                        // This variable will used in the form in 'view_startpage.php'.
                $display_type = 'signin';  // It will display the start page with the SignIn box.
                                           // This variable will be used in 'view_startpage.php'.
                include('./w6_view_startpage.php');
            } 
            // when the user is valid
            else {
                include ('./w6_view_mainpage.php');  // view_mainpage.php
            }
            exit();

        case 'Join':  // With username, password, email, some other information
            if (does_exist($_POST['username'])) {
                $error_msg_username = '* The username already exists.';
                $error_msg_password = '';
                $display_type = 'join';
                include('./w6_view_startpage.php');
            } else {
                if (insert_new_user($_POST['username'], $_POST['password'], $_POST['email'])) {
                    $error_msg_username = '';
                    $error_msg_password = '';
                    $display_type = 'signin';
                    include('./w6_view_startpage.php');
                } else {
                    $error_msg_username = '* Insertion error';
                    $error_msg_password = '';
                    $display_type = 'join';
                    include('./w6_view_startpage.php');
                }
            }
            exit();
        //...
    }
}
// When commands come from 'MainPage'
else if ($_POST['page'] == 'MainPage') 
{
    // support commands
    
    $command = $_POST['command'];
    
    switch($command) {
        case 'SignOut':
            // go to 'StartPage'
            $display_type = 'no-signin';
            $error_msg_username = '';
            $error_msg_password = '';
            include('./w6_view_startpage.php');
            break;
            
        default:
            echo 'Unknown command - ' . $command . '<br>';
    }
}

else {
    //...
}
?>   
