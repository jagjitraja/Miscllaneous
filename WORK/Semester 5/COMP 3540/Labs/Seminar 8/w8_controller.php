<?php

if (empty($_POST['page'])) {  // When no page is sent from the client; The initial display
                                // You may use if (!isset($_POST['page'])) instead of empty(...).
    $display_type = 'no-signin';  // This variable will be used in 'view_startpage.php'.
                              // It will display the start page without any box, i.e., no SignIn box, no Join box, ...
    include ('./w8_view_startpage.php');
    exit();
}

require('./w8_model.php');  // This file includes some routines to use DB.


// When commands come from StartPage
if ($_POST['page'] == 'StartPage')
{
    $command = $_POST['command'];
    switch($command) {  // When a command is sent from the client
        case 'SignIn':  // With username and password
//          if (there is an error in username and password) {
            if (!is_valid($_POST['username'], $_POST['password'])) {
                $error_msg_username = '* Wrong username, or';
                $error_msg_password = '* Wrong password'; // Set an error message into a variable.
                                                        // This variable will used in the form in 'view_startpage.php'.
                $display_type = 'signin';  // It will display the start page with the SignIn box.
                                           // This variable will be used in 'view_startpage.php'.
                include('./w8_view_startpage.php');
            } 
            else {
                $username = $_POST['username'];
                include ('./w8_view_mainpage.php');
            }
            exit();

        case 'Join':  // With username, password, email, some other information
            if (does_exist($_POST['username'])) {
                $error_msg_username = '* The user exists.';
                $display_type = 'join';
                include('./w8_view_startpage.php');
            } else {
                if (insert_new_user($_POST['username'], $_POST['password'], $_POST['email'])) {
                    $display_type = 'signin';
                    include('./w8_view_startpage.php');
                } else {
                    $error_msg_username = '* Insertion error';
                    $display_type = 'join';
                    include('./w8_view_startpage.php');
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
            include('./w8_view_startpage.php');
            break;
        
        // posting a question
        case 'PostQuestion':
			//TODO _ FIGURE OUT USERNAME
            $r = post_question($_POST['question'], 'abc');  // in model.php
                                                 // args: question, username
            if ($r)
                $str = $_POST['question'] . ' posted';
            else
                $str = $_POST['question'] . ' not posted';
            echo $str;
            break;
        
        // list questions
        case 'ListQuestion':
            $r = list_questions();  // in model.php
                                    // the return value is an array of associative arrays
            $str = json_encode($r); // convert it to a string
            echo $str;
            break;
            
        default:
            echo 'Unknown command - ' . $command . '<br>';
    }
}

else {
}
?>   
