<?php
if (empty($_POST['page'])) {  // When no page is sent from the client; The initial display
                                // You may use if (!isset($_POST['page'])) instead of empty(...).
    $display_type = 'no-signin';  // This variable will be used in 'view_startpage.php'.
                              // It will display the start page without any box, i.e., no SignIn box, no Join box, ...
    include ('w5_view_startpage.php');  // Start page with no modal window
    exit();
}

require('w5_model.php');  // This file includes some routines to use DB.

// When commands come from StartPage
if ($_POST['page'] == 'StartPage')
{
    $command = $_POST['command'];
    switch($command) {  // When a command is sent from the client
        case 'SignIn':  // With username and password
            $username = $_POST['username'];
            $password = $_POST['password'];
            if (!is_valid($username,$password)) {  // If the user is not valid, redisplay 'SignIn' modal window.
                $error_msg_username = '* Wrong username, or';
                $error_msg_password = '* Wrong password'; // Set an error message into a variable.
                                                        // This variable will used in the form in 'view_startpage.php'.
                $display_type = 'signin';  // It will display the start page with the SignIn box.
                                           // This variable will be used in 'view_startpage.php'.
                include ('w5_view_startpage.php');
            } else  // If the user is valid, display main page.
                include ('w5_view_mainpage.php');
            exit();

        case 'Join':  // With username, password, email, some other information
            $username = $_POST['username'];
            $password = $_POST['password'];
            $email = $_POST['email'];
            if (is_valid($username,$password)) {  // If the user exists, redisplay 'Join' modal window.
                $error_msg_username = '* The user exists.';
                $display_type = 'join';  // It will display the start page with the Join box.
                                         // This variable will be used in 'view_startpage.php'.
                include ('w5_view_startpage.php');
            } else {  // If the user does not exist
                if (insert_new_user($username,$password,$email)) {  // Insert the new user into DB. If there is no error, display 'SignIn' modal window with no error message.
                    $error_msg_username = '';  // empty error message
                    $display_type = 'signin';
					include ('w5_view_startpage.php');
                } else {  // If there is any error, redisplay 'Join' modal window.
                    $error_msg_username = '* Insertion error';
                    $display_type = 'join';
					include ('w5_view_startpage.php');
                }
            }
            exit();
        //...
    }
}

// When commands come from 'MainPage'
else {
    //...
}
?>   