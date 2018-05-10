<?php
if (empty($_POST['page'])) {  // When no page is sent from the client; The initial display
                                // You may use if (!isset($_POST['page'])) instead of empty(...).
    $display_type = 'no-signin';  // This variable will be used in 'view_startpage.php'.
                              // It will display the start page without any box, i.e., no SignIn box, no Join box, ...
    include ('w9_view_startpage.php');
    exit();
}

require('w9_model.php');  // This file includes some routines to use DB.

// When commands come from StartPage
if ($_POST['page'] == 'StartPage')
{
    $command = $_POST['command'];
    switch($command) {  // When a command is sent from the client
        case 'SignIn':  // With username and password
//            if (there is an error in username and password) {
            if (!is_valid($_POST['username'], $_POST['password'])) {
                $error_msg_username = '* Wrong username, or';
                $error_msg_password = '* Wrong password'; // Set an error message into a variable.
                                                        // This variable will used in the form in 'view_startpage.php'.
                $display_type = 'signin';  // It will display the start page with the SignIn box.
                                           // This variable will be used in 'view_startpage.php'.
                include('w9_view_startpage.php');
            } 
            else {
                // Set a cookie for the welcome message in StartPage
                setcookie('username',$_POST['username'], time() + 3600);
                
                // Set a session variable for the username
                session_start();
                $_SESSION['signedin'] = 'YES';
                $_SESSION['username'] = $_POST['username'];
                
                include ('w9_view_mainpage.php');
            }
            exit();

        case 'Join':  // With username, password, email, some other information
            if (does_exist($_POST['username'])) {
                $error_msg_username = '* The user exists.';
                $display_type = 'join';
                include('w9_view_startpage.php');
            } else {
                if (insert_new_user($_POST['username'], $_POST['password'], $_POST['email'])) {
                    $display_type = 'signin';
                    include('w9_view_startpage.php');
                } else {
                    $error_msg_username = '* Insertion error';
                    $display_type = 'join';
                    include('w9_view_startpage.php');
                }
            }
            exit();
        //...
    }
}

// When commands come from 'MainPage'
else if ($_POST['page'] == 'MainPage') 
{
    // start a session
    session_start();
    
    // check if this session is broken using $_SESSION['signedin']
    if (!isset($_SESSION['signedin']) || $_SESSION['signedin'] != 'YES') {
        echo 'Session is broken<br>';
        exit();
    }
    
    // recover the username stored in the session
    $username = $_SESSION['username'];
    
    // support commands
    
    $command = $_POST['command'];
    
    switch($command) {
        case 'SignOut':
            // destroy session variables and the session
            session_unset();
            session_destroy();
            // go to 'StartPage'
            $display_type = 'no-signin';
            include('w9_view_startpage.php');
            break;
            
        case 'PostQuestion':
            $r = post_question($_POST['question'], $username);  // in model.php
            if ($r)
                $str = $_POST['question'] . ' posted';
            else
                $str = $_POST['question'] . ' not posted';
            echo $str;
            break;
            
        case 'ListQuestions':
            $r = list_questions($username);  // in model.php
                                             // the return value is an array of associative arrays
            $str = json_encode($r);
            echo $str;
            break;
            
        default:
            echo 'Unknown command - ' . $command . '<br>';
    }
}

else {
    //...
}
?>   
