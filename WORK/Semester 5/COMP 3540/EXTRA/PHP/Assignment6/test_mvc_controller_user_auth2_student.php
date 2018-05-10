<?php
    require_once('test_mvc_module_user_auth2_student.php');  // Require the module
?>

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
        $display_type = 'start';
        $error_message = '';
        include ('test_mvc_view_start_user_auth2_student.php');
        exit;
    }
    
    // When 'page' comes - the user sends 'signin', '...
    
    if ($_POST['page'] == 'StartPage')
    {
        $command = $_POST['command'];
    
        switch($command) 
        {
        // The user sent 'SignIn' data.
        case 'SignIn':
            $username = $_POST['username'];
            $password = $_POST['password'];
            if (checkval($username, $password))  // For testing, validity checking
                include('test_mvc_view_main_basic_student.php');  // Main page
            else {
                $display_type = 'signin';
                $error_message = "<span style='color:Red'>* Wrong username of password<br></span>";
                include('test_mvc_view_start_user_auth2_student.php');  // Start page
            }
                
            exit();  // exit

        case 'Join':
            // Do 'join' task, and
            
            exit();

        case 'ForgotPassword':
            // Do 'forgotpassword' task, and

            exit();

        default:
            echo 'Unknown command<br>';
            exit();
        }
    }
    
    else if ($page == 'MainPage') {
        // ...
    }
    
    else {
        echo 'Unknown page<br>';
    }
?>
